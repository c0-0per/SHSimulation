package API;


import objects.WashingMachine;
import objects.states.WorkingState;
import java.util.logging.Logger;


public class WashingMachineAPI {
    private static final Logger LOGGER = Logger.getLogger(WashingMachineAPI.class.getName());

    private WashingMachine washingMachine;

    public WashingMachineAPI(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    public void turnOn(int time) {
        washingMachine.turnOn(time);
    }

    public void turnOff() {
        washingMachine.turnOff();
    }

    public void washClothes(int time) {
        if (washingMachine.getStateOfDevice() instanceof WorkingState) {
            washingMachine.washClothes(time);
        } else LOGGER.info("device is not turned on");
    }

    public void fixDevice() {
        LOGGER.info("Fixing washing machine");
        washingMachine.fixDevice();

    }

}
