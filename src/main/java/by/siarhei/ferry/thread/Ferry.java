package by.siarhei.ferry.thread;

import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.entity.impl.RiverCoast;
import by.siarhei.ferry.service.FerryService;
import by.siarhei.ferry.state.FerryState;
import by.siarhei.ferry.state.LoadingState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ferry extends Thread {
    private static final Logger logger = LogManager.getLogger();

    private static final int MAX_PARKING_PLACES = 10;
    private static final double MAX_CARRYING_CAPACITY = 50;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Ferry instance;
    private static Lock locker = new ReentrantLock();

    private FerryState state;
    private int freeParkingPlaces;
    private double freeCapacity;
    private Queue<Car> loadedCars;
    private RiverCoast eastRiverCoast;
    private RiverCoast westRiverCoast;
    private boolean sailing;
    private RiverCoast currentRiverCoast;
    private Phaser phaser;

    private Ferry() {
        this.loadedCars = new LinkedList<>();
        this.state = new LoadingState(this);
        setSailing(false);
        this.currentRiverCoast = eastRiverCoast;
        freeParkingPlaces = MAX_PARKING_PLACES;
        freeCapacity = MAX_CARRYING_CAPACITY;
        this.phaser = new Phaser();

    }

    public static Ferry getInstance() {
        if (!isCreated.get()) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new Ferry();
                    isCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public void run() {
        FerryService service = new FerryService();
        phaser.register();
        while (!eastRiverCoast.getCarList().isEmpty() || !westRiverCoast.getCarList().isEmpty()) {
            service.loadFerry(currentRiverCoast, freeParkingPlaces, freeCapacity);
            phaser.arriveAndAwaitAdvance();
            sailing();
            phaser.arriveAndAwaitAdvance();
            changeCoast();
            logger.info(String.format("The ferry is reached %s coast",currentCoast().getType() ));
            service.unloadFerry(currentRiverCoast, loadedCars);
            phaser.arriveAndDeregister();
        }
/*
        - паром ожидает погрузки; (установка state на LoadingState)
        - когда freeParkingPlaces приближен к MAX_PARKING_PLACES или capacity приближен к MAX_CARRYING_CAPACITY
        паром отплывает, данные расчитывают после погрузки машины или поставляются самой машиной? возможно нужен
        обьект - погрузчик;
        (установка state на SailingState)
        - паром плывет;
        (установка state на UnloadingState)
        - паром разгружает машины после доставки;
            (установка state на SailingState)
        - паром плывет обратно;
        - паром ожидает погрузки; (установка state на LoadingState)
          */

    }

    private void changeCoast() {
        if (currentRiverCoast.getType().equals(CoastType.EAST)){
            currentRiverCoast = westRiverCoast;
        }
        else{
            currentRiverCoast = eastRiverCoast;
        }
    }

    public void getOnBard(Car car) {
        this.loadedCars.add(car);
    }

    public void setSailing(boolean sailing) {
        this.sailing = sailing;
    }

    public void sailing() {
        logger.info("The ferry is sailing");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(e, e);
        }
    }

    public void setEastRiverCoast(RiverCoast eastRiverCoast) {
        this.eastRiverCoast = eastRiverCoast;
        this.currentRiverCoast = eastRiverCoast;
    }

    public void setWestRiverCoast(RiverCoast westRiverCoast) {
        this.westRiverCoast = westRiverCoast;
    }

    private void changeState(FerryState state) {
        this.state = state;
    }

    private RiverCoast currentCoast() {
        return this.currentRiverCoast;
    }
}
