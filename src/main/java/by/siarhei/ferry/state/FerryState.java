package by.siarhei.ferry.state;

import by.siarhei.ferry.thread.Ferry;

public abstract class FerryState {
    // TODO: 11.12.2019
    Ferry ferry;

    FerryState(Ferry ferry) {
        this.ferry = ferry;
    }

    public abstract String onSeiling();

    public abstract String onUnload();

    public abstract String onLoad();
}
