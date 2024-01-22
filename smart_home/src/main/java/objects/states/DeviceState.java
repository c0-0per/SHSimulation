package objects.states;

import objects.Device;

public interface DeviceState {
    void switchOn(Device device);

    void switchOff(Device device);

    void breakDevice(Device device);

    void repairDevice(Device device);
}
