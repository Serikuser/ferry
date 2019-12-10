package by.siarhei.ferry.entity.impl;

import by.siarhei.ferry.entity.Coast;
import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.thread.Car;

import java.util.LinkedList;
import java.util.Queue;

public class RiverCoast implements Coast {
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
    }

    public CoastType getType() {
        return this.coast;
    }
}
