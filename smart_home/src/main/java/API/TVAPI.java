package API;

import objects.TV;
import java.util.logging.Logger;

public class TVAPI {
    private static final Logger LOGGER = Logger.getLogger(TVAPI.class.getName());

    private TV tv;

    public TVAPI(TV tv) {
        this.tv = tv;
    }

    public void turnOn(int time) {
        tv.turnOn(time);
    }

    public void turnOff() {
        tv.turnOff();
    }

    public void increaseNumberOfChannel() {
        tv.increaseNumberOfChannel();
    }

    public void decreaseNumberOfChannel() {
        tv.decreaseNumberOfChannel();
    }

    public void fixDevice() {
        LOGGER.info("Fixing TV");
        tv.fixDevice();
    }


}
