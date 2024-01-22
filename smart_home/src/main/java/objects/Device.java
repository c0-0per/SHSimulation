package objects;

import API.consumptyionAPI.ElectricityAPI;
import API.EventAPI;
import events.Event;
import events.DeviceEventType;
import objects.sensors.Sensor;
import objects.states.BrokenState;
import objects.states.ChillingState;
import objects.states.DeviceState;
import reports.EventReportStruct;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Represents a device in a Smart House application.
 * This class provides methods for controlling the device and monitoring its state.
 */
public class Device implements  Sensor {
    protected static final Logger LOGGER = Logger.getLogger(Device.class.getName());
    protected int usageReserve;
    protected int currentUsageReserve;

    public void setCurrentUsageReserve(int currentUsageReserve) {
        this.currentUsageReserve = currentUsageReserve;
    }

    private DeviceState stateOfDevice = new ChillingState();
    private final List<Observer> observers = new ArrayList<>();
    private final ElectricityAPI electricityAPI = new ElectricityAPI();
    private final EventAPI eventAPI = new EventAPI();
    private int kWPerHour;
    public String name;
    private int gasPerHour;
    private int waterPerHour;

    /**
     * Constructor to initialize a device with specified electricity, water, and gas consumption.
     *
     * @param kWPerHour    The electricity consumption per hour in kW.
     * @param waterPerHour The water consumption per hour.
     * @param gasPerHour   The gas consumption per hour.
     */
    public Device(int kWPerHour, int waterPerHour, int gasPerHour) {
        this.kWPerHour = kWPerHour;
        this.waterPerHour = waterPerHour;
        this.gasPerHour = gasPerHour;
    }

    /**
     * Parameterless constructor that initializes the device with default values.
     */
    public Device() {
        this.kWPerHour = 1;
        this.waterPerHour = 0;
        this.gasPerHour = 0;
        this.name = this.getClass().getName();
    }


    public EventAPI getEventAPI() {
        return eventAPI;
    }

    public int getkWPerHour() {
        return kWPerHour;
    }

    public void setkWPerHour(int kWPerHour) {
        this.kWPerHour = kWPerHour;
    }

    public ElectricityAPI getElectricityAPI() {
        return electricityAPI;
    }


    public DeviceState getStateOfDevice() {
        return stateOfDevice;
    }

    public void setStateOfDevice(DeviceState stateOfDevice) {
        this.stateOfDevice = stateOfDevice;
    }

    public int getGasPerHour() {
        return gasPerHour;
    }

    public void setGasPerHour(int gasPerHour) {
        this.gasPerHour = gasPerHour;
    }

    public int getWaterPerHour() {
        return waterPerHour;
    }

    public void setWaterPerHour(int waterPerHour) {
        this.waterPerHour = waterPerHour;
    }


    /**
     * Turns on the device for a specified amount of time.
     * If the device is in a broken state, it will not turn on.
     *
     * @param time The duration for which the device should be turned on.
     */
    public void turnOn(int time) {
        if (this.stateOfDevice instanceof BrokenState) {
            LOGGER.info("Device is broken. Cant use it now. Gonna call someone to fix it");
            notifyAllObservers(new Event(DeviceEventType.BROKEN_DEVICE)); // call somebody to fix
            return;
        }
        currentUsageReserve += time;
        if (currentUsageReserve > usageReserve) {
            this.breakDevice();
            LOGGER.info("Device is broken - explatuation time is over");
            return;
        }
        stateOfDevice.switchOn(this);
        getElectricityAPI().increaseCounter(getkWPerHour() / 60 * time);
    }


    public void turnOff() {
        stateOfDevice.switchOff(this);
    }

    public void breakDevice() {
        stateOfDevice.breakDevice(this);
    }

    public void fixDevice() {
        LOGGER.info("Using documentation");
        stateOfDevice.repairDevice(this);
        currentUsageReserve = 0;
    }


    public void update(Event event, Sensor sensor) {
        this.turnOff();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public int getData() {
        return 0;
    }


    /**
     * Notifies all observers about a potential fault in the device.
     *
     * @param event The event to be notified to the observers.
     */
    @Override
    public void notifyAllObservers(Event event) {
        Sensor sourceSensor = this;
        List<Observer> listeners = new ArrayList<>();

        if (!observers.isEmpty()) {
            observers.get(0).update(event, this);
            listeners.add(observers.get(0));
            getEventAPI().addNewEventReportStruct(new EventReportStruct(event, sourceSensor, listeners));
        } else LOGGER.info("No attached observers");

    }

    public int getusageReserve() {
        return usageReserve;
    }

    public int getUsageReserve() {
        return usageReserve;
    }

    public void setUsageReserve(int usageReserve) {
        this.usageReserve = usageReserve;
    }

    public int getCurrentUsageReserve() {
        return currentUsageReserve;
    }

    public String getName() {
        return name;
    }

}
