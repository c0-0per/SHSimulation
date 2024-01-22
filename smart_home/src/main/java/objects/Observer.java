package objects;

import events.Event;
import objects.sensors.Sensor;

public interface Observer {
    /**
     * Called to update the observer about an event that occurred in the observed subject.
     * The method is typically invoked when a change in the state of an observed object is detected.
     *
     * @param event The event that occurred, carrying information about the change.
     * @param sensor The sensor that is the source of the event.
     */
    void update(Event event, Sensor sensor);
}
