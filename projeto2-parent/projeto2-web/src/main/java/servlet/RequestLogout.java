package servlet;

import Service.MyLogger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RequestLogout")
public class RequestLogout extends HttpServlet {

    final static Logger logger = LogManager.getLogger(MyLogger.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //PropertyConfigurator.configure("src/log4j.properties");
        response.setContentType("text/html");
        response.sendRedirect("MyBay.jsp");
        request.getSession(false).invalidate();
        logger.info("User logged out");

    }
}
