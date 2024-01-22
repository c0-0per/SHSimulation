package simulation;

import API.FridgeAPI;
import API.TVAPI;
import house.House;
import house.Room;
import house.RoomType;
import objects.Device;
import objects.Food;
import objects.Fridge;
import objects.TV;
import people.Person;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Living {
    House house;
    private static Logger logger = Logger.getLogger(Living.class.getName());

    public Living(House house) {
        this.house = house;
    }

    public void GoToRoom(Room room, Person person) {
        Room src = house.getRoomByPerson(person);
        if (src == room) {
            logger.log(Level.INFO, "{0} is already in {1}", new Object[]{person.getName(), room.getRoomType()});
        }
        logger.log(Level.INFO, "{0} in {1}", new Object[]{person.getName(), room.getRoomType()});
        src.removePerson(person);
        room.addPerson(person);
    }

    public void GoToEat(Person person) {
        Room kithen = house.getRoomByType(RoomType.KITCHEN);
        GoToRoom(kithen, person);
        Optional<Fridge> fridge = kithen.getDevices().stream()
                .filter(Fridge.class::isInstance)
                .map(Fridge.class::cast)
                .findFirst();

        fridge.ifPresent(f -> {
            FridgeAPI fridgeAPI = new FridgeAPI(f);
            if (fridgeAPI.getFood() != null) {
                fridgeAPI.getFood().cookFood();
                logger.log(Level.INFO, "{0} took food from fridge", new Object[]{person.getName()});
                logger.log(Level.INFO, "{0} cooked food", new Object[]{person.getName()});
                logger.log(Level.INFO, "{0} eats", new Object[]{person.getName()});
            } else {
                logger.log(Level.INFO, "{0} is hungry", new Object[]{person.getName()});
                GoToShop(person);
            }
        });
    }

    public void GoToSleep(Person person) {
        Room bedroom = house.getRoomByType(RoomType.BEDROOM);
        GoToRoom(bedroom, person);
        logger.log(Level.INFO, "{0} is sleeping", new Object[]{person.getName()});
    }


    public void GoToWatchTV(Person person) {
        Room livingRoom = house.getRoomByType(RoomType.LIVINGROOM);
        GoToRoom(livingRoom, person);
        Optional<TV> tv = livingRoom.getDevices().stream()
                .filter(TV.class::isInstance)
                .map(TV.class::cast)
                .findFirst();
        tv.ifPresent(device -> {
            TVAPI tvAPI = new TVAPI(device);
            tvAPI.turnOn(2);
            logger.log(Level.INFO, "{0} is watching TV", new Object[]{person.getName()});
        });
    }

    public void GoToShop(Person person) {
        logger.log(Level.INFO, "{0} Ordered groceries online", new Object[]{person.getName()});
        GoToRoom(house.getRoomByType(RoomType.KITCHEN), person);
        Optional<Fridge> fridge = house.getRoomByType(RoomType.KITCHEN).getDevices().stream()
                .filter(Fridge.class::isInstance)
                .map(Fridge.class::cast)
                .findFirst();

        fridge.ifPresent(f -> {
            FridgeAPI fridgeAPI = new FridgeAPI(f);
            fridgeAPI.addFood(new Food("Milk"));
        });
    }


    public void fixDevice(Device device, Person person) {
        Room source = house.getRoomByDevice(device);
        GoToRoom(source, person);

        device.fixDevice();
        logger.log(Level.INFO, "{0} fixed the {1}", new Object[]{person.getName(), device.getName()});
    }
}
