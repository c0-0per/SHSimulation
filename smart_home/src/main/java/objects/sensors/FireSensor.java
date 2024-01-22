package objects.sensors;

import events.Event;
import objects.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The FireSensor class implements the Sensor interface and simulates a sensor that detects fire.
 * It notifies its observers, typically devices or controllers, about potential fire hazards.
 */
public class FireSensor implements Sensor {
    private static final Logger LOGGER = Logger.getLogger(FireSensor.class.getName());

    private List<Observer> observers = new ArrayList<>();

    /**
     * Attaches an observer to this sensor.
     *
     * @param observer The observer to be attached to this sensor.
     */
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * Detaches an observer from this sensor.
     *
     * @param observer The observer to be detached from this sensor.
     */
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Generates and retrieves simulated data for temperature.
     *
     * @return An integer representing the simulated temperature, potentially indicating a fire hazard.
     */
    @Override
    public int getData() {
        Random random = new Random();
        int temp = random.nextInt(100);
        return temp;
    }

    /**
     * Notifies all attached observers about a specific fire event.
     *
     * @param event The fire event that occurred and is to be notified to the observers.
     */
    @Override
    public void notifyAllObservers(Event event) {
        List<Observer> listeners = new ArrayList<>();
        for (Observer observer : observers) {
            LOGGER.info("FIRE");
            observer.update(event, this);
            listeners.add(observer);
        }
    }

}
