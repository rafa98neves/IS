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
        Item item = new Item();
        SaveItem(item, request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void SaveItem(Item item, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int maxFileSize = 100 * 1024;
        int maxMemSize = 10 * 1024;
        File file ;
        String path, country = new String(), category = new String();
        File dir = new File("D:\\Aulas\\MEI 1 ano\\IS\\IS\\projeto2-parent\\projeto2-web\\src\\main\\webapp\\static\\items\\");
        if(dir.exists()){
            //server 1
            path="D:\\Aulas\\MEI 1 ano\\IS\\IS\\projeto2-parent\\projeto2-web\\src\\main\\webapp\\static\\items\\";
        }
        else{
            //server 2
            path="D:\\Users\\joaom\\Documents\\IS\\IS\\projeto2-parent\\projeto2-web\\src\\main\\webapp\\static\\items\\";
        }
        String filePath = (path);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);
        factory.setRepository(new File("c:\\temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax( maxFileSize );

        try {
            List fileItems = upload.parseRequest(request);
            Iterator i = fileItems.iterator();
            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () ) {
                    // Write the file
                    String fileName = fi.getName();
                    String extension = new String();
                    if( fileName.lastIndexOf("\\") >= 0 ) {
                        extension = fileName.substring( fileName.lastIndexOf("\\"));
                    } else {
                        extension = fileName.substring( fileName.lastIndexOf("\\")+1);
                    }
                    try {
                        file = new File(filePath + extension);
                        fi.write( file ) ;
                    }catch (IOException e){
                        //Imagem já existe, então não a guardo
                    }
                    item.setPicture("static/items/" + extension);
                }
                else{
                    if ("name".equals(fi.getFieldName()))
                        item.setName(fi.getString());
                    else if ("price".equals(fi.getFieldName()))
                        item.setPrice(Float.parseFloat(fi.getString()));
                    else if ("country".equals(fi.getFieldName()))
                        country = fi.getString();
                    else if ("category".equals(fi.getFieldName()))
                        category = fi.getString();
                }
            }
        } catch(Exception ex) {
            request.setAttribute("alert", ex);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        item.setDateOfInsertion(date);

        User user = (User) request.getSession().getAttribute("currentSessionUser");
        item.setOwner(user);
        myItemBean.addItem(item, country, category, user);

        RequestDispatcher rd = request.getRequestDispatcher("/Meus_items.jsp");
        rd.forward(request, response);
    }
}
