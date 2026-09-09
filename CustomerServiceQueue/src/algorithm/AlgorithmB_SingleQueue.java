package algorithm;

import model.Counter;
import model.Customer;
import java.util.*;

public class AlgorithmB_SingleQueue implements CustomerServiceQueueSystem {
    private final List<Counter> counters;
    private final Queue<Customer> sharedQueue;

    public AlgorithmB_SingleQueue(int numCounters) {
        counters = new ArrayList<>();
        for (int i = 0; i < numCounters; i++) {
            counters.add(new Counter(i + 1));
        }
        this.sharedQueue = new ArrayDeque<>();
    }

    @Override
    public void arrive(Customer customer) {
        sharedQueue.offer(customer);
    }

    @Override
    public void assignCounter(long currentTime) {
        for (Counter counter : counters) {
            if (!counter.isBusy() && !sharedQueue.isEmpty()) {
                Customer customer = sharedQueue.poll();
                counter.assignCustomer(customer, currentTime);
            }
        }
    }

    @Override
    public void serviceComplete(int counterId, long currentTime) {
        if (counterId >= 1 && counterId <= counters.size()) {
            counters.get(counterId - 1).completeService(currentTime);
        }
    }

    @Override
    public void displayQueue() {
        System.out.println("=== Single Shared Queue Status (Size: " + sharedQueue.size() + ") ===");
        System.out.println(sharedQueue);
    }

    @Override
    public boolean cancelCustomer(String customerId) {
        Iterator<Customer> iterator = sharedQueue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getCustomerId().equals(customerId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Counter> getCounters() { return counters; }
}
