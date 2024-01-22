package house;

import objects.Device;
import people.Person;
import pet.Pet;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class House {
    private Address address;
    private int area;
    private List<Floor> floors;
    private List<Pet> pets;
    private int numberOfRooms;


    public House() {
        floors = new ArrayList<>();
        pets = new ArrayList<>();
    }

    /**
     * Retrieves a device by its name from the house.
     *
     * @param name the name of the device to find
     * @return the device with the specified name, or null if not found
     */
    public Device getDeviceByName(String name) {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .flatMap(room -> room.getDevices().stream())
                .filter(device -> device.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves the room containing a specific device.
     *
     * @param device the device to find the room for
     * @return the room containing the device, or null if not found
     */
    public Room getRoomByDevice(Device device) {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getDevices().contains(device))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a room by its number.
     *
     * @param number the number of the room to find
     * @return the room with the specified number, or null if not found
     */
    public Room getRoomByNumber(int number) {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getNumber() == number)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a room by its type.
     *
     * @param roomType the type of room to find
     * @return the room with the specified type, or null if not found
     */
    public Room getRoomByType(RoomType roomType) {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getRoomType() == roomType)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves the room containing a specific person.
     *
     * @param person the person to find the room for
     * @return the room containing the person, or null if not found
     */
    public Room getRoomByPerson(Person person) {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getPeople().contains(person))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a random person from the house.
     *
     * @return a random person, or null if no people are present
     */
    public Person getRandomPerson() {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .flatMap(room -> room.getPeople().stream())
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves a list of all people in the house.
     *
     * @return a list of all people in the house
     */
    public List<Person> getPeople() {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .flatMap(room -> room.getPeople().stream())
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all devices in the house.
     *
     * @return a list of all devices in the house
     */
    public List<Device> getDevices() {
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .flatMap(room -> room.getDevices().stream())
                .collect(Collectors.toList());
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public Address getAddress() {
        return address;
    }

    public int getArea() {
        return area;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public Floor getFloor(int number) {
        return floors.get(number);
    }

    public List<Floor> getFloors() {
        return floors;
    }

}
