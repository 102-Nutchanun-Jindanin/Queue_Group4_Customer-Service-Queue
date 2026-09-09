import algorithm.AlgorithmA_SeparateQueue;
import algorithm.AlgorithmB_SingleQueue;
import algorithm.CustomerServiceQueueSystem;
import experiment.QueueBenchmark;
import java.util.Scanner;
import model.Customer;
import model.ServiceType;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("   CUSTOMER SERVICE QUEUE MANAGEMENT SYSTEM");
        System.out.println("==================================================");
        System.out.println("เลือกระบบคิวที่ต้องการใช้งาน:");
        System.out.println("1. Algorithm A: Separate Queues (แยกคิวตามเคาน์เตอร์)");
        System.out.println("2. Algorithm B: Single Shared Queue (คิวกลางเดี่ยว)");
        System.out.println("3. Run Automated Experiments & Test Cases (รันการทดสอบและ Benchmark)");
        System.out.print("เลือกข้อ (1-3): ");

        int choice = Integer.parseInt(scanner.nextLine().trim());

        if (choice == 3) {
            runAutomatedSuite();
            return;
        }

        CustomerServiceQueueSystem system;
        if (choice == 1) {
            system = new AlgorithmA_SeparateQueue(3);
            System.out.println("\n>>> เริ่มใช้งาน Algorithm A (Separate Queues) <<<");
        } else {
            system = new AlgorithmB_SingleQueue(3);
            System.out.println("\n>>> เริ่มใช้งาน Algorithm B (Single Shared Queue) <<<");
        }

        printMenu();

        long currentTime = 1;

        while (true) {
            System.out.print("\n[Time: " + currentTime + "] ป้อนคำสั่ง (หรือพิมพ์ MENU / EXIT): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("EXIT")) {
                System.out.println("จบการทำงานของโปรแกรม");
                break;
            }

            String[] parts = input.split("\\s+");
            String command = parts[0];

            try {
                switch (command) {
                    case "ARRIVE":
                        // รูปแบบ: ARRIVE <CustomerID> <ServiceType: GENERAL|FINANCIAL|VIP> <Duration>
                        if (parts.length < 4) {
                            System.out.println("❌ รูปแบบไม่ถูกต้อง! ตัวอย่าง: ARRIVE C001 GENERAL 5");
                            break;
                        }
                        String id = parts[1];
                        ServiceType type = ServiceType.valueOf(parts[2]);
                        int duration = Integer.parseInt(parts[3]);

                        Customer newCustomer = new Customer(id, currentTime, type, duration);
                        system.arrive(newCustomer);
                        System.out.println("✅ ลูกค้า " + id + " เข้าสู่ระบบเรียบร้อย");
                        break;

                    case "ASSIGN_COUNTER":
                        system.assignCounter(currentTime);
                        System.out.println("✅ ดำเนินการมอบหมายคิวให้เคาน์เตอร์ที่ว่างแล้ว");
                        break;

                    case "SERVICE_COMPLETE":
                        // รูปแบบ: SERVICE_COMPLETE <CounterID>
                        if (parts.length < 2) {
                            System.out.println("❌ รูปแบบไม่ถูกต้อง! ตัวอย่าง: SERVICE_COMPLETE 1");
                            break;
                        }
                        int counterId = Integer.parseInt(parts[1]);
                        system.serviceComplete(counterId, currentTime);
                        System.out.println("✅ เคาน์เตอร์ " + counterId + " ให้บริการเสร็จสิ้นแล้ว");
                        break;

                    case "CANCEL":
                        // รูปแบบ: CANCEL <CustomerID>
                        if (parts.length < 2) {
                            System.out.println("❌ รูปแบบไม่ถูกต้อง! ตัวอย่าง: CANCEL C001");
                            break;
                        }
                        String cancelId = parts[1];
                        boolean cancelled = system.cancelCustomer(cancelId);
                        if (cancelled) {
                            System.out.println("✅ ยกเลิกคิวสำหรับลูกค้า " + cancelId + " เรียบร้อย");
                        } else {
                            System.out.println("❌ ไม่พบคิวของลูกค้า " + cancelId + " ในระบบ");
                        }
                        break;

                    case "DISPLAY_QUEUE":
                    case "DISPLAY":
                        system.displayQueue();
                        break;

                    case "NEXT_TIME":
                    case "TIME":
                        currentTime++;
                        System.out.println("⏱️ เวลาเปลี่ยนเป็น: " + currentTime);
                        break;

                    case "MENU":
                        printMenu();
                        break;

                    default:
                        System.out.println("❌ ไม่รู้จักคำสั่ง พิมพ์ 'MENU' เพื่อดูวิธีใช้งาน");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("❌ ข้อมูลที่ป้อนไม่ถูกต้อง: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n----------------- คำสั่งที่ใช้งานได้ -----------------");
        System.out.println("1. ARRIVE <ID> <GENERAL|FINANCIAL|VIP> <Duration>  - เพิ่มลูกค้าเข้าคิว");
        System.out.println("2. ASSIGN_COUNTER                                  - แจกจ่ายคิวเข้าเคาน์เตอร์ว่าง");
        System.out.println("3. SERVICE_COMPLETE <CounterID>                    - แจ้งเคาน์เตอร์บริการเสร็จ");
        System.out.println("4. CANCEL <ID>                                     - ยกเลิกรายการคิว");
        System.out.println("5. DISPLAY_QUEUE                                   - แสดงสถานะคิวปัจจุบัน");
        System.out.println("6. TIME                                            - เลื่อนเวลา +1");
        System.out.println("7. EXIT                                            - ออกจากโปรแกรม");
        System.out.println("-----------------------------------------------------");
    }

    private static void runAutomatedSuite() {
        System.out.println("\n=============== RUNNING 10-STEP QUEUE TRACE ===============");
        AlgorithmB_SingleQueue traceSys = new AlgorithmB_SingleQueue(3);
        System.out.println("Step 1: ARRIVE C1"); traceSys.arrive(new Customer("C1", 1, ServiceType.GENERAL, 5));
        System.out.println("Step 2: ARRIVE C2"); traceSys.arrive(new Customer("C2", 2, ServiceType.VIP, 3));
        System.out.println("Step 3: ARRIVE C3"); traceSys.arrive(new Customer("C3", 3, ServiceType.FINANCIAL, 10));
        System.out.println("Step 4: ASSIGN_COUNTER"); traceSys.assignCounter(3);
        System.out.println("Step 5: DISPLAY_QUEUE"); traceSys.displayQueue();

        System.out.println("\n=============== RUNNING ALGORITHM EXPERIMENTS ===============");
        QueueBenchmark.runBenchmark();
    }
}