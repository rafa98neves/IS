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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet("/RequestItem")
public class RequestItem extends HttpServlet {
    @EJB ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        try(PrintWriter out = response.getWriter()) {
            String id = request.getParameter("ItID");
            Item item = myItemBean.findItemById(Long.parseLong(id));
            if(item != null){
                RequestDispatcher rd = request.getRequestDispatcher("/Detalhes.jsp");
                request.setAttribute("item", item);
                rd.forward(request, response);
            }else{
                RequestDispatcher rd = request.getRequestDispatcher("/MyBay.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e){
            System.out.println("[ITEM DETAILS ERROR] " + e);
            response.sendRedirect("MyBay.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }


}
