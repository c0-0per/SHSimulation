package objects.states;

import objects.Device;

import java.util.logging.Logger;

/**
 * Represents the working state of a device.
 * In this state, the device is functioning normally.
 */
public class WorkingState implements DeviceState {
    private static final Logger LOGGER = Logger.getLogger(WorkingState.class.getName());

    /**
     * Does nothing since the device is already in the working state.
     * Logs an informational message.
     *
     * @param device The device for which the state is being switched on.
     */
    @Override
    public void switchOn(Device device) {
        LOGGER.info("Device is turned off already");
    }

    /**
     * Switches the state of the device to 'ChillingState'.
     *
     * @param device The device for which the state is being switched off.
     */
    @Override
    public void switchOff(Device device) {
        device.setStateOfDevice(new ChillingState());
    }

    /**
     * Transitions the device to a 'BrokenState', indicating it is no longer functioning properly.
     *
     * @param device The device that is breaking.
     */
    @Override
    public void breakDevice(Device device) {
        device.setStateOfDevice(new BrokenState());
    }

    /**
     * Logs an informational message as the device is already in a working state and doesn't need repair.
     *
     * @param device The device that is being attempted to repair.
     */
    @Override
    public void repairDevice(Device device) {
        LOGGER.info("Device is not broken");
    }
}
