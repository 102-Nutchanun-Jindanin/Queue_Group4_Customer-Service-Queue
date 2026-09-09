package model;

public enum ServiceType {
    GENERAL(5),
    FINANCIAL(10),
    VIP(3);

    private final int defaultDuration;

    ServiceType(int defaultDuration) {
        this.defaultDuration = defaultDuration;
    }

    public int getDefaultDuration() {
        return defaultDuration;
    }
}
