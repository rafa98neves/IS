package org;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Repository {

    private ArrayList<Owner> owners;
    private ArrayList<Car> cars;

    public Repository(){
        Car car1 =  new Car(1,"Opel","Corsa",1200,75,7.5f, "00-01-AA");
        Car car2 =  new Car(2,"Audi","A5",1200,75,7.5f, "00-02-AA");
        Car car3 =  new Car(3,"Opel","Zafira",1200,75,7.5f, "00-03-AA");
        Car car4 =  new Car(4,"Carro","da Mãe",1200,75,7.5f, "00-04-AA");


        Owner owner1 =  new Owner(1,"Rafael",969999123,"Rua de Santa Rita, Castelo");
        Owner owner2 =  new Owner(1,"João",969922123,"Vila de Rei");

        owner1.addCar(car1);
        owner1.addCar(car2);
        owner2.addCar(car3);
        owner2.addCar(car4);

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);

        owners.add(owner1);
        owners.add(owner2);
    }

    public ArrayList<Owner> getOwners(){
        return owners;
    }
    public ArrayList<Car> getCars(){
        return cars;
    }
}
