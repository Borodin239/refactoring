package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.akirakozov.sd.refactoring.CommonServletTest;
import ru.akirakozov.sd.refactoring.Product;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryServletTest extends CommonServletTest {
    private final QueryServlet servlet = new QueryServlet();

    @Before
    public void addDefaultData() {
        addProduct(new Product("Samsung Z Fold 4", 150_000));
        addProduct(new Product("iPhone 12 mini", 40_000));
    }

    @Test
    public void emptyOperation() throws IOException {
        // Action
        servlet.doGet(request, response);

        // Assert
        assertEquals(
            "Unknown command: null\n",
            stringWriter.toString()
        );
    }

    @Test
    public void testMinRequest() throws IOException {
        // Rules
        when(request.getParameter("command")).thenReturn("min");

        // Action
        servlet.doGet(request, response);

        // Assert
        assertEquals(
            getHTML("<h1>Product with min price: </h1>\n" +
                "iPhone 12 mini\t40000</br>\n"),
            stringWriter.toString()
        );
    }

    @Test
    public void testMaxRequest() throws IOException {
        // Rules
        when(request.getParameter("command")).thenReturn("max");

        // Action
        servlet.doGet(request, response);

        // Assert
        assertEquals(
            getHTML("<h1>Product with max price: </h1>\n" +
                    "Samsung Z Fold 4\t150000</br>\n"),
            stringWriter.toString()
        );
    }

    @Test
    public void testSumRequest() throws IOException {
        // Rules
        when(request.getParameter("command")).thenReturn("sum");

        // Action
        servlet.doGet(request, response);

        // Assert
        assertEquals(
            getHTML("Summary price: \n" +
                    "190000\n"),
            stringWriter.toString()
        );
    }

    @Test
    public void testCountRequest() throws IOException {
        // Rules
        when(request.getParameter("command")).thenReturn("count");

        // Action
        servlet.doGet(request, response);

        // Assert
        assertEquals(
            getHTML("Number of products: \n" +
                    "2\n"),
            stringWriter.toString()
        );
    }

    private String getHTML(String content) {
        return String.format("<html><body>\n%s</body></html>\n", content);
    }
}
