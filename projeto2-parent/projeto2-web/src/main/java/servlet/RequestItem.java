package servlet;

import data.Item;
import data.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RequestItem")
public class RequestItem extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html");
        try(PrintWriter out = response.getWriter()) {
            String n = request.getParameter("ItID");



            Item item = new Item();
            if (n.compareTo("meu item") == 0){
                item.setName(n);
                item.setPrice(1.2f);
                HttpSession session = request.getSession(true);
                item.setOwner((User) session.getAttribute("currentSessionUser"));
            }
            else {
                item.setName(n);
                item.setPrice(1.2f);
                User testes = new User();
                testes.setName("Ze das Couves");
                testes.setEmail("asdamsdklmaskdm@gmail.com");
                item.setOwner(testes);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/Detalhes.jsp");
            request.setAttribute("item", item);
            rd.forward(request, response);
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
