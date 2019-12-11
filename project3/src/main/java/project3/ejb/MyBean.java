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

    public MyBean() {
        Country Pt = new Country(1,"Portugal");
        Item item1 = new Item(1,"bola",1.2f);
        Item item2 = new Item(2,"rato",9f);
        Item item3 = new Item(3,"leite",0.5f);

        Purchase purchase = new Purchase(1,item2,4);
        purchases.add(purchase);

        Sale sale1 = new Sale(1, item2, 2, Pt);
        Sale sale2 = new Sale(2, item3, 1, Pt);
        sales.add(sale1);
        sales.add(sale2);

    }

    public List<Purchase> getListPurchase() {
        return this.purchases;
    }

    public Sale getSale(int id) {
        return sales.get(id);
    }

    public List<Sale> getListSales() {
        return this.sales;
    }
}
