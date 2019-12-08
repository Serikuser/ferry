package by.siarhei.ferry.state;

import by.siarhei.ferry.thread.Ferry;

public class UnloadingState extends FerryState {

    public UnloadingState(Ferry ferry) {
        super(ferry);
        ferry.setSailing(false);
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
