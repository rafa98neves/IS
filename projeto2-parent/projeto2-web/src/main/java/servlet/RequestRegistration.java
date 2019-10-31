package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/RequestRegistration")
public class RequestRegistration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()){
            String name= request.getParameter("email");
            String psw= request.getParameter("psw");
            ServletContext context= getServletContext();
            RequestDispatcher rd= context.getRequestDispatcher("/login.html");
            rd.include(request, response);
        } catch (Exception e){
            //....
        }
    }


}
