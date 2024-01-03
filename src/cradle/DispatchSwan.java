package cradle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.GsonBuilder;

import cradle.utils.Constantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DispatchSwan {
    private String default_route;
    private CradleRoute[] sidebar_routes;
    private String layout;
    public String getLayout() {
        return layout;
    }
    public void setLayout(String layout) {
        this.layout = layout;
    }
    public String getDefault_route() {
        return default_route;
    }
    public void setDefault_route(String default_route) {
        this.default_route = default_route;
    }
    public static DispatchSwan getSwan(HttpServlet servlet) throws FileNotFoundException {
        File configs=new File(servlet.getServletContext().getRealPath(Constantes.CRADLEROUTE_FILE));
        String content="";
        try(Scanner scanner=new Scanner(configs)){
            while(scanner.hasNextLine()){
                content+=scanner.nextLine();
            }
        }
        DispatchSwan dispat=new GsonBuilder().create().fromJson(content, DispatchSwan.class);
        return dispat;
    }
    public static DispatchSwan getSwan(ServletContext servletContext) throws FileNotFoundException {
        File configs=new File(servletContext.getRealPath(Constantes.CRADLEROUTE_FILE));
        String content="";
        try(Scanner scanner=new Scanner(configs)){
            while(scanner.hasNextLine()){
                content+=scanner.nextLine();
            }
        }
        DispatchSwan dispat=new GsonBuilder().create().fromJson(content, DispatchSwan.class);
        return dispat;
    }
    public void dispatch(HttpServletRequest req, HttpServletResponse resp, String view, String tabtitle) throws ServletException, IOException{
        req.setAttribute("view", view);
        req.setAttribute("routes", getSidebar_routes());
        req.setAttribute("tabtitle", tabtitle);
        RequestDispatcher dispat=req.getRequestDispatcher(getLayout());
        dispat.forward(req, resp);
    }
    public CradleRoute[] getSidebar_routes() {
        return sidebar_routes;
    }
    public void setSidebar_routes(CradleRoute[] sidebar_routes) {
        this.sidebar_routes = sidebar_routes;
    }
}
