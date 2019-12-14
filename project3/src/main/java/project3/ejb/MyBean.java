package project3.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import project3.data.Country;
import project3.data.Item;
import project3.data.Purchase;
import project3.data.Sale;
import java.util.List;


@Stateless
@LocalBean
public class MyBean {

    List<Purchase> purchases;
    List<Sale> sales;
    List<Country> countries;
    List<Item> items;

    public MyBean() {
    }

    /* -- Actions on countries --*/
    public boolean addCountry(String name){
        //add country to db

        Country new_country = new Country(0,name);
        return true;
    }

    public List<Country> getListCountries(){
        //get countries from db
        return countries;
    }

    public String getCountryHS(){
        //get countriy with highest sales per item
        String response = "Pais x - Item x com sales x";
        return response;
    }


    /* -- Actions on items --*/

    public List<Item> getListItems(){
        return items;
    }

    public boolean addItem(String name, int cost){
        //add item to db

        Item new_item = new Item(0,name,cost);
        return true;
    }


    /* -- Actions on purchases --*/

    public List<Purchase> getListPurchase() {
        return this.purchases;
    }

    public Purchase getPurchase(int id) {
        return purchases.get(id);
    }

    public Purchase getHPPurchase(){
        //get highest profit purchase on db

        return null;
    }


    /* -- Actions on sales --*/

    public List<Sale> getListSales() {
        return this.sales;
    }

    public Sale getSale(int id) {
        return sales.get(id);
    }


    /*-- Last hour status --*/

    public String getStatus(){
        // get last hour status from streams
        // convert it to string
        return "";
    }
}
