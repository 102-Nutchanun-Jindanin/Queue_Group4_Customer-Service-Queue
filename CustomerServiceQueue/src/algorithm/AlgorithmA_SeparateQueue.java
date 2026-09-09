package algorithm;

import java.util.*;
import model.Counter;
import model.Customer;

public class AlgorithmA_SeparateQueue implements CustomerServiceQueueSystem {
    private final List<Counter> counters;
    private final List<Deque<Customer>> counterQueues;

    public AlgorithmA_SeparateQueue(int numCounters) {
        counters = new ArrayList<>();
        counterQueues = new ArrayList<>();
        for (int i = 0; i < numCounters; i++) {
            counters.add(new Counter(i + 1));
            counterQueues.add(new ArrayDeque<>());
        }
    }

    @Override
    public void arrive(Customer customer) {
        int shortestIndex = 0;
        int minSize = counterQueues.get(0).size();

        for (int i = 1; i < counterQueues.size(); i++) {
            if (counterQueues.get(i).size() < minSize) {
                minSize = counterQueues.get(i).size();
                shortestIndex = i;
            }
        }
        counterQueues.get(shortestIndex).addLast(customer);
    }

    @Override
    public void assignCounter(long currentTime) {
        for (int i = 0; i < counters.size(); i++) {
            Counter counter = counters.get(i);
            Deque<Customer> queue = counterQueues.get(i);

        if (!counter.isBusy() && !queue.isEmpty()) {
            // 📌 ใช้ peekFirst() เพื่ออ่านข้อมูลลูกค้าหัวคิวโดยที่ยังไม่ลบออกจาก Queue
                Customer customer = queue.peekFirst(); 
                counter.assignCustomer(customer, currentTime);
            }
        }
    }

    @Override
    public void serviceComplete(int counterId, long currentTime) {
        if (counterId >= 1 && counterId <= counters.size()) {
            int index = counterId - 1;
            Counter counter = counters.get(index);
            Deque<Customer> queue = counterQueues.get(index);

        if (counter.isBusy()) {
            // 📌 เมื่อบริการเสร็จสิ้น ให้ Dequeue (pollFirst) ลบลูกค้าออกจากคิวจริงตรงนี้
            if (!queue.isEmpty()) {
                queue.pollFirst();
            }
                counter.completeService(currentTime);
            }
        }
    }

    @Override
    public void displayQueue() {
        System.out.println("=== Separate Queues Status ===");
        for (int i = 0; i < counterQueues.size(); i++) {
            Deque<Customer> queue = counterQueues.get(i);
            System.out.println("Counter " + (i + 1) + " Queue (Size " + queue.size() + "): " + queue);
    }
}
    @Override
    public boolean cancelCustomer(String customerId) {
        for (Deque<Customer> queue : counterQueues) {
            Iterator<Customer> iterator = queue.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getCustomerId().equals(customerId)) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public List<Counter> getCounters() { return counters; }
}
