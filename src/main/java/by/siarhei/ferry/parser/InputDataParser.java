package by.siarhei.ferry.parser;

import by.siarhei.ferry.entity.Coast;
import by.siarhei.ferry.entity.impl.EastCoast;
import by.siarhei.ferry.entity.impl.WestCoast;
import by.siarhei.ferry.thread.Car;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class InputDataParser {

    private static final String SEPARATION_LINE = "-------------------------";
    private static final String SEPARATION_SYMBOL = ";";
    private static final int DESTINATION_POINT_ID = 4;
    private static final int NAME_ID = 0;
    private static final int WEIGHT_ID = 1;
    private static final int WIDTH_ID = 2;
    private static final int LENGTH_ID = 3;
    private static final int DATA_IN_LINE_ID = 1;

    public List<Car> parse(String data) {
        List<Car> carsList = new LinkedList<>();
        String[] cars = data.split(SEPARATION_LINE);
        for (String car : cars) {
            String[] carInformation = car.split(SEPARATION_SYMBOL);
            String name = carInformation[NAME_ID].split(" ")[DATA_IN_LINE_ID];
            double weight = Double.parseDouble(carInformation[WEIGHT_ID].split(" ")[DATA_IN_LINE_ID]);
            double width = Double.parseDouble(carInformation[WIDTH_ID].split(" ")[DATA_IN_LINE_ID]);
            double length = Double.parseDouble(carInformation[LENGTH_ID].split(" ")[DATA_IN_LINE_ID]);
            Coast destinationPoint;
            if (carInformation[DESTINATION_POINT_ID].split(" ")[DATA_IN_LINE_ID].equals("east")) {
                destinationPoint = new EastCoast();
            } else {
                destinationPoint = new WestCoast();
            }
            carsList.add(new Car(name, weight, width, length, destinationPoint));
        }
        return carsList;
    }

}
