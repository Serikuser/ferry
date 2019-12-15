package by.siarhei.ferry.state;

import by.siarhei.ferry.service.FerryService;
import by.siarhei.ferry.entity.Ferry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class FerryState {

    protected static final Logger logger = LogManager.getLogger();

    protected Ferry ferry;

    FerryState() {
        this.ferry = Ferry.getInstance();
    }

    public abstract void interpret(FerryService service);

}
