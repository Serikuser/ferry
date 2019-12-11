package by.siarhei.ferry.thread;

import by.siarhei.ferry.entity.CoastType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car extends Thread {

    private static final Logger logger = LogManager.getLogger();

    private static final int OCCUPIED_PARKING_PLACES_CAR = 1;
    private static final int OCCUPIED_PARKING_PLACES_TRUCK = 3;

    private String number;
    private double weight;
    private CarType type;
    private CoastType destinationPoint;
    private Lock locker;
    private boolean reachedDestination;

    public Car(String number, double weight, CarType type, CoastType destinationPoint) {
        this.number = number;
        this.weight = weight;
        this.type = type;
        this.destinationPoint = destinationPoint;
        this.locker = new ReentrantLock();
        this.reachedDestination = false;
    }

    @Override
    public void run() {
        Ferry.getInstance().getOnBard(this);
        logger.info(String.format(
                "Car: [%s] is loaded on ferry ", this.toString()));
        this.locker.lock();
        this.reachedDestination = true;
        logger.info(String.format(
                "Car: [%s] reached destination", this.toString()));
    }

    public boolean isReachedDestination() {
        return this.reachedDestination;
    }

    public double getWeight() {
        return this.weight;
    }

    public CoastType getDestinationPoint() {
        return this.destinationPoint;
    }

    @Override
    public String toString() {
        return String.format(
                "%s with number: [%s] and weight: %s", this.type, this.number, this.weight);
    }

    public int getSize() {
        if (this.type.equals(CarType.CAR)) {
            return OCCUPIED_PARKING_PLACES_CAR;

        } else {
            return OCCUPIED_PARKING_PLACES_TRUCK;
        }
    }

    public Lock getLock() {
        return this.locker;
    }
}
