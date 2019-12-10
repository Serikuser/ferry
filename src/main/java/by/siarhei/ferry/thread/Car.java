package by.siarhei.ferry.thread;

import by.siarhei.ferry.entity.impl.RiverCoast;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car extends Thread {
    private static final Logger logger = LogManager.getLogger();

    private String number;
    private double weight;
    private CarType type;
    private RiverCoast destinationPoint;
    private Lock locker;
    private boolean reachedDestination;

    public Car(String number, double weight, CarType type, RiverCoast destinationPoint) {
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
        logger.info(String.format("Car: [%s] is loaded on ferry ", this.number));
        this.locker.lock();
        reachedDestination = true;
        logger.info(String.format("Car: [%s] reached destination", this.number));
/*
        - прибывает к парому;
        - пытается погрузиться на паром, если места есть погружается иначе ждет;
        - плывет на пароме;
        - сьезжает с парома;
        */
    }

    public boolean isReachedDestination(){
        return this.reachedDestination;
    }
    public void board(Ferry ferry) {
        ferry.getOnBard(this);
    }

    public String getNumber() {
        return number;
    }

    public double getWeight() {
        return weight;
    }

    public CarType getType() {
        return type;
    }

    public RiverCoast getDestinationPoint() {
        return destinationPoint;
    }

    @Override
    public String toString() {
        return String.format("state car number: %s, destination point: %s", number, destinationPoint);
    }

    public int getSize() {
        if (this.type.equals(CarType.CAR)) {
            return 1;
        } else {
            return 2;
        }
    }

    public Lock getLock() {
        return this.locker;
    }
}
