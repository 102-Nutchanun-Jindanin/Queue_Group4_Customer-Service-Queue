package model;

public class Counter {
    private final int counterId;
    private boolean isBusy;
    private Customer currentCustomer;
    private long totalBusyTime;

    public Counter(int counterId) {
        this.counterId = counterId;
        this.isBusy = false;
        this.currentCustomer = null;
        this.totalBusyTime = 0;
    }

    public int getCounterId() { return counterId; }
    public boolean isBusy() { return isBusy; }
    public Customer getCurrentCustomer() { return currentCustomer; }
    public long getTotalBusyTime() { return totalBusyTime; }

    public void assignCustomer(Customer customer, long currentTime) {
        this.currentCustomer = customer;
        this.isBusy = true;
        customer.setStartServiceTime(currentTime);
    }

    public Customer completeService(long currentTime) {
        if (currentCustomer == null) return null;
        Customer finished = currentCustomer;
        finished.setCompletionTime(currentTime);
        this.totalBusyTime += finished.getServiceDuration();
        this.currentCustomer = null;
        this.isBusy = false;
        this.isBusy = false;          
        this.currentCustomer = null;
        return finished;     
    }
}
