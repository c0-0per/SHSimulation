package API.consumptyionAPI;

public class ElectricityAPI {
    private int spentKilowatts = 0;

    public int getSpentKilowatts() {
        return spentKilowatts;
    }

    /**
     * increments counter of spent energy of device.
     *
     * @param num
     */
    public void increaseCounter(int num) {
        spentKilowatts += num;
    }
}

