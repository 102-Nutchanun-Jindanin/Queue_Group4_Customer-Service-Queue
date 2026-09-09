package algorithm;

import model.Customer;

public interface CustomerServiceQueueSystem {
    void arrive(Customer customer);
    void assignCounter(long currentTime);
    void serviceComplete(int counterId, long currentTime);
    void displayQueue();
    boolean cancelCustomer(String customerId);
}
