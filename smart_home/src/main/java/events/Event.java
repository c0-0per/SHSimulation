package events;

public class Event {
    private final DeviceEventType deviceEventType;

    public Event(DeviceEventType deviceEventType) {
        this.deviceEventType = deviceEventType;
    }

    public DeviceEventType getEventType() {
        return deviceEventType;
    }
}
