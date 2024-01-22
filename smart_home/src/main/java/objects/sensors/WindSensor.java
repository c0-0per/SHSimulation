package objects.sensors;

import events.Event;
import objects.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The WindSensor class implements the Sensor interface and simulates a sensor that detects wind speed.
 * It notifies its observers, typically devices or controllers, about changes in wind conditions.
 */
public class WindSensor implements Sensor {
    private static final Logger LOGGER = Logger.getLogger(WindSensor.class.getName());

    List<Observer> observers = new ArrayList<>();

    /**
     * Attaches an observer to this sensor.
     *
     * @param observer The observer to be attached to this sensor.
     */
    @Override
    public void attach(Observer observer) {observers.add(observer);}

    /**
     * Detaches an observer from this sensor.
     *
     * @param observer The observer to be detached from this sensor.
     */
    @Override
    public void detach(Observer observer) {observers.remove(observer);}

    /**
     * Generates and retrieves simulated data for wind speed.
     *
     * @return An integer representing the simulated wind speed.
     */
    @Override
    public int getData() {
        Random random = new Random();
        int speed = random.nextInt(30);
        return speed;
    }

    /**
     * Notifies all attached observers about a specific wind event.
     *
     * @param event The wind event that occurred and is to be notified to the observers.
     */
    public void notifyAllObservers(Event event) {
        List<Observer> listeners = new ArrayList<>();
        for (Observer observer : observers) {
            LOGGER.info("Now is strong wind outside");
            observer.update(event, this);
            listeners.add(observer);
        }
    }


}
