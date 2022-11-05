package ru.akirakozov.sd.refactoring.servlet;

import lombok.val;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.akirakozov.sd.refactoring.data.ProductDB.*;

/**
 * @author akirakozov
 */
public class QueryServlet extends AbstractServlet {
    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        if ("max".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with max price: </h1>");

            val res = max();

            response.getWriter().println(res.getName() + "\t" + res.getPrice() + "</br>");
            response.getWriter().println("</body></html>");
        } else if ("min".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with min price: </h1>");

            val res = min();

            response.getWriter().println(res.getName() + "\t" + res.getPrice() + "</br>");
            response.getWriter().println("</body></html>");
        } else if ("sum".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("Summary price: ");

            val res = sum();

            response.getWriter().println(res);
            response.getWriter().println("</body></html>");
        } else if ("count".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("Number of products: ");

            val res = count();

            response.getWriter().println(res);
            response.getWriter().println("</body></html>");
        } else {
            response.getWriter().println("Unknown command: " + command);
        }
    }

}
