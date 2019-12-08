package by.siarhei.ferry;

import by.siarhei.ferry.exception.InvalidInputFilePathException;
import by.siarhei.ferry.parser.InputDataParser;
import by.siarhei.ferry.reader.InputDataReader;
import by.siarhei.ferry.thread.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Start {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws InvalidInputFilePathException {
        InputDataReader reader = new InputDataReader();
        InputDataParser parser = new InputDataParser();
        List<Car> cars = parser.parse(reader.readData());
        logger.info(cars);
    }
}
