package com.tuygun.sandbox.other.ocajp8.jdbc;

import java.sql.*;
import java.util.Properties;

public class JDBCTests {
    public static void main(String[] args) throws Exception {
        //getConnectionTests();
    }

    private static void getConnectionTests() throws SQLException {
        DriverManager.getConnection("jdbc:mysql://localhost:3306/testDb", "root", "p@ssw0rd");

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "p@ssw0rd");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDb", properties);

        Statement statement = connection.createStatement();
        boolean execute = statement.execute("SELECT * FROM MEMBERS");
        ResultSet resultSet1 = statement.getResultSet();
        resultSet1.getInt(1);
        resultSet1.getString(2);

        resultSet1.close();
        statement.close();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEMBERS WHERE AGE=? AND NAME=?");
        preparedStatement.setInt(1, 38);
        preparedStatement.setString(2, "Serhan");
        execute = preparedStatement.execute();
        ResultSet resultSet2 = preparedStatement.getResultSet();
        resultSet2.getInt(1);
        resultSet2.getString(2);
        while(resultSet2.next()){
            //do the mapping..
        }

        preparedStatement.close();
        // it is already closed, since prepared statement is closed.
        // If a resource is closed when it is already closed nothing will be happended.
        resultSet2.close();

        connection.close();
    }
}
