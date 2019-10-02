package org;

import org.grpcFA.Car;
import org.grpcFA.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Repository {

    private List<Owner> owners;
    private List<Car> cars;

    public Repository(){
        Car car1 = Car.newBuilder()
                .setId(1)
                .setBrand("Opel")
                .setModel("Corsa")
                .setEngineSize(1200)
                .setPower(75)
                .setConsumption(7.5f)
                .setPlate("00-01-AA").
                build();

        Car car2 = Car.newBuilder()
                .setId(2)
                .setBrand("Audi")
                .setModel("A5")
                .setEngineSize(1200)
                .setPower(75)
                .setConsumption(7.5f)
                .setPlate("00-02-AA")
                .build();
        Car car3 = Car.newBuilder()
                .setId(3)
                .setBrand("Opel")
                .setModel("Zafira")
                .setEngineSize(1200)
                .setPower(75)
                .setConsumption(7.5f)
                .setPlate("00-03-AA")
                .build();
        Car car4 = Car.newBuilder()
                .setId(4)
                .setBrand("Carro")
                .setModel("da Mãe")
                .setEngineSize(1200)
                .setPower(75)
                .setConsumption(7.5f)
                .setPlate("00-04-AA")
                .build();

        Owner owner1 = Owner.newBuilder()
                .setId(1)
                .setAddress("Rua de Santa Rita, Castelo")
                .setTelephone(969999123)
                .setName("Rafael")
                .setCarId(0,1)
                .setCarId(1,2)
                .build();
        Owner owner2 = Owner.newBuilder()
                .setId(2)
                .setAddress("Vila de Rei")
                .setTelephone(969922123)
                .setName("João")
                .setCarId(0,3)
                .setCarId(1,4)
                .build();

        owners = Arrays.asList(owner1, owner2);
        cars = Arrays.asList(car1,car2,car3,car4);
    }

    public List<Owner> getOwners(){
        return owners;
    }
    public List<Car> getCars(){
        return cars;
    }
}
