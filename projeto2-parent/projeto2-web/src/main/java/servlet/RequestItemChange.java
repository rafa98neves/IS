package servlet;

import data.Category;
import data.Country;
import data.Item;
import data.User;
import ejb.ItemBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RequestItemChange")
public class RequestItemChange extends HttpServlet {

    @EJB
    ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        ServletContext context = getServletContext();
        try(PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            String name= request.getParameter("name");
            Category category = (Category) request.getAttribute("category");
            Country country = (Country) request.getAttribute("country");
            String picture = request.getParameter("picture");
            float price = Float.parseFloat(request.getParameter("price"));
            long itemId = Long.valueOf(request.getParameter("itemId"));

            Item item;
            if((item = myItemBean.editItem(itemId,name,category,country, picture))!= null){
                RequestDispatcher rd = context.getRequestDispatcher("/Detalhes.jsp");
                request.setAttribute("item", item);
                rd.forward(request, response);
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Erro ao editar o item');");
                out.println("location='Detalhes.jsp';");
                out.println("</script>");
                RequestDispatcher rd = context.getRequestDispatcher("/Detalhes.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e){
            System.out.println("[ITEM CHANGE ERROR] " + e);
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
