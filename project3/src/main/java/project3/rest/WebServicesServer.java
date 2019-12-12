package project3.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
//import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.inject.Inject;
import javax.ws.rs.*;
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

    // http://localhost:8080/project3/webapi/p/gettext
    @GET
    @Path("gettext")
    @Produces({MediaType.TEXT_PLAIN})
    public String getText() {
        return "Hello World!";
    }




    /* -- Actions on countries --*/

    @POST
    @Path("addcountry")
    @Produces({MediaType.TEXT_PLAIN})
    public String addCountry(@QueryParam("country") String name) {
        if (db.addCountry(name)){
            return "Country added succefully!";
        }
        else{
            return "Country couldn't be added!";
        }
    }

    @GET
    @Path("getcountries")
    @Produces({MediaType.APPLICATION_XML})
    public List<Country> getAllCountries() {
        return db.getListCountries();
    }

    @GET
    @Path("getcountrie/HS")
    @Produces({MediaType.TEXT_PLAIN})
    public String getCountryHS() {
        return db.getCountryHS();
    }

    /* -- Actions on items -- */

    @GET
    @Path("getitems")
    @Produces({MediaType.APPLICATION_XML})
    public List<Item> getAllItems() {
        return db.getListItems();
    }

    @POST
    @Path("additem")
    @Produces({MediaType.TEXT_PLAIN})
    public String addSale(@QueryParam("name") String name, @QueryParam("cost") float cost) {
        if(db.addItem(name,cost)){
            return "Item added for sale succefully!";
        }
        else{
            return "Item couldn't be added for sale";
        }
    }


    /* -- Actions on purchases --*/

    @GET
    @Path("getpurchase/hp")
    @Produces({MediaType.APPLICATION_JSON})
    public Purchase getHPPurchase() {
        return db.getHPPurchase();
    }

    @GET
    @Path("getpurchases")
    @Produces({MediaType.APPLICATION_XML})
    public List<Purchase> getAllPurchases() {
        return db.getListPurchase();
    }

    @GET
    @Path("getpurchase")
    @Produces({MediaType.APPLICATION_JSON})
    public Purchase getPurchase(@QueryParam("id") int id) {
        return db.getPurchase(id);
    }


    /* -- Actions on sales --*/

    @GET
    @Path("getsale")
    @Produces({MediaType.APPLICATION_JSON})
    public Sale getSale(@QueryParam("id") int id) {
        return db.getSale(id);
    }

    @GET
    @Path("getsales")
    @Produces({MediaType.APPLICATION_XML})
    public List<Sale> getAllSales() {
        return db.getListSales();
    }

    /*-- Last hour status --*/

    @GET
    @Path("status")
    @Produces({MediaType.TEXT_PLAIN})
    public String getStatus() {
        return db.getStatus();
    }
}
