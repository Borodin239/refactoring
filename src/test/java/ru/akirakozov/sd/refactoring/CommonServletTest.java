package ru.akirakozov.sd.refactoring;

import lombok.val;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mock;
import ru.akirakozov.sd.refactoring.data.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class CommonServletTest {
    private final static String DB_URL = "jdbc:sqlite:test.db";
    private static Connection connection;
    @Mock
    protected HttpServletRequest request;
    @Mock
    protected HttpServletResponse response;
    protected StringWriter stringWriter;


    @BeforeClass
    public static void setUpConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
        executeQuery(
            "CREATE TABLE IF NOT EXISTS PRODUCT" +
                "(ID    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME  TEXT                              NOT NULL, " +
                " PRICE INT                               NOT NULL)"
        );
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Before
    public void beforeTest() throws IOException {
        // Clear DB
        executeQuery("DELETE FROM PRODUCT;");

        // Prepare response
        stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
    }

    protected void addProduct(Product product) {
        executeQuery(
            "INSERT INTO PRODUCT (NAME, PRICE) VALUES " + product.toString()
        );
    }

    protected void addProducts(List<Product> productList) {
        productList.forEach(this::addProduct);
    }

    protected List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            val res = statement.executeQuery("SELECT * FROM PRODUCT;");
            if (res == null) return result;
            while (res.next()) {
                result.add(
                    new Product(
                        res.getString("name"),
                        res.getInt("price")
                    )
                );
            }
        } catch (SQLException e) {
            System.err.println("Can not get data from PRODUCT: " + e.getMessage());
        }

        return result;
    }

    private static void executeQuery(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            System.err.println("Can not execute query (" + query + "): " + e.getMessage());
        }
    }
}
