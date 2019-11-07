package servlet;

import data.Item;
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
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


@WebServlet("/RequestItemsPageable")
public class RequestItemsPageable extends HttpServlet {

    @EJB
    ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()){
            String search = request.getParameter("search");
            String category = request.getParameter("category");
            String country = request.getParameter("country");
            String min_price = request.getParameter("min");
            String max_price = request.getParameter("max");
            String date = request.getParameter("date");

            List<Item> items;
            if(search == null)
                items = myItemBean.searchAllItems("");
            else
                items = myItemBean.searchAllItems(search);
            Item item = new Item();
            item.setName("meu item");
            item.setPrice(1.2f);
            items.add(item);

            Item item2 = new Item();
            item2.setName("item de outro");
            item2.setPrice(1.3f);
            items.add(item2);

            RequestDispatcher rd = request.getRequestDispatcher("/MyBay.jsp");
            request.setAttribute("items", items);
            rd.forward(request, response);
        } catch (Exception e){
            System.out.println("[REQUEST ITEMS PAGEABLE ERROR] " + e);
            response.sendRedirect("MyBay.jsp");
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        processRequest(request, response);
    }


}
