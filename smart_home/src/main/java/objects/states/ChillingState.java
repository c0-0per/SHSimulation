package objects.states;

import objects.Device;
import java.util.logging.Logger;

/**
 * Represents the chilling state of a device.
 * In this state, the device is not actively working but is ready to be switched on.
 */
public class ChillingState implements DeviceState {
    private static final Logger LOGGER = Logger.getLogger(ChillingState.class.getName());

    public ChillingState() {}

    /**
     * Transitions the device from the chilling state to a 'WorkingState', indicating it is being activated.
     *
     * @param device The device that is being switched on.
     */
    @Override
    public void switchOn(Device device) {
        device.setStateOfDevice(new WorkingState());
    }

    /**
     * Logs a message indicating that the device is already in a low-power state when an attempt is made to switch it off.
     *
     * @param device The device that is attempted to be switched off.
     */
    @Override
    public void switchOff(Device device) {
        LOGGER.info("Device is turned off already");
    }

    /**
     * Transitions the device to a 'BrokenState', indicating that it has malfunctioned or is damaged.
     *
     * @param device The device that is breaking.
     */
    @Override
    public void breakDevice(Device device) {
        device.setStateOfDevice(new BrokenState());
    }

    /**
     * Logs a message indicating that the device is not in need of repair, as it is not broken.
     *
     * @param device The device that is attempted to be repaired.
     */
    @Override
    public void repairDevice(Device device) {
        LOGGER.info("Device is not broken");
    }
}
