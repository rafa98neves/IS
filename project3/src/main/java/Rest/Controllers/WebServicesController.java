package Rest.Controllers;

import java.util.List;

import Rest.Repositories.*;
import Rest.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebServicesController {

    @Autowired
    private RT1Repository RT1Repo;
    @Autowired
    private RT2Repository RT2Repo;
    @Autowired
    private RT3Repository RT3Repo;

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

    @RequestMapping("/get/item/revenue")
    public List<RTopic1> getRevenue() {
        return RT1Repo.findAll();
    }

    @RequestMapping("/get/item/expenses")
    public List<RTopic1> getExpenses() {
        return RT1Repo.findExpenses();
    }

    @RequestMapping("/get/item/profit")
    public List<RTopic1> getProfit() {
        return RT1Repo.findProfit();
    }

    @RequestMapping("/get/item/average")
    public List<RTopic1> getAverage() {
        return RT1Repo.findAverage();
    }

    @RequestMapping("/get/total/revenue")
    public RTopic2 getTotalRevenue() {
        return RT2Repo.findRevenues();
    }

    @RequestMapping("/get/total/expenses")
    public RTopic2 getTotalExpenses() {
        return RT2Repo.findExpenses();
    }

    @RequestMapping("/get/total/profit")
    public RTopic2 getTotalProfit() {
        return RT2Repo.findProfit();
    }

    @RequestMapping("/lastHour")
    public RTopic3 getStatus() {
        return RT3Repo.find();
    }
}
