CLASS SingleSharedQueueSystem:
    // Initialize properties
    counters = ARRAY of Counter objects
    sharedQueue = Queue<Customer> (FIFO Queue กลางเดี่ยว)

    // 1. ARRIVE Operation
    FUNCTION arrive(customer):
        sharedQueue.offer(customer) // เพิ่มลงท้ายคิวกลางเดี่ยว (Enqueue)
    END FUNCTION

    // 2. ASSIGN_COUNTER Operation
    FUNCTION assignCounter(currentTime):
        FOR EACH counter IN counters DO:
            // ถ้าเคาน์เตอร์ว่าง และ คิวกลางมีคนรออยู่
            IF NOT counter.isBusy() AND NOT sharedQueue.isEmpty() THEN:
                customer = sharedQueue.poll() // ดึงคนหน้าสุดของคิวกลางออก (Dequeue)
                counter.assignCustomer(customer, currentTime)
            END IF
        END FOR
    END FUNCTION

    // 3. SERVICE_COMPLETE Operation
    FUNCTION serviceComplete(counterId, currentTime):
        counterIndex = counterId - 1
        IF counterIndex >= 0 AND counterIndex < counters.length THEN:
            counters[counterIndex].completeService(currentTime)
        END IF
    END FUNCTION

    // 4. CANCEL Operation
    FUNCTION cancelCustomer(targetCustomerId):
        FOR EACH customer IN sharedQueue DO:
            IF customer.id == targetCustomerId THEN:
                sharedQueue.remove(customer) // ลบคนออกจากคิวกลาง
                RETURN TRUE
            END IF
        END FOR
        RETURN FALSE
    END FUNCTION