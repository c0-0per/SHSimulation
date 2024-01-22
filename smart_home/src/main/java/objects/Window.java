package objects;


import events.Event;
import events.DeviceEventType;
import objects.sensors.Sensor;

/**
 * The Window class extends the Device class, representing a window in a smart home environment.
 * It can be opened or closed.
 */
public class Window extends Device {
    private boolean isOpened = false;

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public void open() {
        isOpened = true;
    }

    public void close() {
        isOpened = false;
    }

    /**
     * Updates the state of the window in response to an external event.
     * For example, the window will close automatically in response to wind events.
     *
     * @param event The event that triggers the update.
     * @param sensor The sensor detecting the event.
     */
    public void update(Event event, Sensor sensor) {
        if (event.getEventType() == DeviceEventType.WIND) {
            isOpened = false;
            LOGGER.info("Windows are closed due to wind");
        }
    }
}
