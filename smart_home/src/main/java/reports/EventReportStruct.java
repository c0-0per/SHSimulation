package reports;

import events.Event;
import objects.Observer;
import objects.sensors.Sensor;

import java.util.List;

/**
 * Represents a structured report of an event, including the event itself, its source sensor, and a list of observers.
 * This class is designed to encapsulate the details of an event, including where it originated and who is listening for it.
 * It is useful for logging, monitoring, or handling events in a system that uses the Observer pattern.
 */
public class EventReportStruct {
    private final Event event;
    private final Sensor sourceSensor;
    private final List<Observer> listeners;

    /**
     * Constructs a new EventReportStruct with the specified event, source sensor, and list of listeners (observers).
     *
     * @param event        the event that occurred
     * @param sourceSensor the sensor that detected or generated the event
     * @param listeners    the list of observers that are listening for the event
     */
    public EventReportStruct(Event event, Sensor sourceSensor, List<Observer> listeners) {
        this.event = event;
        this.sourceSensor = sourceSensor;
        this.listeners = listeners;
    }

    public Event getEventType() {
        return event;
    }

    public Sensor getSourceSensor() {
        return sourceSensor;
    }

    public List<Observer> getListeners() {
        return listeners;
    }
}
