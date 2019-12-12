package by.siarhei.ferry.service;

import by.siarhei.ferry.entity.RiverCoast;
import by.siarhei.ferry.thread.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FerryService {
    private static final Logger logger = LogManager.getLogger();
    private ExecutorService executor;

    public void loadCarsOnFerry(RiverCoast currentRiverCoast, int freeParkingPlaces, double freeCapacity) {
        logger.info(String.format("Loading ferry on %s coast %n Cars on %s coast: %s "
                , currentRiverCoast.getType(), currentRiverCoast.getType(), currentRiverCoast.getCarList()));
        int parkedCars = currentRiverCoast.getCarList().size();
        executor = Executors.newCachedThreadPool();
        Car car;
        for (int i = 0; i < parkedCars; i++) {
            car = currentRiverCoast.getCar();
            if (!isFerryFull(freeParkingPlaces, freeCapacity, car)) {
                acceptCarToParkOnFerry(executor, car);
                freeCapacity -= car.getWeight();
                freeParkingPlaces -= car.getSize();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.error("Thread cannot be sleeped throwing exception:", e);
                }
            } else {
                currentRiverCoast.setCar(car);
            }
        }
        logger.info("The ferry is full");
    }

    public void unloadCarsFromFerry(RiverCoast currentRiverCoast, Queue<Car> loadedCars) {
        logger.info(String.format("The ferry is unloading on %s coast", currentRiverCoast.getType()));
        while (!loadedCars.isEmpty()) {
            unloadCarFromFerry(executor, loadedCars);
        }
        logger.info("The ferry unloading finished");
    }

    private boolean isFerryFull(int freeParkingPlaces, double freeCapacity, Car car) {
        return !(freeCapacity >= car.getWeight() && freeParkingPlaces >= car.getSize());
    }

    private void acceptCarToParkOnFerry(ExecutorService executor, Car car) {
        executor.submit(car);
        car.getLock().lock();
    }

    private void unloadCarFromFerry(ExecutorService executor, Queue<Car> loadedCars) {
        Car car = loadedCars.poll();
        car.getLock().unlock();
        executor.shutdown();
    }
}

