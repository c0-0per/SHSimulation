package API;

import objects.Car;
import objects.states.WorkingState;

import java.util.logging.Logger;

public class CarAPI {
    private static final Logger LOGGER = Logger.getLogger(CarAPI.class.getName());
    private Car car;

    public CarAPI(Car car) {
        this.car = car;
    }

    public void turnOn(int time) {
        car.turnOn(time);
    }

    public void turnOff() {
        car.turnOff();
    }


    public void fixDevice() {
        LOGGER.info("Fixing car");
        car.fixDevice();
    }
}
