package API.consumptyionAPI;

public class WaterAPI {
    private int spentWaterAmount = 0;

    public int getSpentWaterAmount() {
        return spentWaterAmount;
    }

    /**
     * increments spent liters counter on num.
     *
     * @param num
     */
    public void increaseCounter(int num) {
        spentWaterAmount += num;
    }
}
