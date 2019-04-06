package com.example;

import java.sql.*;

public class Main {
    public static final String DB_NAME = "testjdbc.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\database\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";


    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + " text, " +
                    COLUMN_PHONE + " int, " +
                    COLUMN_EMAIL + " text" +
                    ")");

            insertToDB(statement, "Ionut", 40720165989L, "ionut@gmail.com");
            insertToDB(statement, "Andrei", 40781922123L, "Andrei@GMAIL.COM");
            insertToDB(statement, "Alex", 40789129271L, "Alex@GMAIL.COM");
            insertToDB(statement, "Mirela", 40723091823L, "Mirela@GMAIL.COM");


            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " +
                    COLUMN_PHONE + "=40721982828 " +
                    "WHERE " + COLUMN_NAME + "='Mirela'");

            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " +
                    COLUMN_PHONE + "=40711111111 " +
                    "WHERE " + COLUMN_NAME + "='Alex'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS +
                    " WHERE " + COLUMN_NAME + "= 'Cristian'");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(COLUMN_NAME) + " " +
                        resultSet.getLong(COLUMN_PHONE) + " " +
                        resultSet.getString(COLUMN_EMAIL));
            }
            resultSet.close();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertToDB(Statement statement, String name, long phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                "(" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL +
                ")" +
                "VALUES('" + name + "', " + phone + ",' " + email + "')");
    }
}
