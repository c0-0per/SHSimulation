package house;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int number;
    private List<Room> rooms;

    public Floor(int number) {
        this.number = number;
        rooms = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room getRoom(int number) {
        return rooms.get(number);
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
