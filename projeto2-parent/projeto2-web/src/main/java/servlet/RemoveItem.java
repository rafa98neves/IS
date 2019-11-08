package servlet;

import data.Item;
import data.User;
import ejb.ItemBeanLocal;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RemoveItem")
public class RemoveItem extends HttpServlet {

    @EJB
    ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()){
            User user = (User) request.getSession().getAttribute("currentSessionUser");
            myItemBean.delete(Long.parseLong(request.getParameter("ItID")), user);
            RequestDispatcher rd = request.getRequestDispatcher("/Meus_items.jsp");
            rd.forward(request, response);
        } catch (Exception e){
            System.out.println("[REMOVING ITEM ERROR] " + e);
            response.sendRedirect("/Meus_items.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }


}
