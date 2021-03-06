package project3.CLI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import project3.helpers.JsonReader;

import javax.json.Json;
import javax.json.JsonArray;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Admin {

    Scanner scanIn = new Scanner(System.in);
    JsonReader reader = new JsonReader();

    public static void main(String[] args) throws IOException, JSONException {
        Admin admin = new Admin();
        System.out.println("\t\tMAIN MENU");
        admin.MainMenu();
    }

    public void MainMenu() throws IOException, JSONException{
        while(true){
            System.out.println("\t1.Countries\n\t2.Items\n\t3.Operations\n\t0.Exit");
            switch(scanIn.nextInt()){
                case 1:
                    CountriesMenu();
                    break;
                case 2:
                    ItemsMenu();
                    break;
                case 3:
                    OperationsMenu();
                    break;
                case 0:
                    return;
            }
            System.out.println("\n\n\t\tMAIN MENU");
        }
    }

    public void CountriesMenu() throws IOException, JSONException{
        while(true){
            System.out.println("\n\n\t\tCOUNTRIES");
            System.out.println("\t1.List countries\n\t2.Add country\n\t3.Country with the highest sales per item\n\t0.Back");
            switch(scanIn.nextInt()){
                case 1:
                    ListCountries();
                    break;
                case 2:
                    AddCountry();
                    break;
                case 3:
                    HSCountry();
                    break;
                case 0:
                    return;
            }
        }

    }


    public void ItemsMenu() throws IOException, JSONException {
        while(true){
            System.out.println("\n\n\t\tITEMS");
            System.out.println("\t1.Add item\n\t2.List items\n\t3.Revenue per item\n\t4.Expenses per item\n\t5.Profit per item\n\t6.Highest profit item\n\t0.Back");
            switch(scanIn.nextInt()){
                case 1:
                    AddItem();
                    break;
                case 2:
                    GetItem("all");
                    break;
                case 3:
                    GetItem("revenue");
                    break;
                case 4:
                    GetItem("expenses");
                    break;
                case 5:
                    GetItem("profit");
                    break;
                case 6:
                    GetItem("hp");
                    break;
                case 0:
                    return;
            }
        }
    }

    public void OperationsMenu() throws IOException, JSONException{
        while(true){
            System.out.println("\n\n\t\tOPERATIONS");
            System.out.println("1.Total Revenues\n\t2.Total Expenses\n\t3.Total profit\n\t4.Amount spent in each purchase (by item)\n\t5.Amount spent in each purchase (all items)\n\t" +
                    "6.Last hour statistics\n\t0.Back");
            switch(scanIn.nextInt()){
                case 1:
                    GetTotal("revenue");
                    break;
                case 2:
                    GetTotal("expenses");
                    break;
                case 3:
                    GetTotal("profit");
                    break;
                case 4:
                    GetAmountSpent("item");
                    break;
                case 5:
                    GetAmountSpent("all");
                    break;
                case 6:
                    LastHour();
                    break;
                case 0:
                    return;
            }
        }
    }

    /* --------- CountriesMenu ---------- */

    public void ListCountries() throws IOException, JSONException  {
        JSONArray json = reader.readJsonArrayFromUrl("http://localhost:8080/list/countries");
        System.out.println(json);
        System.out.println("0.Back\n");
        scanIn.next();
        return;

    }

    public void AddCountry() throws IOException, JSONException {
        System.out.println("Name: ");
        String name = scanIn.next();
        new URL("http://localhost:8080/add/country?name="+name).openStream();
        System.out.println("0.Back\n");
        scanIn.next();
        return;
    }

    public void HSCountry(){
        System.out.println("NOT DONE\n");
        System.out.println("0.Back\n");
        scanIn.next();
        return;
    }

    /* --------- ItemsMenu ---------- */

    public void AddItem() throws IOException, JSONException {
        System.out.println("Name: ");
        String name = scanIn.next();
        System.out.println("Cost: ");
        String cost = scanIn.next();
        new URL("http://localhost:8080/add/item?name="+name+"&cost="+cost).openStream();
        System.out.println("0.Back\n");
        scanIn.next();
        return;
    }

    public void GetItem(String by) throws IOException, JSONException{
        JSONArray json = new JSONArray();
        switch(by){
            case "all":
                json = reader.readJsonArrayFromUrl("http://localhost:8080/list/items");
                break;
            case "revenue":
                json = reader.readJsonArrayFromUrl("http://localhost:8080/get/item/revenue");
                break;
            case "expenses":
                json = reader.readJsonArrayFromUrl("http://localhost:8080/get/item/expenses");
                break;
            case "profit":
                json = reader.readJsonArrayFromUrl("http://localhost:8080/get/item/profit");
                break;
            case "hp":
                System.out.println("NOT DONE!");
                break;
        }
        if(json != null)
            System.out.println(json);

        System.out.println("0.Back\n");
        scanIn.next();
        return;
    }

    /* --------- OperationsMenu ---------- */

    public void GetTotal(String by) throws IOException, JSONException{
        JSONObject json = new JSONObject();
        switch(by){
            case "revenue":
                json = reader.readJsonObjectFromUrl("http://localhost:8080/get/total/revenue");
                break;
            case "expenses":
                json = reader.readJsonObjectFromUrl("http://localhost:8080/get/total/expenses");
                break;
            case "profit":
                json = reader.readJsonObjectFromUrl("http://localhost:8080/get/total/profit");
                break;
        }
        if(json != null)
            System.out.println(json);
        System.out.println("0.Back\n");
        scanIn.next();
        return;
    }

    public void GetAmountSpent(String by) throws IOException, JSONException{
        switch(by) {
            case "item":
                JSONArray json1 = reader.readJsonArrayFromUrl("http://localhost:8080/get/item/average");
                System.out.println(json1);
                break;
            case "all":
                JSONObject json = reader.readJsonObjectFromUrl("http://localhost:8080/total/average");
                System.out.println(json);
                break;
        }

        System.out.println("0.Back\n");
        scanIn.next();
        return;
    }

    public void LastHour() throws IOException, JSONException{
        JSONObject json = reader.readJsonObjectFromUrl("http://localhost:8080/lastHour");
        if(json != null)
            System.out.println(json);

        System.out.println("0.Back\n");
        scanIn.next();
        return;
    }
}
