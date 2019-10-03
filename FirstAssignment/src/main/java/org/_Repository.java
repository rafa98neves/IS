package org;

import com.google.protobuf.Descriptors;
import org.grpcFA.Car;
import org.grpcFA.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

import static org.grpcFA.Owner.newBuilder;

public class _Repository {

    private List<Owner> owners;
    private List<Car> cars;
    private OwnerListXML ownersXML;
    private OwnershipXML carsXML;

    public _Repository(){
        ownersXML = new OwnerListXML();
        carsXML = new OwnershipXML();
        owners = new ArrayList<Owner>();
        cars = new ArrayList<Car>();
    }

    public void GenerateOwners(int numberOwners){
        for(int i=1; i<=numberOwners; i++){
            Owner owner = newBuilder()
                    .setId(i)
                    .setAddress("Somewhere")
                    .setTelephone(99999999)
                    .setName("Bot " + i)
                    .build();
            owners.add(owner);
        }
    }

    public void GenerateCars(int numberOwners, int numberCars){
        Random r = new Random();
        for(int i=1; i<=numberCars; i++){
            Car car = Car.newBuilder().
                    setId(i)
                    .setOwnerId(r.nextInt((numberOwners - 1) + 1) + 1)
                    .setBrand("SomeBrand")
                    .setModel("SomeModel")
                    .setConsumption(7.5f)
                    .setEngineSize(1000)
                    .setPower(75)
                    .setConsumption(7.5f)
                    .setPlate("00-"+i+"-AA")
                    .build();
            cars.add(car);
        }
    }

    public void GenerateOwnersXML(int numberOwners){
        for(int i=1; i<=numberOwners; i++){
            OwnerXML owner = new OwnerXML(i,"Bot" + i,99999999, "Somewhere",null);
            ownersXML.add(owner);
        }
    }

    public void GenerateCarsXML(int numberOwners, int numberCars){
        Random r = new Random();
        for(int i=1; i<=numberCars; i++){
            CarXML car = new CarXML(i,"Bot",""+i, 1200,75,7.5f,
                    "00-"+i+"-AA",r.nextInt((numberOwners - 1) + 1) + 1);
            carsXML.add(car);
        }
    }

    public List<Owner> getOwners(){
        return owners;
    }
    public List<Car> getCars(){
        return cars;
    }
    public OwnershipXML getCarsXML(){
        return carsXML;
    }
    public OwnerListXML getOwnersXML(){
        return ownersXML;
    }
}
