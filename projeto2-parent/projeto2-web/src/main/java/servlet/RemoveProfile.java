package servlet;

import Service.MyLogger;
import data.User;
import ejb.UserBeanLocal;
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

@WebServlet("/RemoveProfile")
public class RemoveProfile extends HttpServlet {
    final static Logger logger = LogManager.getLogger(MyLogger.class);

    @EJB
    UserBeanLocal myUserBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        //PropertyConfigurator.configure("src/log4j.properties");
        try (PrintWriter out = response.getWriter()){
            User user = (User) request.getSession().getAttribute("currentSessionUser");
            myUserBean.deleteUser(user);
            logger.info("RemoveProfile succeeded");
            RequestDispatcher rd = request.getRequestDispatcher("RequestLogout");
            rd.forward(request, response);
        } catch (Exception e){
            logger.info("RemoveProfile error occurred");
            request.setAttribute("alert",e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("RemoveProfile doPost() method called");
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("RemoveProfile doPost() method called");
        processRequest(request, response);
    }


}
