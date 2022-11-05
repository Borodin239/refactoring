package ru.akirakozov.sd.refactoring.servlet;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.akirakozov.sd.refactoring.CommonServletTest;
import ru.akirakozov.sd.refactoring.data.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServletTest extends CommonServletTest {
    private final AddProductServlet servlet = new AddProductServlet();

    @Test
    public void testSuccessfulAddition() throws IOException {
        // Init data
        List<Product> data = new ArrayList<>(Arrays.asList(
            new Product("iPhone 6", 300),
            new Product("iPhone 7", 400)
        ));

        // Rules
        for (Product product: data) {
            when(request.getParameter("name")).thenReturn(product.getName());
            when(request.getParameter("price")).thenReturn(Integer.toString(product.getPrice()));

            // Action
            servlet.doGet(request, response);

            // Assert response code
            assertEquals("OK" + System.lineSeparator(), stringWriter.toString());

            stringWriter = new StringWriter();
            when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
        }

        // Assertion
        val result = getProducts();
        assertTrue(result.containsAll(data));
        assertTrue(data.containsAll(result));
    }

    @Test
    public void testWrongArguments() {
        // Prepare data
        request.getParameter("WrongArgument");

        // Action & Assert
        assertThrows(NumberFormatException.class, () -> servlet.doGet(request, response));
    }
}
