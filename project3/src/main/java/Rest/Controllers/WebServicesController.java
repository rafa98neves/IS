package Rest.Controllers;

import java.util.List;

import Rest.Repositories.CountryRepository;
import Rest.Repositories.ItemRepository;
import Rest.data.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import Rest.data.Country;


@RestController
public class WebServicesController {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private CountryRepository countryRepository;

    public WebServicesController(){
    }


    @RequestMapping("/add/country")
    public String addCountry(@RequestParam(value="name") String name) {
        Country c = new Country();
        c.setCountry_name(name);
        if(countryRepository.save(c) != null){
            return "Country added succefully!";
        }
        else{
            return "Country couldn't be added";
        }
    }

    @RequestMapping("/list/countries")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

   /*
    @RequestMapping("/country/hs")
    public String getCountryHS() {
        // #######################################################  NOT DONE
        return "";
    }
    */


    @RequestMapping("/list/items")
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @RequestMapping("/add/item")
    public String addItem(@RequestParam("name") String name, @RequestParam("cost") String cost) {
        Item i = new Item();
        i.setItem_name(name);
        i.setItem_price(Float.parseFloat(cost));
        if(itemRepo.save(i) != null){
            return "Item added for sale succefully!";
        }
        else{
            return "Item couldn't be added for sale";
        }
    }

    /*

    @RequestMapping("/get/item/revenue")
    public List<Item> getRevenue() {
        // #######################################################  NOT DONE
        return db.getListItems();
    }

    @RequestMapping("/get/item/expenses")
    public List<Item> getExpenses() {
        // #######################################################  NOT DONE
        return db.getListItems();
    }

    @RequestMapping("/get/item/profit")
    public List<Item> getProfit() {
        // #######################################################  NOT DONE
        return db.getListItems();
    }

    @RequestMapping("/get/total/revenue")
    public List<Item> getTotalRevenue() {
        // #######################################################  NOT DONE
        return db.getListItems();
    }


    @RequestMapping("/get/total/expenses")
    public List<Item> getTotalExpenses() {
        // #######################################################  NOT DONE
        return db.getListItems();
    }

    @RequestMapping("/get/total/profit")
    public List<Item> getTotalProfit() {
        // #######################################################  NOT DONE
        return db.getListItems();
    }

    @RequestMapping("/get/amount/item")
    public List<Item> getAmountSpentItem() {
        // #######################################################  NOT DONE
        return db.getListItems();
    }

    @RequestMapping("/get/amount/all")
    public List<Item> getAmountSpent() {
        // #######################################################  NOT DONE
        return db.getListItems();
    }

    @RequestMapping("/lastHour")
    public String getStatus() {
        // #######################################################  NOT DONE
        return db.getStatus();
    }*/
}
