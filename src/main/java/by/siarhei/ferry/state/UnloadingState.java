package by.siarhei.ferry.state;

import by.siarhei.ferry.service.FerryService;
import by.siarhei.ferry.entity.Ferry;

public class UnloadingState extends FerryState {

    @Override
    public void interpret(FerryService service) {
        Ferry.getInstance().changeCoast();
        logger.info(String.format("The ferry is reached %s coast", ferry.currentCoast().getType()));
        service.unloadCarsFromFerry(ferry.currentCoast(), ferry.getLoadedCars());
        ferry.changeState(new LoadingState());
    }
}
