package by.siarhei.ferry.entity.impl;

import by.siarhei.ferry.entity.Coast;

public class WestCoast implements Coast {
    String destinationPoint = "west";

    @Override
    public String toString() {
        return destinationPoint;
    }
}
