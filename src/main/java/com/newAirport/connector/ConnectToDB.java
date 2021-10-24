package com.newAirport.connector;

import java.sql.*;

public class ConnectToDB {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    private static final String URL = "jdbc:mysql://localhost:3306/airport";
    private Connection connection;
    private Statement statement;

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Could not connect to DataBase");
            }
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;

            } catch (SQLException e) {
                System.err.println("Problem occurred while disconnecting from Database.");
            }
        }
    }

    public ResultSet select(String query) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return statement.executeQuery(query);
    }

    public int createUpdateDelete(String query) {
        int result = 0;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("SQL error or connection is closed!");
        }
        return result;
    }


}
