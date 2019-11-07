package servlet;

import data.User;
import ejb.InformationBeanLocal;
import ejb.LoginBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/RequestLogin")
public class RequestLogin extends HttpServlet {

    @EJB LoginBeanLocal myLoginBean;

    private void setSession(HttpServletRequest request, User user){
        HttpSession session = request.getSession(true);
        session.setAttribute("currentSessionUser",user);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()){
            String email= request.getParameter("email");
            String psw= request.getParameter("psw");

            User user = myLoginBean.login(email,psw);

            if(user != null){
                setSession(request, user);
                response.sendRedirect("RequestItemsPageable");
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Email ou password incorrectos');");
                out.println("location='Login.jsp';");
                out.println("</script>");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request,response);
                out.close();
            }
        } catch (Exception e){
            System.out.println("[REQUEST LOGIN ERROR] " + e);
            response.sendRedirect("Login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }


}
