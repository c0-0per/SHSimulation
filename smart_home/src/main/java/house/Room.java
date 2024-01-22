package house;


import objects.Device;
import objects.sensors.Sensor;
import people.Person;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int number;
    private RoomType roomType;

    private List<Person> people;
    private List<Device> devices;
    private List<Sensor> sensors;

    /**
     * Constructs a new Room with the specified number and type.
     * Initializes empty lists for people, devices, and sensors.
     *
     * @param number   the number of the room
     * @param roomType the type of the room (e.g., Bedroom, Kitchen)
     */
    public Room(int number, RoomType roomType) {
        this.number = number;
        this.roomType = roomType;
        people = new ArrayList<>();
        devices = new ArrayList<>();
        sensors = new ArrayList<>();
    }


    public List<Sensor> getSensors() {
        return sensors;
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void removePerson(Person person) {
        people.remove(person);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }

    public int getNumber() {
        return number;
    }

}
