package objects;

import API.consumptyionAPI.GasAPI;
import objects.states.ChillingState;

/**
 * The Car class extends the Device class, representing a car in a smart system.
 * It manages gas consumption and includes functionalities specific to a car.
 */
public class Car extends Device {
    private final GasAPI gasAPI = new GasAPI();
    private final int gasPerHour = 10;

    /**
     * Constructs a Car object with default settings.
     * Sets the gas consumption specific to the car and initializes its state.
     */
    public Car() {
        super();
        setGasPerHour(gasPerHour);
        setStateOfDevice(new ChillingState());
    }

    /**
     * Turns on the car for a specified amount of time.
     * Additionally, it calculates and updates the gas consumption during this period.
     *
     * @param time The duration for which the car should be turned on, in minutes.
     */
    @Override
    public void turnOn(int time) {
        super.turnOn(time);
        gasAPI.increaseCounter(getGasPerHour() / 60 * time);
    }

    public void drive(int time) {
        turnOn(time);
    }

    public GasAPI getGasAPI() {
        return gasAPI;
    }

}
