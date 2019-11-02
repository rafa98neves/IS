package servlet;

import data.User;
import ejb.LoginBean;
import ejb.LoginBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/RequestLogin")
public class RequestLogin extends HttpServlet {

    @EJB LoginBeanLocal myLoginBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        ServletContext context= getServletContext();

        try (PrintWriter out = response.getWriter()){
            String email= request.getParameter("email");
            String psw= request.getParameter("psw");

            User user = myLoginBean.login(email,psw);

            if(user != null){
                RequestDispatcher rd = context.getRequestDispatcher("/");
                rd.include(request, response);
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Email ou password incorrectos');");
                out.println("location='login.html';");
                out.println("</script>");
                RequestDispatcher rd = context.getRequestDispatcher("/login.html");
                rd.include(request, response);
            }
        } catch (Exception e){
            //....
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }


}
