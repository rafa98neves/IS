package servlet;

import data.Category;
import data.Country;
import data.Item;
import data.User;
import ejb.ItemBeanLocal;

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

@WebServlet("/RequestItemChange")
public class RequestItemChange extends HttpServlet {

    @EJB ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        try(PrintWriter out = response.getWriter()) {
            String name= request.getParameter("name");
            long categoryId = Long.parseLong(request.getParameter("category"));
            long countryId = Long.parseLong(request.getParameter("country"));
            String picture = request.getParameter("picture");
            float price = Float.parseFloat(request.getParameter("price"));
            long itemId = Long.parseLong(request.getParameter("itemId"));
            Item item;
            if((item = myItemBean.editItem(itemId,name,categoryId,countryId, picture, price))!= null){
                RequestDispatcher rd = request.getRequestDispatcher("/Detalhes.jsp");
                request.setAttribute("item", item);
                rd.forward(request, response);
            }
            else{
                RequestDispatcher rd = request.getRequestDispatcher("/Detalhes.jsp");
                request.setAttribute("alert","Não foi possível editar o seu item");
                rd.forward(request, response);
            }
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
