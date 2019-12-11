package by.siarhei.ferry.entity;

import by.siarhei.ferry.thread.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class RiverCoast {
    private static final Logger logger = LogManager.getLogger();

    private Queue<Car> cars = null;
    private CoastType coast;

    public RiverCoast(CoastType coast) {
        this.coast = coast;
        cars = new LinkedList<>();
    }

    public Queue<Car> getCarList() {
        return this.cars;
    }

    public Car getCar() {
        return cars.poll();
    }

    public void setCar(Car car) {
        cars.add(car);
        logger.info(String.format("%s is parked on %s coast", car.toString(), this.coast));
    }

    public CoastType getType() {
        return this.coast;
    }
}
