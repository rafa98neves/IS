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

@WebServlet("/RequestItem")
public class RequestItem extends HttpServlet {
    @EJB ItemBeanLocal myItemBean;
    final static Logger logger = LogManager.getLogger(MyLogger.class);

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        //PropertyConfigurator.configure("src/log4j.properties");
        try(PrintWriter out = response.getWriter()) {
            String id = request.getParameter("ItID");
            Item item = myItemBean.findItemById(Long.parseLong(id));
            logger.info("Requestitem finding item...");
            if(item != null){
                logger.info("Requestitem item found");
                RequestDispatcher rd = request.getRequestDispatcher("/Detalhes.jsp");
                request.setAttribute("item", item);
                rd.forward(request, response);
            }else{
                logger.info("Requestitem not found");
                RequestDispatcher rd = request.getRequestDispatcher("/MyBay.jsp");
                request.setAttribute("alert","Este item está indisponível");
                rd.forward(request, response);
            }

        } catch (Exception e){
            logger.info("Requestitem error occurred");
            request.setAttribute("alert",e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Requestitem doPost() method called");
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Requestitem doGet() method called");
        processRequest(request, response);
    }


}
