package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/RequestLogin")
public class RequestLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()){
            String name= request.getParameter("email");
            String psw= request.getParameter("psw");
        }finally {

        }
    }


}
