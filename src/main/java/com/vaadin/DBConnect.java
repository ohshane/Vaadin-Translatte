package com.vaadin;

import java.sql.*;

public class DBConnect {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private String tableName;

    public DBConnect(String tableName) {
        this.tableName = tableName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/translatte?serverTimezone=UTC", "root", "Gr22npo!s0n");
            statement = connection.createStatement();

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public boolean getData() {
        try {
            String query = "SELECT * FROM translatte." + tableName;
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String fileName = resultSet.getString("fileName");
                String sourceLang = resultSet.getString("sourceLang");
                String targetLang = resultSet.getString("targetLang");

                System.out.printf("%s %s %s %s\n", id, fileName, sourceLang, targetLang);
            }
            return true;

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return false;
        }
    }

    public boolean addSourceData(String fileName, String sourceLang, String targetLang, String sourceFile) {
        try {
            String query = String.format("INSERT INTO translatte.%s (fileName, sourceLang, targetLang, sourceFile) VALUES (%s, %s, %s, %s)", tableName, fileName, sourceLang, targetLang, sourceFile);
            statement.execute(query);
            return true;

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return false;
        }
    }

    public boolean deleteData(String id) {
        try {
            String query = String.format("DELETE FROM translatte.%s WHERE (id = %s)", tableName, id);
            statement.execute(query);
            return true;

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return false;
        }
    }

}
