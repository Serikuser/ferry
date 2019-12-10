package by.siarhei.ferry.service;

import by.siarhei.ferry.entity.impl.RiverCoast;
import by.siarhei.ferry.thread.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class FerryService {
    static final Logger logger = LogManager.getLogger();


    public void loadFerry(RiverCoast currentRiverCoast, int freeParkingPlaces, double freeCapacity) {
        logger.info(String.format("Loading ferry on %s coast", currentRiverCoast.getType()));
        int count = currentRiverCoast.getCarList().size();
        for (int i = 0; i < count; i++) {
            Car car = currentRiverCoast.getCar();
            if (car == null) {
                return;
            }
            if (freeCapacity >= car.getWeight() && freeParkingPlaces >= car.getSize() && !car.isReachedDestination()) {
                car.start();
                car.getLock().lock();
                freeCapacity -= car.getWeight();
                freeParkingPlaces -= car.getSize();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.error(e, e);
                }
            } else {
                currentRiverCoast.setCar(car);
            }
        }
        logger.info("The ferry is full");
    }

    public void unloadFerry(RiverCoast currentRiverCoast, Queue<Car> loadedCars) {
        logger.info(String.format("The ferry is unloading on %s coast", currentRiverCoast.getType()));
        while (!loadedCars.isEmpty()) {
            Car car = loadedCars.poll();
            car.getLock().unlock();
        }
        logger.info("Ferry unloading finished");
    }
}

