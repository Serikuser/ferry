package by.siarhei.ferry.factory;

import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.parser.CarDataParser;
import by.siarhei.ferry.thread.Car;
import by.siarhei.ferry.thread.CarType;

public class CarFactory {
    private static CarFactory instance;

    private CarFactory() {
    }

    public static CarFactory getInstance() {
        if (instance == null) {
            instance = new CarFactory();
        }
        return instance;
    }

    public Car createCar(String line) {
        CarDataParser parser = new CarDataParser();
        String carNumber = parser.parseNumber(line);
        double weight = parser.parseWeight(line);
        CarType type = parser.parseType(line);
        CoastType destinationPoint = parser.destinationPoint(line);
        return new Car(carNumber, weight, type, destinationPoint);
    }
}
