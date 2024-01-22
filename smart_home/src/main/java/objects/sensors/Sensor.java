package objects.sensors;


import events.Event;
import objects.Observer;

/**
 * The Sensor interface represents a sensor in a smart home system.
 * It provides functionalities to attach or detach observers (such as devices or controllers)
 * and to notify them about sensor-specific events.
 */
public interface Sensor {
    /**
     * Attaches an observer to the sensor.
     *
     * @param observer The observer to be attached to the sensor.
     */
    void attach(Observer observer);

    /**
     * Detaches an observer from the sensor.
     *
     * @param observer The observer to be detached from the sensor.
     */
    void detach(Observer observer);

    /**
     * Retrieves data from the sensor.
     *
     * @return An integer representing the sensor's data.
     */
    int getData();

    /**
     * Notifies all attached observers about a specific event.
     *
     * @param event The event that occurred and is to be notified to the observers.
     */
    void notifyAllObservers(Event event);
}
