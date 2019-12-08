package by.siarhei.ferry.state;

import by.siarhei.ferry.thread.Ferry;

public class SailingState extends FerryState {

    public SailingState(Ferry ferry) {
        super(ferry);
        ferry.setSailing(true);
    }

    @Override
    public String onSeiling() {
        return null;
    }

    @Override
    public String onUnload() {
        return null;
    }

    @Override
    public String onLoad() {
        return null;
    }
}
