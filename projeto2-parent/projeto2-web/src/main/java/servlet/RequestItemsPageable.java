package servlet;

import data.Item;
import ejb.ItemBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/RequestItemsPageable")
public class RequestItemsPageable extends HttpServlet {

    @EJB
    ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()){

            String search = request.getParameter("search");
            List<Item> items = null;
            if(search == null)
                items = myItemBean.searchAllItems("");
            else
                items = myItemBean.searchAllItems(search);

            if(items != null){
                RequestDispatcher rd = request.getRequestDispatcher("/MyBay.jsp");
                request.setAttribute("items", items);
                rd.include(request, response);
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Nao foram encontrados itens para a sua pesquisa');");
                out.println("location='MyBay.jsp';");
                out.println("</script>");
                RequestDispatcher rd = request.getRequestDispatcher("/MyBay.jsp");
                rd.include(request, response);
                out.close();
            }
        } catch (Exception e){
            System.out.println("[REQUEST ITEMS PAGEABLE ERROR] " + e);
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
