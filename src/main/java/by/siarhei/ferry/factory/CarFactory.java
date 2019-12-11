package by.siarhei.ferry.factory;

import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.parser.InputDataParser;
import by.siarhei.ferry.thread.Car;
import by.siarhei.ferry.thread.CarType;

public class CarFactory {

    public Car createCar(String line) {
        InputDataParser parser = new InputDataParser();
        String carNumber = parser.parseNumber(line);
        double weight = parser.parseWeight(line);
        CarType type = parser.parseType(line);
        CoastType destinationPoint = parser.destinationPoint(line);
        return new Car(carNumber, weight, type, destinationPoint);
    }
}
