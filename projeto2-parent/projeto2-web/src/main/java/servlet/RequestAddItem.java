package servlet;

import data.Country;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/RequestAddItem")
public class RequestAddItem extends HttpServlet {

    @EJB
    ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()){
            User user = (User) request.getSession().getAttribute("currentSessionUser");

            Item item = new Item();
            item.setPrice(Float.parseFloat(request.getParameter("price")));
            item.setName(request.getParameter("name"));
            item.setCountry((Country) request.getAttribute("country"));
            item.setOwner(user);
            item.setPicture(request.getParameter("picture"));
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(utilDate.getTime());
            item.setDateOfInsertion(date);

            myItemBean.addItem(item);
            //List<Item> items = myItemBean.searchItemsByUser(user)
            RequestDispatcher rd = request.getRequestDispatcher("/Meus_items.jsp");
            //request.setAttribute("items", items);
            rd.include(request, response);
        } catch (Exception e){
            System.out.println("[ADDING ITEM ERROR] " + e);
            response.sendRedirect("/MyBay.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }


}
