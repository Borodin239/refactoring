package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.akirakozov.sd.refactoring.data.ProductDB.getAll;
import static ru.akirakozov.sd.refactoring.html.HtmlUtils.getProductsHTML;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends AbstractServlet {

    @Override
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter()
            .println(
                getProductsHTML(getAll(), "")
            );
    }
}
