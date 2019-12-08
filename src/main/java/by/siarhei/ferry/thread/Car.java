package by.siarhei.ferry.thread;

import by.siarhei.ferry.entity.Coast;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Car extends Thread {
    private static final Logger logger = LogManager.getLogger();

    private String name;
    private double weight;
    private double width;
    private double length;
    private Coast destinationPoint;

   public Car(String name, double weight, double width, double length, Coast destinationPoint) {
        this.name = name;
        this.weight = weight;
        this.width = width;
        this.length = length;
        this.destinationPoint = destinationPoint;
    }

    @Override
    public void run() {
/*
        - прибывает к парому;
        - пытается погрузиться на паром, если места есть погружается иначе ждет;
        - плывет на пароме;
        - сьезжает с парома;
        */
    }
    public void board (Ferry ferry){
        ferry.getOnBard(this);
    }
    @Override
    public String toString() {
        return String.format("state car number: %s, destination point: %s", name, destinationPoint);
    }
}
