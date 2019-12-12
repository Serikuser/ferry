package by.siarhei.ferry.thread;

public enum CarType {
    CAR(1),
    TRUCK(3);
    private int occupiedParkingPlaces;

    CarType(int parkingPlace) {
        this.occupiedParkingPlaces = parkingPlace;
    }

    public int getOccupiedParkingPlaces() {
        return occupiedParkingPlaces;
    }
}
