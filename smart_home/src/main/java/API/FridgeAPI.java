package API;


import objects.Device;
import objects.Food;
import objects.Fridge;

import java.util.logging.Logger;

public class FridgeAPI {
    private static final Logger LOGGER = Logger.getLogger(FridgeAPI.class.getName());

    private Fridge fridge;

    public FridgeAPI(Fridge fridge) {
        this.fridge = fridge;
    }

    public void turnOn(int time) {
        fridge.turnOn(time);
    }

    public void turnOff() {
        fridge.turnOff();
    }

    public Food getFood() {
        return fridge.getFood();
    }

    public boolean addFood(Food food) {
        return fridge.addFood(food);
    }


    public void fixDevice() {
        LOGGER.info("Fixing fridge");
        fridge.fixDevice();
    }

    public void removeFoodByPerson(Food food) {
        fridge.getFoodInFridge().remove(food);
    }
}
