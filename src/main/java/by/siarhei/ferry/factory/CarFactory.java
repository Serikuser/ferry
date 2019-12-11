package by.siarhei.ferry.factory;

import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.parser.InputDataParser;
import by.siarhei.ferry.thread.Car;
import by.siarhei.ferry.thread.CarType;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarFactory {

    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Lock locker = new ReentrantLock();
    private static CarFactory instance;

    private CarFactory() {
    }

    public static CarFactory getInstance() {
        if (!isCreated.get()) {
            locker.lock();
            try {
                if (instance == null) {
                    instance = new CarFactory();
                    isCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public Car createCar(String line) {
        InputDataParser parser = new InputDataParser();
        String carNumber = parser.parseNumber(line);
        double weight = parser.parseWeight(line);
        CarType type = parser.parseType(line);
        CoastType destinationPoint = parser.destinationPoint(line);
        return new Car(carNumber, weight, type, destinationPoint);
    }
}
