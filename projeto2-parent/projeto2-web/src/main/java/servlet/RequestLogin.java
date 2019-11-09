package servlet;

import Service.MyLogger;
import com.google.common.hash.Hashing;
import data.User;
import ejb.LoginBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet("/RequestLogin")
public class RequestLogin extends HttpServlet {
    @EJB LoginBeanLocal myLoginBean;

    final static Logger logger = LogManager.getLogger(MyLogger.class);

    private void setSession(HttpServletRequest request, User user){
        logger.info("Request Login setting user session");
        HttpSession session = request.getSession(true);
        session.setAttribute("currentSessionUser",user);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        //PropertyConfigurator.configure("log4j.properties");

        try (PrintWriter out = response.getWriter()){
            String email= request.getParameter("email");
            logger.info("Request Login hashing password");
            String psw= Hashing.sha256()
                .hashString(request.getParameter("psw"), StandardCharsets.UTF_8)
                .toString();
            User user = myLoginBean.login(email,psw);

            if(user != null){
                logger.info("Request Login succeeded");
                setSession(request, user);
                response.sendRedirect("RequestItemsPageable");
            }
            else{
                logger.info("Request Login negated");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                request.setAttribute("alert","Email ou password errados!");
                rd.forward(request,response);
            }
        } catch (Exception e){
            logger.info("Request Login error occurred");
            request.setAttribute("alert",e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("RequestLogin doPost() method called");
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("RequestLogin doGet() method called");
        processRequest(request, response);
    }


}
