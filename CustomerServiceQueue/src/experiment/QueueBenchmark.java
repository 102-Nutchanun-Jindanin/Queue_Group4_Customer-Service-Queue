package experiment;

import algorithm.AlgorithmA_SeparateQueue;
import algorithm.AlgorithmB_SingleQueue;
import model.Customer;
import model.ServiceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QueueBenchmark {

    public static void runBenchmark() {
        int[] sizes = {100, 1000, 10000, 50000};
        int rounds = 5;
        long seed = 42L;

        System.out.println("=======================================================================");
        System.out.println("                    ALGORITHM EXPERIMENTAL BENCHMARK                   ");
        System.out.println("=======================================================================");

        System.out.println("Performing JVM Warm-up...");
        for (int i = 0; i < 3; i++) {
            benchmarkAlgorithmA(1000, seed);
            benchmarkAlgorithmB(1000, seed);
        }
        System.out.println("Warm-up Completed.\n");

        for (int n : sizes) {
            long totalTimeA = 0;
            long totalTimeB = 0;

            for (int r = 0; r < rounds; r++) {
                totalTimeA += benchmarkAlgorithmA(n, seed + r);
                totalTimeB += benchmarkAlgorithmB(n, seed + r);
            }

            double avgTimeAMs = (totalTimeA / (double) rounds) / 1_000_000.0;
            double avgTimeBMs = (totalTimeB / (double) rounds) / 1_000_000.0;

            System.out.printf("N = %,6d | Avg Execution Time (A - Separate): %8.4f ms | (B - Single): %8.4f ms%n",
                    n, avgTimeAMs, avgTimeBMs);
        }
        System.out.println("=======================================================================\n");
    }

    private static long benchmarkAlgorithmA(int n, long seed) {
        Random random = new Random(seed);
        AlgorithmA_SeparateQueue sysA = new AlgorithmA_SeparateQueue(3);
        List<Customer> testData = generateData(n, random);

        long startTime = System.nanoTime();
        for (Customer c : testData) {
            sysA.arrive(c);
            sysA.assignCounter(c.getArrivalTime());
        }
        return System.nanoTime() - startTime;
    }

    private static long benchmarkAlgorithmB(int n, long seed) {
        Random random = new Random(seed);
        AlgorithmB_SingleQueue sysB = new AlgorithmB_SingleQueue(3);
        List<Customer> testData = generateData(n, random);

        long startTime = System.nanoTime();
        for (Customer c : testData) {
            sysB.arrive(c);
            sysB.assignCounter(c.getArrivalTime());
        }
        return System.nanoTime() - startTime;
    }

    private static List<Customer> generateData(int n, Random random) {
        List<Customer> list = new ArrayList<>(n);
        ServiceType[] types = ServiceType.values();
        for (int i = 0; i < n; i++) {
            ServiceType type = types[random.nextInt(types.length)];
            list.add(new Customer("C" + (i + 1), i, type, type.getDefaultDuration()));
        }
        return list;
    }
}
