package db.entity;

public enum Status {

    REGISTERED, PAID, CANCELED;

    public static Status getSatus(Order order) {
        int statusId = order.getStatusId();
        return Status.values()[--statusId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
