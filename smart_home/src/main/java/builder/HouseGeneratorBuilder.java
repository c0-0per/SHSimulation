package builder;

import config.HouseConfig;
import house.House;

public interface HouseGeneratorBuilder {
    House house = new House();

    public House generateHouse();

    public void generateFloor();

    public void generatePeople();

    public void generatePets();

    public void generateDevices();

    public House getHouse();

}
