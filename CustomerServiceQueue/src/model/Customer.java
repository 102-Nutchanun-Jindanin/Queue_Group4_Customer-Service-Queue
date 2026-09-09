package model;

public class Customer {
    private final String customerId;
    private final long arrivalTime;
    private final ServiceType serviceType;
    private final int serviceDuration;
    private long startServiceTime;
    private long completionTime;

    public Customer(String customerId, long arrivalTime, ServiceType serviceType, int serviceDuration) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.serviceType = serviceType;
        this.serviceDuration = serviceDuration;
        this.startServiceTime = -1;
        this.completionTime = -1;
    }

    public String getCustomerId() { return customerId; }
    public long getArrivalTime() { return arrivalTime; }
    public ServiceType getServiceType() { return serviceType; }
    public int getServiceDuration() { return serviceDuration; }
    
    public long getStartServiceTime() { return startServiceTime; }
    public void setStartServiceTime(long startServiceTime) { this.startServiceTime = startServiceTime; }
    
    public long getCompletionTime() { return completionTime; }
    public void setCompletionTime(long completionTime) { this.completionTime = completionTime; }

    public long getWaitingTime() {
        if (startServiceTime == -1) return 0;
        return startServiceTime - arrivalTime;
    }

    @Override
    public String toString() {
        return String.format("Customer[ID=%s, Type=%s, Duration=%d]", customerId, serviceType, serviceDuration);
    }
}
