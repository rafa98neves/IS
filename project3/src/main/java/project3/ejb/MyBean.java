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
        Country Pt = new Country(1,"Portugal");
        countries.add(Pt);

        Item item1 = new Item(1,"bola",1.2f);
        items.add(item1);
        Item item2 = new Item(2,"rato",9f);
        items.add(item2);
        Item item3 = new Item(3,"leite",0.5f);
        items.add(item3);

        Purchase purchase = new Purchase(1,item2,4);
        purchases.add(purchase);

        Sale sale1 = new Sale(1, item2, 2, Pt);
        sales.add(sale1);
        Sale sale2 = new Sale(2, item3, 1, Pt);
        sales.add(sale2);
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

    public boolean addItem(String name, float cost){
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
