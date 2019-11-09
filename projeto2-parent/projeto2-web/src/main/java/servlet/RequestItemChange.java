package servlet;

import Service.MyLogger;
import data.Item;
import ejb.ItemBeanLocal;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
    final static Logger logger = LogManager.getLogger(MyLogger.class);

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        //PropertyConfigurator.configure("src/log4j.properties");
        try(PrintWriter out = response.getWriter()) {
            String name= request.getParameter("name");
            long categoryId = Long.parseLong(request.getParameter("category"));
            long countryId = Long.parseLong(request.getParameter("country"));
            String picture = request.getParameter("picture");
            float price = Float.parseFloat(request.getParameter("price"));
            long itemId = Long.parseLong(request.getParameter("itemId"));
            Item item;
            if((item = myItemBean.editItem(itemId,name,categoryId,countryId, picture, price))!= null){
                logger.info("RequestItemChange item edited with success");
                RequestDispatcher rd = request.getRequestDispatcher("/Detalhes.jsp");
                request.setAttribute("item", item);
                rd.forward(request, response);
            }
            else{
                logger.info("RequestItemChange item not edited");
                RequestDispatcher rd = request.getRequestDispatcher("/Detalhes.jsp");
                request.setAttribute("alert","Não foi possível editar o seu item");
                rd.forward(request, response);
            }
        } catch (Exception e){
            logger.info("RequestItemChange error occurred");
            request.setAttribute("alert",e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("RequestItemChange doPost() method called");
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("RequestItemChange doGet() method called");
        processRequest(request, response);
    }


}
