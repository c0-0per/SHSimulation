package objects;

import events.Event;
import events.DeviceEventType;
import objects.sensors.Sensor;
import objects.states.BrokenState;
import reports.EventReportStruct;
import java.util.ArrayList;
import java.util.List;

public class Fridge extends Device implements Sensor {
    private final List<Observer> observers = new ArrayList<>();
    private final List<Food> foodInFridge = new ArrayList<>();
    private final int foodLimit = 5;

    /**
     /**
     * Attempts to add a food item into the fridge. It checks if the fridge is functioning and not full.
     * If the fridge is broken or full, it will not add the food and return false.
     *
     * @param food The food item to be added to the fridge.
     * @return boolean indicating whether the food was successfully added or not.
     */
    public boolean addFood(Food food) {
        if (getStateOfDevice() instanceof BrokenState) {
            LOGGER.info("Device is broken. Cant use it now. Gonna call smone to fix it");
            notifyAllObservers(new Event(DeviceEventType.BROKEN_DEVICE)); // call somebody to fix
            return false;
        }
        setCurrentUsageReserve(getCurrentUsageReserve() + 100);
        if (getCurrentUsageReserve() > getusageReserve()) {
            this.breakDevice();
            LOGGER.info("Device is old - broke");
            return false;
        }
        if (foodInFridge.size() < foodLimit) {
            foodInFridge.add(food);
            return true;
        } else {
            LOGGER.info("No place for food in fridge");
            return false;
        }
    }

    public List<Food> getFoodInFridge() {
        return foodInFridge;
    }

    /**
     * Retrieves and removes the first food item from the fridge. If the fridge is empty,
     * it notifies an observer to go shopping.
     *
     * @return fst found food
     */
    public Food getFood() {
        if (foodInFridge.size() == 0) {
            notifyAllObservers(new Event(DeviceEventType.EMPTY_FRIDGE));
            LOGGER.warning("Fridge is empty");
            return new Food("Air");
        }
        Food firstFood = foodInFridge.get(0);
        foodInFridge.remove(firstFood);
        return firstFood;
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
    public void notifyAllObservers(Event event) {
        Sensor sourceSensor = this;
        List<Observer> listeners = new ArrayList<>();

        LOGGER.info(event.getEventType().toString());
        if (observers.size() > 0) {
            observers.get(0).update(event, this);
            listeners.add(observers.get(0));
            getEventAPI().addNewEventReportStruct(new EventReportStruct(event, sourceSensor, listeners));
        } else LOGGER.info("No attached observers in fridge");

    }

}
