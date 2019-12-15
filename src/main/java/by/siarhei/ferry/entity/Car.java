package by.siarhei.ferry.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static final Logger logger = LogManager.getLogger();

    private String number;
    private double weight;
    private CarType type;
    private CoastType destinationPoint;
    private Lock locker;

    public Car(String number, double weight, CarType type, CoastType destinationPoint) {
        this.number = number;
        this.weight = weight;
        this.type = type;
        this.destinationPoint = destinationPoint;
        this.locker = new ReentrantLock();
    }

    @Override
    public void run() {
        Ferry.getInstance().getOnBard(this);
        logger.info(String.format(
                "Car: [%s] is loaded on ferry ", this.toString()));
        this.locker.lock();
        logger.info(String.format(
                "Car: [%s] reached destination", this.toString()));
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
        return this.type.getOccupiedParkingPlaces();
    }

    public Lock getLock() {
        return this.locker;
    }
}
