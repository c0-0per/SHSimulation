import builder.RandomHouseGenerator;
import simulation.Simulator;
import builder.HouseGeneratorBuilder;
import house.House;

public class SmartHouseApp {
    public static void main(String[] args) {
        HouseGeneratorBuilder houseGenerator = new RandomHouseGenerator(1,2,6);
        House house = houseGenerator.generateHouse();

        Simulator simulator = new Simulator(house);
        simulator.startSimulation(50);
    }
}
