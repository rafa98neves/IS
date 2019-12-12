package project3.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
//import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import project3.data.Country;
import project3.data.Item;
import project3.data.Purchase;
import project3.data.Sale;
import project3.ejb.MyBean;

@Path("/p")
@RequestScoped
public class WebServicesServer {

    @Inject
    MyBean db;

    public WebServicesServer() throws NamingException {
        System.out.println("WebServicesServer created. db = " + this.db);
    }

    // http://localhost:8080/play-REST-server/webapi/p/gettext
    @GET
    @Path("gettext")
    @Produces({MediaType.TEXT_PLAIN})
    public String getText() {
        return "Hello World!";
    }

    // http://localhost:8080/play-REST-server/webapi/project3/getpurchases
    @GET
    @Path("getpurchases")
    @Produces({MediaType.APPLICATION_XML})
    public List<Purchase> getAllPurchases() {
        return db.getListPurchase();
    }

    // http://localhost:8080/play-REST-server/webapi/project3/getsale?id=1
    @GET
    @Path("getsale")
    @Produces({MediaType.APPLICATION_JSON})
    public Sale getSale(@QueryParam("id") int id) {
        return db.getSale(id);
    }

    // http://localhost:8080/play-REST-server/webapi/project3/getsales
    @GET
    @Path("getsales")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sale> getAllSales() {
        return db.getListSales();
    }
}
