package com.epam.task.web.connection;

import com.epam.task.web.loader.PropertiesLoader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;

    private static final int POOL_SIZE = 10;

    private static final AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private static final ReentrantLock INITIALIZE_LOCK = new ReentrantLock();
    private static final ReentrantLock CONNECTION_LOCK = new ReentrantLock();
    private final ConnectionFactory connectionFactory;
    private final Semaphore connectionsSemaphore;

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
        connectionsSemaphore = new Semaphore(POOL_SIZE);
        connectionFactory = new ConnectionFactory();
        createConnections();
    }

    public static ConnectionPool getInstance() {
        if (INSTANCE.get() == null) {
            try {
                INITIALIZE_LOCK.lock();
                if (INSTANCE.get() == null) {
                    ConnectionPool pool = new ConnectionPool();
                    INSTANCE.getAndSet(pool);
                }
            } finally {
                INITIALIZE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    private void createConnections() {

        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = connectionFactory.create();
                ProxyConnection proxyConnection = new ProxyConnection(connection, this);
                availableConnections.add(proxyConnection);
            }
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    public ProxyConnection getConnection() {
        try {
            connectionsSemaphore.acquire();
            CONNECTION_LOCK.lock();

            ProxyConnection connection = availableConnections.poll();
            connectionsInUse.add(connection);
            return connection;

        } catch (InterruptedException e) {
            throw new ConnectionException(e);

        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    public void returnConnection(ProxyConnection connection) {
        CONNECTION_LOCK.lock();
        try {
            if (connectionsInUse.contains(connection)) {
                connectionsInUse.remove(connection);
                availableConnections.add(connection);

                connectionsSemaphore.release();
            }
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }
}

