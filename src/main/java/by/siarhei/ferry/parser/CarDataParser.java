package by.siarhei.ferry.parser;

import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.thread.CarType;

public class CarDataParser {
    private static final String DATA_TYPE = "(?<=[a-zA-Z][:])\\s+";
    private static final String SPLIT_SYMBOL = ";";
    private static final int CAR_NUMBER_ID = 0;
    private static final int CAR_TYPE_ID = 1;
    private static final int CAR_WEIGHT_ID = 2;
    private static final int CAR_DESTINATION_POINT_ID = 3;
    private static final int VALUE_ID = 1;
    private static final String TYPE_CAR = "Car";
    private static final String COAST_TYPE_EAST = "east";

    public String parseNumber(String line) {
        String[] subLines = line.split(SPLIT_SYMBOL);
        return subLines[CAR_NUMBER_ID].split(DATA_TYPE)[VALUE_ID];
    }

    public CarType parseType(String line) {
        String[] subLines = line.split(SPLIT_SYMBOL);
        String type = subLines[CAR_TYPE_ID].split(DATA_TYPE)[VALUE_ID];
        if (type.equals(TYPE_CAR)) {
            return CarType.CAR;
        } else {
            return CarType.TRUCK;
        }
    }

    public double parseWeight(String line) {
        double weight;
        String[] subLines = line.split(SPLIT_SYMBOL);
        weight = Double.valueOf(subLines[CAR_WEIGHT_ID].split(DATA_TYPE)[VALUE_ID]);
        return weight;
    }

    public CoastType destinationPoint(String line) {
        String[] subLines = line.split(SPLIT_SYMBOL);
        String type = subLines[CAR_DESTINATION_POINT_ID].split(DATA_TYPE)[VALUE_ID];
        if (type.equals(COAST_TYPE_EAST)) {
            return CoastType.EAST;
        } else {
            return CoastType.WEST;
        }
    }
}
