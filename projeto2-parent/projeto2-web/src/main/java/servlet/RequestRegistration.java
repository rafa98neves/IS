package servlet;

import Service.MyLogger;
import com.google.common.hash.Hashing;
import ejb.RegisterBeanLocal;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import java.nio.charset.StandardCharsets;
import java.sql.Date;


@WebServlet("/RequestRegistration")
public class RequestRegistration extends HttpServlet {

    @EJB RegisterBeanLocal myRegisterBean;
    final static Logger logger = LogManager.getLogger(MyLogger.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        response.setContentType("text/html");
        //PropertyConfigurator.configure("src/log4j.properties");
        ServletContext context= getServletContext();
        logger.info("RequestRegistration doPost() method called");
        try (PrintWriter out = response.getWriter()){
            request.setCharacterEncoding("UTF-8");
            String name= request.getParameter("name");
            String email= request.getParameter("email");
            String raw_country= request.getParameter("country");
            Date birthdate = Date.valueOf(request.getParameter("birthdate"));
            logger.info("RequestRegistration hashing password");
            String psw= Hashing.sha256()
                .hashString(request.getParameter("psw"), StandardCharsets.UTF_8)
                .toString();

            if(myRegisterBean.registerUser(name,email,raw_country,birthdate,psw)){
                logger.info("RequestRegistration auth succeeded");
                RequestDispatcher rd = context.getRequestDispatcher("/Login.jsp");
                rd.forward(request, response);
            }
            else{
                logger.info("RequestRegistration auth negated");
                request.setAttribute("alert","Este email já tem conta associada");
                RequestDispatcher rd = context.getRequestDispatcher("/Registo.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e){
            logger.info("RequestRegistration error occurred");
            request.setAttribute("alert",e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }




}
