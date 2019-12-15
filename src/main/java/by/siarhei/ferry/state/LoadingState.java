package by.siarhei.ferry.state;

import by.siarhei.ferry.service.FerryService;

public class LoadingState extends FerryState {

    @Override
    public void interpret(FerryService service) {
        service.loadCarsOnFerry(ferry.currentCoast(), ferry.MAX_PARKING_PLACES, ferry.MAX_CARRYING_CAPACITY);
        ferry.changeState(new SailingState());
    }
}
