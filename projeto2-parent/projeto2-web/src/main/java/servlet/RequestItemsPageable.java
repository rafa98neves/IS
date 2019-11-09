package servlet;

import data.Item;
import ejb.ItemBeanLocal;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;


@WebServlet("/RequestItemsPageable")
public class RequestItemsPageable extends HttpServlet {

    @EJB
    ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()){

            String search = request.getParameter("search");
            String category = request.getParameter("category");
            String country = request.getParameter("country");
            String min = request.getParameter("min");
            String max = request.getParameter("max");
            String date = request.getParameter("date");
            String by = request.getParameter("by");
            String order = request.getParameter("order");
            String order_type = "asc";

            if(order != null) {
                switch (order) {
                    case "asc":
                        order_type = "desc";
                        break;
                    case "desc":
                        order_type = "asc";
                        break;
                    default:
                        order_type = "asc";
                }
                request.setAttribute("order",order_type);
            }

            List<Item> items;
            if(search == null) search = "";

            if(category != null){-
                items = myItemBean.searchItemsByCategory(search, Long.parseLong(category), by, order_type);
            }
            else if(country != null){
                items = myItemBean.searchItemsByCountry(search,Long.parseLong(country), by, order_type);
            }
            else if(min != null || max != null){
                items = myItemBean.searchItemsByPriceRange(search,Float.parseFloat(min), Float.parseFloat(max), by, order_type);
            }
            else if(date != null){
                items = myItemBean.searchItemsByDateOfInsertion(search, Date.valueOf(date), by, order_type);
            }
            else{
                items = myItemBean.searchAllItems(search, by, order_type);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/MyBay.jsp");
            request.setAttribute("items", items);
            request.setAttribute("search",search);
            request.setAttribute("category",category);
            request.setAttribute("country",country);
            request.setAttribute("min_price",min);
            request.setAttribute("max_price",max);
            request.setAttribute("date",date);
            request.setAttribute("by",by);
            rd.forward(request, response);

        } catch (Exception e){
            request.setAttribute("alert",e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }


}
