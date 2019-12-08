package by.siarhei.ferry.state;

import by.siarhei.ferry.entity.Coast;
import by.siarhei.ferry.thread.Ferry;

public abstract class FerryState {
    Coast currentCoast;
    Ferry ferry;

    FerryState(Ferry ferry) {
        this.ferry = ferry;
    }

    private void setCoast(Coast coast){
        this.currentCoast=coast;
    }

    public abstract String onSeiling();
    public abstract String onUnload();
    public abstract String onLoad();
}
