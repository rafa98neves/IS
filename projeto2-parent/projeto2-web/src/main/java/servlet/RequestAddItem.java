package servlet;

import data.Item;
import data.User;
import ejb.ItemBeanLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/RequestAddItem")
public class RequestAddItem extends HttpServlet {

    @EJB
    ItemBeanLocal myItemBean;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()){
            User user = (User) request.getSession().getAttribute("currentSessionUser");
            Item item = new Item();

            //item.setPicture(SaveFile(request, response));
            item.setPicture(request.getParameter("picture"));
            item.setName(request.getParameter("name"));
            item.setPrice(Float.parseFloat(request.getParameter("price")));
            item.setOwner(user);

            java.util.Date utilDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(utilDate.getTime());
            item.setDateOfInsertion(date);

            user.getItems().sort(new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return o1.getDateOfInsertion().compareTo(o2.getDateOfInsertion());
                }
            });
            myItemBean.addItem(item, request.getParameter("country"), request.getParameter("category"), user);
            RequestDispatcher rd = request.getRequestDispatcher("/Meus_items.jsp");
            rd.forward(request, response);
        } catch (Exception e){
            request.setAttribute("alert",e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected String SaveFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int maxFileSize = 50 * 1024;
        int maxMemSize = 4 * 1024;
        // enctype = "multipart/form-data"
        File file ;

        String path = new File(".").getCanonicalPath();
        String filePath = (path+"\\pictures\\");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        if( !isMultipart ) {
            request.setAttribute("alert", "Imagem inválida");
            RequestDispatcher rd = request.getRequestDispatcher("/Meus_items.jsp");
            rd.forward(request, response);
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("c:\\temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax( maxFileSize );

        PrintWriter out = response.getWriter();
        try {
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    String fileName = fi.getName();
                    // Write the file
                    if( fileName.lastIndexOf("\\") >= 0 ) {
                        file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                    } else {
                        file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    fi.write( file ) ;
                }
            }
            return filePath;
        } catch(Exception ex) {
            return "errr";
        }
    }
}
