package servlet;

import Service.MyLogger;
import data.User;
import ejb.UserBeanLocal;
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
import java.sql.Date;

@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
    final static Logger logger = LogManager.getLogger(MyLogger.class);
    @EJB UserBeanLocal myUserBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //PropertyConfigurator.configure("src/log4j.properties");
        ServletContext context = getServletContext();
        logger.info("Edit Profile doPost() method called");
        try (PrintWriter out = response.getWriter()){
            request.setCharacterEncoding("UTF-8");
            String name= request.getParameter("name"); //get name
            String email= request.getParameter("email"); //get email
            String country_id= request.getParameter("country"); //get country
            Date birthdate = Date.valueOf(request.getParameter("birthdate"));

            User user;
            if((user = myUserBean.edit(name,Long.parseLong(country_id),email,birthdate)) != null){
                logger.info("Edit Profile succeeded");
                request.getSession().setAttribute("currentSessionUser", user);
                RequestDispatcher rd = context.getRequestDispatcher("/Perfil.jsp");
                rd.forward(request, response);
            }
            else{
                logger.info("Edit Profile negated");
                request.setAttribute("alert","Erro ao editar o perfil");
                RequestDispatcher rd = context.getRequestDispatcher("/Perfil.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e){
            logger.info("Edit Profile error occurred");
            request.setAttribute("alert",e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }

}
