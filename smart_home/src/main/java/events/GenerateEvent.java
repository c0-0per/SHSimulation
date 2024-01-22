package events;

import house.Floor;
import house.House;
import house.Room;
import objects.Device;
import objects.sensors.FireSensor;
import objects.sensors.Sensor;
import objects.sensors.WindSensor;

import java.util.List;
import java.util.Random;

public class GenerateEvent {
    House house;

    public GenerateEvent(House house) {
        this.house = house;
    }


    public void generateFireEvent() {
        Sensor sensor = house.getRoomByNumber(0).getSensors().get(0);
        Event event = new Event(DeviceEventType.FIRE);

        sensor.notifyAllObservers(event);
    }

    public void generateWindEvent() {
        Sensor sensor = house.getRoomByNumber(0).getSensors().get(1);
        Event event = new Event(DeviceEventType.WIND);

        sensor.notifyAllObservers(event);
    }

    public void generateBrokenDeviceEvent() {
        Random random = new Random();
        int roomIndex = random.nextInt(house.getNumberOfRooms());
        int deviceIndex = random.nextInt(house.getRoomByNumber(roomIndex).getDevices().size());
        Device device = house.getRoomByNumber(roomIndex).getDevices().get(deviceIndex);

        Event event = new Event(DeviceEventType.BROKEN_DEVICE);

        device.breakDevice();
        device.notifyAllObservers(event);
    }

    public static void simulateRandomEvent(House house) {
        Random random = new Random();
        int randNum = random.nextInt(2);
        switch (randNum) {
            case 0 -> simulateFireEvent(house);
            case 1 -> simulateStrongWindEvent(house);
        }
    }

    public static void simulateStrongWindEvent(House house) {
        Event event = new Event(DeviceEventType.WIND);
        for (Room room : getRandomStorey(house.getFloors()).getRooms()) {
            for (Sensor s : room.getSensors()) {
                if (s instanceof WindSensor) s.notifyAllObservers(event);
            }
        }
    }

    public static void simulateFireEvent(House house) {
        Event event = new Event(DeviceEventType.FIRE);
        for (Room room : getRandomStorey(house.getFloors()).getRooms()) {
            for (Sensor s : room.getSensors()) {
                if (s instanceof FireSensor) s.notifyAllObservers(event);
            }
        }
    }

    private static Floor getRandomStorey(List<Floor> storeys) {
        Random random = new Random();
        int randStoreyIndex = random.nextInt(storeys.size());
        return storeys.get(randStoreyIndex);
    }
}
