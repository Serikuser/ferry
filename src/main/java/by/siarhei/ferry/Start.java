package by.siarhei.ferry;

import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.entity.impl.RiverCoast;
import by.siarhei.ferry.exception.InvalidInputFilePathException;
import by.siarhei.ferry.thread.Car;
import by.siarhei.ferry.thread.CarType;
import by.siarhei.ferry.thread.Ferry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Start {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws InvalidInputFilePathException {
        RiverCoast eastCoast = new RiverCoast(CoastType.EAST);
        RiverCoast westCoast = new RiverCoast(CoastType.WEST);
        Car car1 = new Car("1", 44, CarType.CAR, eastCoast);
        Car car2 = new Car("2", 4, CarType.CAR, eastCoast);
        Car car3 = new Car("3", 4, CarType.CAR, eastCoast);
        Car car4 = new Car("4", 4, CarType.CAR, eastCoast);
        Car car5 = new Car("5", 4, CarType.CAR, westCoast);
        Car car6 = new Car("6", 4, CarType.CAR, westCoast);
        Car car7 = new Car("7", 4, CarType.CAR, westCoast);
        eastCoast.setCar(car1);
        eastCoast.setCar(car2);
        eastCoast.setCar(car3);
        eastCoast.setCar(car4);
        westCoast.setCar(car5);
        westCoast.setCar(car6);
        westCoast.setCar(car7);
        Ferry.getInstance().setEastRiverCoast(eastCoast);
        Ferry.getInstance().setWestRiverCoast(westCoast);
        Ferry.getInstance().start();
    }
}
