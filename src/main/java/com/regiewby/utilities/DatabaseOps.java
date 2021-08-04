package com.regiewby.utilities;

import java.sql.*;
import java.util.HashMap;

public class DatabaseOps {


    private static DatabaseOps dbOps;

    private DatabaseOps(){}

    public static DatabaseOps getInstance() {
        if (dbOps == null) {
            dbOps = new DatabaseOps();
        }
        return dbOps;
    }

    private static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/db_learning";
        String username = "root";
        String password = "r1ghtn0W@999";
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    public HashMap<String,String> getData(String sqlQuery) throws SQLException {

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);

            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData md = resultSet.getMetaData();

            HashMap<String,String> dataTemp = new HashMap<>();

            while (resultSet.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    dataTemp.put(md.getColumnName(i),resultSet.getString(i));
                }
            }
            return dataTemp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
