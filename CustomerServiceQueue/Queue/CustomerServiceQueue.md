# กลุ่มที่ 3: Customer Service Queue ระบบคิวลูกค้าศูนย์บริการ

## Case Story

ศูนย์บริการมีเคาน์เตอร์หลายช่อง แต่ลูกค้าทุกคนเข้าคิวกลางเพียง **Queue เดียว**

### ข้อมูลลูกค้า

* Customer ID
* Arrival Time
* Service Type
* Service Duration

### ระบบต้องรองรับ

* `ARRIVE`
* `ASSIGN_COUNTER`
* `SERVICE_COMPLETE`
* `DISPLAY_QUEUE`

มีเคาน์เตอร์ทั้งหมด 3 ช่อง

* Counter 1
* Counter 2
* Counter 3

## Algorithm A: Separate Queue

แต่ละ Counter มี Queue ของตนเอง

## Algorithm B: Single Shared Queue

ใช้ Queue กลางเพียง Queue เดียว เมื่อ Counter ว่าง ให้เรียกลูกค้าคนแรกใน Queue

## สิ่งที่ต้องเปรียบเทียบ

* Waiting Time
* Queue Length
* Counter Utilization
* ความยุติธรรม (Fairness)

## คำถาม

**เหตุใดธนาคารและสนามบินจำนวนมากจึงนิยมใช้ Single Queue + Multiple Servers?**
