package db.entity;

public enum TourType {

    REST, EXCURSION, SHOPPING;

    public static TourType getTourType(Tour tour) {
        int tourTypeId = tour.getTourTypeId();
        return TourType.values()[--tourTypeId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
