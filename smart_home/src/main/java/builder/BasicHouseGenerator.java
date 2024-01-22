package builder;

import com.google.gson.JsonObject;
import config.HouseConfig;
import house.*;
import objects.Fridge;
import objects.TV;
import objects.WashingMachine;
import objects.sensors.FireSensor;
import objects.sensors.WindSensor;
import people.Person;
import people.PersonGender;
import pet.PetType;

import java.util.List;
import java.util.Random;

public class BasicHouseGenerator implements HouseGeneratorBuilder {
    private String street;
    private String city;
    private String state;
    private String country;

    public BasicHouseGenerator(String street, String city, String state, String country){
        this.state = state;
        this.street = street;
        this.country = country;
        this.city = city;
    }
    @Override
    public House generateHouse() {
        house.setAddress(new Address(street, city, state, country));
        generateFloor();
        generateDevices();
        generatePeople();
        generatePets();
        generatePeople();
        return house;
    }


    @Override
    public void generateFloor() {
        int floorNumber = house.getFloors().size() + 1;
        Floor floor = new Floor(floorNumber);
        floor.addRoom(new Room(1, RoomType.BATHROOM));
        floor.addRoom(new Room(2, RoomType.BEDROOM));
        floor.addRoom(new Room(3, RoomType.KITCHEN));
        floor.addRoom(new Room(4, RoomType.LIVINGROOM));
        floor.addRoom(new Room(6, RoomType.LIVINGROOM));
        floor.addRoom(new Room(7, RoomType.LIVINGROOM));

        house.addFloor(floor);
    }

    @Override
    public void generatePeople() {
        int numberOfFloors = house.getFloors().size();
        int floorNumber = new Random().nextInt(numberOfFloors - 1);
        int roomNumber = new Random().nextInt(house.getFloor(floorNumber).getRooms().size());
        house.getFloor(floorNumber).getRoom(roomNumber).addPerson(new Person("Petr", "Novak", PersonGender.BIGENDER, 20));
        house.getFloor(floorNumber).getRoom(roomNumber).addPerson(new Person("Rayan", "Gosling", PersonGender.TRANSGENDER, 40));
        house.getFloor(floorNumber).getRoom(roomNumber).addPerson(new Person("Mary", "Brown", PersonGender.FEMALE, 14));
    }

    @Override
    public void generatePets() {
        house.addPet(new pet.Pet("dog", 5, "dog", PetType.Dog));
    }

    @Override
    public void generateDevices() {
        house.getFloor(1).getRoom(1).addDevice(new WashingMachine());
        house.getFloor(1).getRoom(2).addDevice(new TV());
        house.getFloor(1).getRoom(4).addDevice(new Fridge());
        house.getFloor(1).getRoom(0).addSensor(new FireSensor());
        house.getFloor(1).getRoom(0).addSensor(new WindSensor());

    }

    @Override
    public House getHouse() {
        return house;
    }
}
