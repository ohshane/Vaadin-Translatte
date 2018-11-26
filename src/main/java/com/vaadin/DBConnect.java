package com.vaadin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/translatte?serverTimezone=UTC", "root", "Gr22npo!s0n");
            statement = connection.createStatement();

        } catch (Exception e) {
            System.out.println("0>>" + e.fillInStackTrace());
        }
    }

    public void getData() {
        try {
            String query = "SELECT * FROM translatte.sourcefilegeneral";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String fileName = resultSet.getString("fileName");
                String sourceLang = resultSet.getString("sourceLang");
                String targetLang = resultSet.getString("targetLang");

                System.out.printf("%s %s %s %s\n", id, fileName, sourceLang, targetLang);
            }

        } catch (Exception e) {
            System.out.println("1>>" + e.fillInStackTrace());
        }
    }

    public static void main(String[] args) {
        DBConnect connect = new DBConnect();
        connect.getData();
    }

}
