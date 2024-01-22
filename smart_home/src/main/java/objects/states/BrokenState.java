package objects.states;

import objects.Device;
import java.util.logging.Logger;

/**
 * Represents the broken state of a device.
 * In this state, the device is non-functional and cannot perform its normal operations.
 */
public class BrokenState implements DeviceState {
    private static final Logger LOGGER = Logger.getLogger(BrokenState.class.getName());

    public BrokenState() {}

    /**
     * Logs a message indicating that the device is broken when an attempt is made to switch it on.
     *
     * @param device The device that is attempted to be switched on.
     */
    @Override
    public void switchOn(Device device) {
        LOGGER.info("Device is broken");
    }

    /**
     * Logs a message indicating that the device is broken when an attempt is made to switch it off.
     *
     * @param device The device that is attempted to be switched off.
     */
    @Override
    public void switchOff(Device device) {
        LOGGER.info("Device is broken");
    }

    /**
     * Logs a message indicating that the device is already broken when an attempt is made to break it.
     *
     * @param device The device that is attempted to be broken.
     */
    @Override
    public void breakDevice(Device device) {
        LOGGER.info("Device is broken");
    }

    /**
     * Transitions the device from a broken state to a 'ChillingState', indicating a repair has been made.
     *
     * @param device The device that is being repaired.
     */
    @Override
    public void repairDevice(Device device) {
        device.setStateOfDevice(new ChillingState());
    }
}
