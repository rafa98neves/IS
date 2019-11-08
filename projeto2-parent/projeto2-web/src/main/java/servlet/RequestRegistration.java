package servlet;

import data.Country;
import ejb.RegisterBean;
import ejb.RegisterBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;


@WebServlet("/RequestRegistration")
public class RequestRegistration extends HttpServlet {

    @EJB RegisterBeanLocal myRegisterBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html");
        ServletContext context= getServletContext();
        try (PrintWriter out = response.getWriter()){
            request.setCharacterEncoding("UTF-8");
            String name= request.getParameter("name"); //get name
            String email= request.getParameter("email"); //get email

            String raw_country= request.getParameter("country"); //get country

            Date birthdate = Date.valueOf(request.getParameter("birthdate"));
            String psw= request.getParameter("psw"); //get password

            if(myRegisterBean.registerUser(name,email,raw_country,birthdate,psw)){
                RequestDispatcher rd = context.getRequestDispatcher("/Login.jsp");
                rd.forward(request, response);
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Este email já está associado a um utilizador');");
                out.println("location='Registo.jsp';");
                out.println("</script>");
                RequestDispatcher rd = context.getRequestDispatcher("/Registo.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e){
            System.out.println("[REQUEST REGISTRATION ERROR] " + e);
            response.sendRedirect("/Registo.jsp");
        }
    }




}
