package by.siarhei.ferry.main;

import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.entity.RiverCoast;
import by.siarhei.ferry.exception.InvalidInputFilePathException;
import by.siarhei.ferry.factory.CarFactory;
import by.siarhei.ferry.reader.InputDataReader;
import by.siarhei.ferry.thread.Car;
import by.siarhei.ferry.thread.Ferry;

import java.util.List;

public class Start {

    public static void main(String[] args) throws InvalidInputFilePathException {
        RiverCoast eastCoast = new RiverCoast(CoastType.EAST);
        RiverCoast westCoast = new RiverCoast(CoastType.WEST);

        Ferry.getInstance().setEastRiverCoast(eastCoast);
        Ferry.getInstance().setWestRiverCoast(westCoast);


        InputDataReader reader = new InputDataReader();
        CarFactory factory = new CarFactory();
        List<String> cars = reader.readData();
        for (String line : cars) {
            if (!line.isBlank()) {
                Car car = factory.createCar(line);
                if (CoastType.EAST.equals(car.getDestinationPoint())) {
                    eastCoast.setCar(car);
                } else {
                    westCoast.setCar(car);
                }
            }
        }
        Ferry.getInstance().start();
    }
}
