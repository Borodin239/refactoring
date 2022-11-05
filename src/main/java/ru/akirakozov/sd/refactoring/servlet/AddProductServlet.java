package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.data.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.akirakozov.sd.refactoring.data.ProductDB.add;

/**
 * @author akirakozov
 */
public class AddProductServlet extends AbstractServlet {

    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        add(new Product(name, price));

        response.getWriter().println("OK");
    }
}
