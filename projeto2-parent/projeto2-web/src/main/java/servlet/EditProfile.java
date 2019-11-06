package servlet;

import data.Country;
import data.User;
import ejb.UserBeanLocal;

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
    @EJB UserBeanLocal myUserBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ServletContext context = getServletContext();
        try (PrintWriter out = response.getWriter()){
            request.setCharacterEncoding("UTF-8");
            String name= request.getParameter("name"); //get name
            String email= request.getParameter("email"); //get email

            String raw_country= request.getParameter("country"); //get country
            Country country = new Country("Portugal"); //????
            Date birthdate = Date.valueOf(request.getParameter("birthdate"));

            User user;
            if((user = myUserBean.edit(name,country,email,birthdate)) != null){
                request.getSession().setAttribute("currentSessionUser", user);
                RequestDispatcher rd = context.getRequestDispatcher("/Perfil.jsp");
                rd.forward(request, response);
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Erro ao editar o perfil');");
                out.println("location='Perfil.jsp';");
                out.println("</script>");
                RequestDispatcher rd = context.getRequestDispatcher("/Perfil.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e){
            System.out.println("[REQUEST EDIT ERROR] " + e);
            response.sendRedirect("/Perfil.jsp");
        }
    }

}
