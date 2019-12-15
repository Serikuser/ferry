package by.siarhei.ferry.entity;

import java.util.ArrayDeque;
import java.util.Queue;

public class RiverCoast {
    private Queue<Car> cars;
    private CoastType coast;

    public RiverCoast(CoastType coast) {
        this.coast = coast;
        cars = new ArrayDeque<>();
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
