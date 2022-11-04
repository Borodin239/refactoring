package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet extends HttpServlet {

    protected abstract void doRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doRequest(request, response);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
