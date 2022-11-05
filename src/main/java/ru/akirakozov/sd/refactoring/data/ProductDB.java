package ru.akirakozov.sd.refactoring.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    private static final String DATABASE_URL = "jdbc:sqlite:test.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.err.println("Can not get connection: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void initTables() {
        try (Statement statement = getConnection().createStatement()) {
            String query =
                "CREATE TABLE IF NOT EXISTS PRODUCT" +
                    "(ID    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " NAME  TEXT                              NOT NULL, " +
                    " PRICE INT                               NOT NULL)";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Can not initialize table: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void add(Product product) {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute("INSERT INTO PRODUCT (NAME, PRICE) VALUES " + product.toString());
        } catch (SQLException e) {
            System.err.println("Can not execute query: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Integer sum() {
        return execQuery("SELECT SUM(price) FROM PRODUCT", getSingleInteger).get(0);
    }

    public static Integer count() {
        return execQuery("SELECT COUNT(*) FROM PRODUCT", getSingleInteger).get(0);
    }

    public static Product max() {
        return execQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1", getProduct).get(0);
    }

    public static Product min() {
        return execQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1", getProduct).get(0);
    }

    public static List<Product> getAll() {
        return execQuery("SELECT * FROM PRODUCT", getProduct);
    }

    private static final ResultParser<Product> getProduct =
        rs -> new Product(rs.getString("name"), rs.getInt("price"));

    private static final ResultParser<Integer> getSingleInteger = rs -> rs.getInt(1);

    private static <T> List<T> execQuery(String query, ResultParser<T> rp) {
        ArrayList<T> res = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                res.add(rp.apply(rs));
            }
        } catch (SQLException e) {
            System.err.println("Can not execute query: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return res;
    }
}
