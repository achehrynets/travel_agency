package db.entity;

public enum TransportType {

    AIRCRAFT, BUS, SHIP;

    public static TransportType getFlightType(Flight flight) {
        int transportTypeId = flight.getTransportTypeId();
        return TransportType.values()[--transportTypeId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
