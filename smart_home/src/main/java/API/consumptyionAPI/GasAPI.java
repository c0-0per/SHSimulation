package API.consumptyionAPI;

public class GasAPI {
    private int spentGasAmount = 0;

    public int getSpentGasAmount() {
        return spentGasAmount;
    }

    /**
     * increments spent liters counter on num.
     *
     * @param num
     */
    public void increaseCounter(int num) {
        spentGasAmount += num;
    }
}
