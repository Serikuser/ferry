package by.siarhei.ferry.entity.impl;

import by.siarhei.ferry.entity.Coast;

public class EastCoast implements Coast {


    String destinationPoint = "east";

    @Override
    public String toString() {
        return destinationPoint;
    }
}
