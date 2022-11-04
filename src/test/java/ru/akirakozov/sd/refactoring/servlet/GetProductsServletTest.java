package ru.akirakozov.sd.refactoring.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.akirakozov.sd.refactoring.CommonServletTest;
import ru.akirakozov.sd.refactoring.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetProductsServletTest extends CommonServletTest {

    private final GetProductsServlet servlet = new GetProductsServlet();

    @Test
    public void testGetList() throws IOException {
        // Prepare data
        List<Product> data = new ArrayList<>(Arrays.asList(
            new Product("iPhone 6", 300),
            new Product("iPhone 7", 400)
        ));
        addProducts(data);

        // Action
        servlet.doGet(request, response);

        // Assert
        assertEquals(
            "<html><body>\n" +
                "iPhone 6\t300</br>\n" +
                "iPhone 7\t400</br>\n" +
            "</body></html>\n",
            stringWriter.toString()
        );
    }
}
