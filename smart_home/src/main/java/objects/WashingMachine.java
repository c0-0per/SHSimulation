package objects;

import API.consumptyionAPI.WaterAPI;

/**
 * The WashingMachine class extends the Device class, representing a washing machine in a smart home environment.
 * It manages water consumption and includes functionalities specific to a washing machine.
 */
public class WashingMachine extends Device {
    private final WaterAPI waterAPI = new WaterAPI();
    private final int waterPerHour = 10;

    /**
     * Constructs a WashingMachine object with default settings.
     * Sets the water consumption specific to the washing machine.
     */
    public WashingMachine() {
        super();
        setWaterPerHour(waterPerHour);
    }

    /**
     * Turns on the washing machine for a specified amount of time.
     * It updates the water consumption during this period.
     *
     * @param time The duration for which the device should be turned on.
     */
    @Override
    public void turnOn(int time) {
        super.turnOn(time);
        waterAPI.increaseCounter(getWaterPerHour() / 60 * time);
    }

    public void washClothes(int time) {
        getWaterAPI().increaseCounter(getWaterPerHour() / 60 * time);
    }

    public WaterAPI getWaterAPI() {
        return waterAPI;
    }
}
