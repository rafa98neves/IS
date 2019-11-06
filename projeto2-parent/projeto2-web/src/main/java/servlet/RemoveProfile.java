package servlet;

import data.Country;
import data.Item;
import data.User;
import ejb.ItemBeanLocal;
import ejb.UserBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/RemoveProfile")
public class RemoveProfile extends HttpServlet {

    @EJB
    UserBeanLocal myUserBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()){
            User user = (User) request.getSession().getAttribute("currentSessionUser");
            myUserBean.deleteUser(user);
            RequestDispatcher rd = request.getRequestDispatcher("RequestLogout");
            rd.forward(request, response);
        } catch (Exception e){
            System.out.println("[REMOVING USER ERROR] " + e);
            response.sendRedirect("/Login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }


}