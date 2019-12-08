package by.siarhei.ferry.thread;

import by.siarhei.ferry.state.FerryState;
import by.siarhei.ferry.state.LoadingState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ferry extends Thread {
    private static final Logger logger = LogManager.getLogger();

    private static final int MAX_PARKING_PLACES = 10;
    private static final double MAX_CARRYING_CAPACITY = 50;
    private static Ferry instance;
    private FerryState state;
    private int occupiedParkingPlaces;
    private double currentCapacity;

    private boolean sailing;

    private Ferry() {
        state = new LoadingState(this);
        setSailing(false);
    }

    public static Ferry getInstance() {
        if (instance == null) {
            instance = new Ferry();
        }
        return instance;
    }

    @Override
    public void run() {

/*
        - паром ожидает погрузки; (установка state на LoadingState)
        - когда occupiedParkingPlaces приближен к MAX_PARKING_PLACES или capacity приближен к MAX_CARRYING_CAPACITY
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

    public void getOnBard(Car car) {
    }

    private void changeState(FerryState state){
        this.state = state;
    }

    public void setSailing(boolean sailing) {
        this.sailing = sailing;
    }

    public boolean isSailing() {
        return this.sailing;
    }
}
