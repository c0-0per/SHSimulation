package API;

import objects.Food;
import objects.Owen;
import objects.states.WorkingState;

import java.util.List;
import java.util.logging.Logger;

public class OwenAPI {
    private static final Logger LOGGER = Logger.getLogger(OwenAPI.class.getName());

    private Owen owen;

    public OwenAPI(Owen owen) {
        this.owen = owen;
    }

    public void turnOn(int time) {
        owen.turnOn(time);
    }

    public void turnOff() {
        owen.turnOff();
    }

    public void bake(List<Food> food) {
        if (owen.getStateOfDevice() instanceof WorkingState) {
            owen.bakeFood(food);
        } else LOGGER.info("device is not turned on");

    }


    public void fixDevice() {
        LOGGER.info("Fixing own");
        owen.fixDevice();
    }
}
