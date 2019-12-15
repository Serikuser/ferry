package by.siarhei.ferry.main;

import by.siarhei.ferry.entity.CoastType;
import by.siarhei.ferry.entity.RiverCoast;
import by.siarhei.ferry.factory.CarFactory;
import by.siarhei.ferry.reader.CarDataReader;
import by.siarhei.ferry.entity.Car;
import by.siarhei.ferry.entity.Ferry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        RiverCoast eastCoast = new RiverCoast(CoastType.EAST);
        RiverCoast westCoast = new RiverCoast(CoastType.WEST);
        Ferry.getInstance().setEastRiverCoast(eastCoast);
        Ferry.getInstance().setWestRiverCoast(westCoast);
        CarDataReader reader = new CarDataReader();
        CarFactory factory = CarFactory.getInstance();
        List<String> cars = reader.readData();
        for (String line : cars) {
            if (!line.isBlank()) {
                Car car = factory.createCar(line);
                if (CoastType.EAST.equals(car.getDestinationPoint())) {
                    eastCoast.setCar(car);
                    logger.info(String.format("%s is parked on %s coast", car.toString(), eastCoast.getType()));
                } else {
                    westCoast.setCar(car);
                    logger.info(String.format("%s is parked on %s coast", car.toString(), westCoast.getType()));
                }
            }
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(Ferry.getInstance());
        executor.shutdown();
    }
}
