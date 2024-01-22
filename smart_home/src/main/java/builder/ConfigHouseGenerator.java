package builder;

import com.google.gson.JsonObject;
import config.HouseConfig;
import house.*;
import objects.*;
import objects.Observer;
import objects.sensors.FireSensor;
import objects.sensors.WindSensor;
import people.Person;
import people.PersonGender;
import pet.PetType;

import java.util.*;
import java.util.function.Supplier;

public class ConfigHouseGenerator implements HouseGeneratorBuilder{


    private List<String> names;
    private List<String> Pets;
    private int numberOfFloors;
    private int numberOfRoomsOnFloor;
    private int numberOfPeople;

    private String[] surnames = {"Smith", "Jones", "Brown", "Williams", "Johnson"};
    private String[] Genders = {"male", "female"};
    private String[] streets = {"Main St", "Elm St", "Maple Ave", "Oak St"};
    private String[] cities = {"Springfield", "Rivertown", "Laketown"};
    private String[] states = {"NY", "CA", "IL"};
    private String[] countries = {"USA", "Canada"};


    private String configFilePath;
    HouseConfig houseConfig;

    public ConfigHouseGenerator(String configFilePath){
        this.configFilePath = configFilePath;
        houseConfig = new HouseConfig(house);
    }
    @Override
    public House generateHouse() {
        JsonObject jsonObject = houseConfig.loadConfig(configFilePath);
        String address = jsonObject.get("address").getAsString();
        int area = jsonObject.get("area").getAsInt();
        int numberOfRooms = jsonObject.get("numberOfRooms").getAsInt();
        names = houseConfig.convertJsonArrayToList(jsonObject.getAsJsonArray("people"));
        Pets = houseConfig.convertJsonArrayToList(jsonObject.getAsJsonArray("pets"));

        String[] parts = address.split(",\\s*");

        String street = parts[0];
        String city = parts[1];
        String state = parts[2];
        String country = parts[3];
        house.setAddress(new Address(street, city, state, country));

        return house;
    }

    @Override
    public void generateFloor() {
        int numberOfRooms = 0;
        for (int i = 0; i < numberOfFloors; i++) {
            int floorNumber = house.getFloors().size() + 1;
            Floor floor = new Floor(floorNumber);
            for (int j = 0; j < numberOfRoomsOnFloor; j++) {
                RoomType roomType = RoomType.values()[new Random().nextInt(RoomType.values().length)];
                floor.addRoom(new Room(numberOfRooms, roomType));
                numberOfRooms++;
            }
            house.addFloor(floor);
        }
        house.setNumberOfRooms(numberOfRooms);
    }

    @Override
    public void generatePeople() {
        Random random = new Random();
        List<Floor> floors = house.getFloors();
        int numberOfFloors = floors.size();

        for (int i = 0; i < numberOfPeople; i++) {
            String name = names.get(random.nextInt(names.size()));
            String surname = surnames[random.nextInt(surnames.length)];
            PersonGender[] personGenders = PersonGender.values();


            PersonGender personGender = personGenders[random.nextInt(personGenders.length)];
            int age = random.nextInt(100);

            int floorNumber = random.nextInt(numberOfFloors);
            Floor selectedFloor = floors.get(floorNumber);
            List<Room> rooms = selectedFloor.getRooms();
            int roomNumber = random.nextInt(rooms.size());

            Room selectedRoom = rooms.get(roomNumber);
            Person person = new Person(name, surname, personGender, age);
            person.setPrefixTelephone("+420");
            selectedRoom.addPerson(person);
        }
    }


    @Override
    public void generatePets() {
        int PetsCount = 9;
        for (int i = 0; i < PetsCount; i++) {
            String name = names.get(new Random().nextInt(names.size()));
            String gender = Genders[new Random().nextInt(Genders.length)];
            int age = new Random().nextInt();
            PetType petType = PetType.values()[new Random().nextInt(PetType.values().length)];
            house.addPet(new pet.Pet(name, age, gender, petType));
        }
    }

    @Override
    public void generateDevices() {
        int deviceCount = house.getNumberOfRooms() * 2;
        Random random = new Random();

        Map<RoomType, List<Supplier<Device>>> deviceSuppliers = new HashMap<>();
        deviceSuppliers.put(RoomType.BATHROOM, Arrays.asList(WashingMachine::new));
        deviceSuppliers.put(RoomType.BEDROOM, Arrays.asList(TV::new, Window::new));
        deviceSuppliers.put(RoomType.KITCHEN, Arrays.asList(Fridge::new, Owen::new, Window::new));
        deviceSuppliers.put(RoomType.LIVINGROOM, Arrays.asList(TV::new, Window::new));

        List<Floor> floors = house.getFloors();

        for (Floor floor : floors) {
            for (Room room : floor.getRooms()) {
                List<Supplier<Device>> suppliers = deviceSuppliers.get(room.getRoomType());
                if (suppliers != null && !suppliers.isEmpty()) {
                    for (int i = 0; i < 2; i++) {
                        Supplier<Device> deviceSupplier = suppliers.get(random.nextInt(suppliers.size()));
                        room.addDevice(deviceSupplier.get());
                    }
                }
            }
        }

        house.getFloor(0).getRoom(0).addSensor(new FireSensor());
        house.getFloor(0).getRoom(0).addSensor(new WindSensor());
    }

    private void attachPeopleToBreakableDevices() {
        List<Person> people = house.getPeople();
        List<Device> devices = house.getDevices();

        people.forEach(person ->
                devices.forEach(device ->
                        device.attach((Observer) person)
                )
        );
    }

    @Override
    public House getHouse() {
        return house;
    }
}
