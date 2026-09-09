CLASS SeparateQueueSystem:
    // Initialize properties
    counters = ARRAY of Counter objects
    queues = ARRAY of Deque<Customer> objects (1 Deque per Counter)

    // 1. ARRIVE Operation
    FUNCTION arrive(customer):
        shortestIndex = 0
        minSize = queues[0].size()

        // หา Queue ที่มีจำนวนคนรอน้อยที่สุด
        FOR i FROM 1 TO queues.length - 1 DO:
            IF queues[i].size() < minSize THEN:
                minSize = queues[i].size()
                shortestIndex = i
            END IF
        END FOR

        queues[shortestIndex].addLast(customer) // เพิ่มลงท้ายคิวที่สั้นที่สุด
    END FUNCTION

    // 2. ASSIGN_COUNTER Operation
    FUNCTION assignCounter(currentTime):
        FOR i FROM 0 TO counters.length - 1 DO:
            // ถ้าเคาน์เตอร์ว่าง และ มีลูกค้าต่อคิวช่องนั้นอยู่
            IF NOT counters[i].isBusy() AND NOT queues[i].isEmpty() THEN:
                customer = queues[i].pollFirst() // ดึงคนหน้าสุดของคิวนั้น
                counters[i].assignCustomer(customer, currentTime)
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
        FOR EACH queue IN queues DO:
            FOR EACH customer IN queue DO:
                IF customer.id == targetCustomerId THEN:
                    queue.remove(customer) // ลบคนออกจากคิวกลางแถว
                    RETURN TRUE
                END IF
            END FOR
        END FOR
        RETURN FALSE
    END FUNCTION