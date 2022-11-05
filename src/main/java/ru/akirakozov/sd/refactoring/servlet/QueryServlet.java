package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static ru.akirakozov.sd.refactoring.data.ProductDB.*;
import static ru.akirakozov.sd.refactoring.html.HtmlUtils.getProductsHTML;
import static ru.akirakozov.sd.refactoring.html.HtmlUtils.getStatisticHTML;

/**
 * @author akirakozov
 */
public class QueryServlet extends AbstractServlet {
    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");
        if ("max".equals(command)) {
            response.getWriter().println(
                getProductsHTML(Collections.singletonList(max()), "<h1>Product with max price: </h1>\n"));
        } else if ("min".equals(command)) {
            response.getWriter().println(
                getProductsHTML(Collections.singletonList(min()), "<h1>Product with min price: </h1>\n"));
        } else if ("sum".equals(command)) {
            response.getWriter().println(getStatisticHTML(sum(), "Summary price: \n"));
        } else if ("count".equals(command)) {
            response.getWriter().println(getStatisticHTML(count(), "Number of products: \n"));
        } else {
            response.getWriter().println("Unknown command: " + command);
        }
    }
}
