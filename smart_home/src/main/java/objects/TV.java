package objects;

/**
 * The TV class extends the Device class, representing a television in a smart home environment.
 * It manages the current channel and tracks the power consumption specific to a TV.
 */
public class TV extends Device {
    private final int kWPerHour = 2;
    private int indexOfChannel = 1;

    /**
     * Constructs a TV object with default settings.
     * Sets the power consumption specific to the TV.
     */
    public TV() {
        super();
        setkWPerHour(kWPerHour);
    }

    /**
     * Increases the current channel index by one.
     * If it exceeds the maximum channel index, it resets to the first channel.
     */
    public void increaseNumberOfChannel() {
        indexOfChannel++;
        if (indexOfChannel > 21) {
            indexOfChannel = 1;
        }
    }

    /**
     * Decreases the current channel index by one.
     * If it goes below the first channel, it resets to the maximum channel index.
     */
    public void decreaseNumberOfChannel() {
        indexOfChannel--;
        if (indexOfChannel < 1) {
            indexOfChannel = 20;
        }
    }


    public int getIndexOfChannel() {
        return indexOfChannel;
    }

    public void setIndexOfChannel(int indexOfChannel) {
        this.indexOfChannel = indexOfChannel;
    }
}
