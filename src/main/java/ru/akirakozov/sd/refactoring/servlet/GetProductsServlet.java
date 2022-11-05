package ru.akirakozov.sd.refactoring.servlet;

import lombok.val;
import ru.akirakozov.sd.refactoring.data.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.akirakozov.sd.refactoring.data.ProductDB.getAll;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends AbstractServlet {

    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("<html><body>");

        val res = getAll();

        for (Product product: res) {
            response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
        }
        response.getWriter().println("</body></html>");
    }
}
