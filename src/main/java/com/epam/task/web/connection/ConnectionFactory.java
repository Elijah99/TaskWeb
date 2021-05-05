package com.epam.task.web.connection;

import com.epam.task.web.loader.PropertiesLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String PROPERTIES_FILE = "db.properties";

    private final String url;
    private final String driver;
    private final String login;
    private final String password;

    ConnectionFactory() {
        try {
            Properties properties = new PropertiesLoader().loadProperties(PROPERTIES_FILE);

            driver = properties.getProperty("db.driver");
            Class.forName(driver);

            url = properties.getProperty("db.url");
            login = properties.getProperty("db.login");
            password = properties.getProperty("db.password");

        } catch (ClassNotFoundException | IOException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    Connection create() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }
}

