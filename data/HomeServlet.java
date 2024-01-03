package [appnamemin].controllers;

import java.io.IOException;
import cradle.CradleServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeServlet extends CradleServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getSwan().dispatch(req, resp, "home.jsp", "Welcome");
    }
    
}
