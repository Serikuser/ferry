package by.siarhei.ferry.entity;

import by.siarhei.ferry.service.FerryService;
import by.siarhei.ferry.state.FerryState;
import by.siarhei.ferry.state.LoadingState;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ferry implements Runnable {

    public static final int MAX_PARKING_PLACES = 8;
    public static final double MAX_CARRYING_CAPACITY = 20;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Ferry instance;
    private static Lock locker = new ReentrantLock();

    private FerryState state;
    private Queue<Car> loadedCars;
    private RiverCoast eastRiverCoast;
    private RiverCoast westRiverCoast;
    private RiverCoast currentRiverCoast;

    private Ferry() {
        this.loadedCars = new ArrayDeque<>();
    }

    public static Ferry getInstance() {
        if (!isCreated.get()) {
            try {
                locker.lock();
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
        this.state = new LoadingState();
        while (!eastRiverCoast.getCarList().isEmpty()
                || !westRiverCoast.getCarList().isEmpty()
                || !loadedCars.isEmpty()) {
            state.interpret(service);
        }
    }

    public void changeCoast() {
        if (currentRiverCoast.getType().equals(CoastType.EAST)) {
            currentRiverCoast = westRiverCoast;
        } else {
            currentRiverCoast = eastRiverCoast;
        }
    }

    public void getOnBard(Car car) {
        this.loadedCars.add(car);
    }

    public void setEastRiverCoast(RiverCoast eastRiverCoast) {
        this.eastRiverCoast = eastRiverCoast;
        this.currentRiverCoast = eastRiverCoast;
    }

    public void setWestRiverCoast(RiverCoast westRiverCoast) {
        this.westRiverCoast = westRiverCoast;
    }

    public void changeState(FerryState state) {
        this.state = state;
    }

    public RiverCoast currentCoast() {
        return this.currentRiverCoast;
    }

    public Queue<Car> getLoadedCars() {
        return loadedCars;
    }

}
