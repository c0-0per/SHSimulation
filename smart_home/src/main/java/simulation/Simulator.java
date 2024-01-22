package simulation;

import events.GenerateEvent;
import house.House;
import reports.EventReport;
import reports.EventReportStruct;

import java.util.logging.Logger;

public class Simulator {
    private static final Logger LOGGER = Logger.getLogger(Simulator.class.getName());

    private House house;
    private Living living;
    private GenerateEvent generateEvent;

    public Simulator(House house) {
        this.house = house;
        generateEvent = new GenerateEvent(house);
        living = new simulation.Living(house);
    }

    private void printLogo() {
        LOGGER.info("*****************************************");
        LOGGER.info("*                                       *");
        LOGGER.info("*            SMART HOME SYSTEM          *");
        LOGGER.info("*                                       *");
        LOGGER.info("*****************************************");
        LOGGER.info("*          Welcome to your home         *");
        LOGGER.info("*        Your comfort is our duty       *");
        LOGGER.info("*    Enjoy the convenience of automation *");
        LOGGER.info("*****************************************");
    }

    public void startSimulation(int time) {
        printLogo();
        for (int i = 0; i < time; i++) {
            if (i % 31 == 0) {
                generateEvent.generateBrokenDeviceEvent();
            } else if (i % 12 == 0) {
                generateEvent.generateFireEvent();
            } else if (i % 11 == 0) {
                generateEvent.generateWindEvent();
            } else if (i % 7 == 0) {
                living.GoToWatchTV(house.getRandomPerson());
            } else if (i % 5 == 0) {
                living.GoToSleep(house.getRandomPerson());
            } else if (i % 10 == 0) {
                living.GoToShop(house.getRandomPerson());
            } else if (i % 3 == 0) {
                living.GoToEat(house.getRandomPerson());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.info("Simulation interrupted");
                return;
            }
        }
        LOGGER.info("Simulation ended");
    }


}
