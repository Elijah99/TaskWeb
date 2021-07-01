package com.epam.task.web.mapper;

import com.epam.task.web.entity.Order;
import com.epam.task.web.entity.OrderStatus;
import com.epam.task.web.entity.PaymentType;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class OrderRowMapper implements RowMapper<Order> {
    private final static String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String CREATION_DATE = "creation_date";
    private final static String ORDER_DATE = "order_date";
    private final static String STATUS = "status";
    private final static String PAYMENT_TYPE = "payment_type";
    private final static String RATING = "rating";
    private final static String COMMENT = "comment";

    @Override
    public Order mapRow(ResultSet resultSet) throws SQLException {
        BigInteger id = (BigInteger) resultSet.getObject(ID);
        BigInteger userId = (BigInteger) resultSet.getObject(USER_ID);
        Timestamp creationDate = resultSet.getTimestamp(CREATION_DATE);
        Timestamp orderDate = resultSet.getTimestamp(ORDER_DATE);
        String statusString = resultSet.getString(STATUS);
        OrderStatus status = getStatusFromString(statusString);
        String paymentTypeString = resultSet.getString(PAYMENT_TYPE);
        PaymentType paymentType = getPaymentTypeFromString(paymentTypeString);
        double rating = resultSet.getDouble(RATING);
        String comment = resultSet.getString(COMMENT);
        Order order = new Order(id, userId, creationDate, orderDate, status, paymentType, rating, comment);
        return order;
    }

    private OrderStatus getStatusFromString(String text) throws SQLException {
        Optional<OrderStatus> statusOptional = OrderStatus.fromString(text);
        if (statusOptional.isPresent()) {
            return statusOptional.get();
        } else {
            throw new SQLException("Unknown role");
        }
    }

    private PaymentType getPaymentTypeFromString(String text) throws SQLException {
        Optional<PaymentType> paymentTypeOptional = PaymentType.fromString(text);
        if (paymentTypeOptional.isPresent()) {
            return paymentTypeOptional.get();
        } else {
            throw new SQLException("Unknown role");
        }
    }
}
