# งานปฏิบัติการกลุ่ม: Queue Algorithm Design & Analysis with Java

## หัวข้อหลัก

Queue, Circular Queue, Priority Queue, Deque และการประยุกต์ใช้

## วัตถุประสงค์

1. วิเคราะห์ว่าปัญหาใดเหมาะกับโครงสร้างข้อมูลแบบ Queue
2. ออกแบบ Algorithm โดยใช้หลัก FIFO ได้
3. เลือก Queue ชนิดที่เหมาะสมกับปัญหา
4. เขียนโปรแกรม Java เพื่อจำลองการทำงานของ Algorithm
5. Trace การทำงานของ Queue แบบ Step-by-step
6. วิเคราะห์ Time Complexity และ Space Complexity
7. เปรียบเทียบอัลกอริทึมอย่างน้อย 2 แนวทาง
8. อธิบายข้อดี ข้อจำกัด และเหตุผลในการเลือกโครงสร้างข้อมูล

## ข้อกำหนดร่วมของทุกกลุ่ม

ทุกกลุ่มต้องพัฒนา **อย่างน้อย 2 Algorithms** สำหรับ Case Study เดียวกัน แล้วเปรียบเทียบกันอย่างเป็นระบบ

## ส่วนที่ 1 การวิเคราะห์ปัญหา

อธิบายหัวข้อต่อไปนี้

* **Input**
* **Output**
* **Constraints**
* Queue เก็บข้อมูลอะไร
* Queue ชนิดใดเหมาะสม
* เหตุผลที่ต้องใช้ Queue

## ส่วนที่ 2 Algorithm Design

เขียน Pseudocode ของ Algorithm A และ Algorithm B โดยไม่ควรเริ่มด้วย Java ทันที

## ส่วนที่ 3 Queue Trace

ต้องมีอย่างน้อย **10 Operations** แสดงในรูปตาราง

และนักศึกษาต้องอธิบายว่า เหตุใด Queue จึงเปลี่ยนเป็นสถานะดังกล่าว

## ส่วนที่ 4 Correctness

อธิบายว่า Algorithm ทำงานถูกต้องอย่างไร

**ตัวอย่าง Invariant ของ FIFO Queue:**

ก่อนเริ่มแต่ละรอบของ Algorithm สมาชิกที่อยู่บริเวณ Front คือสมาชิกที่เข้ามาก่อนสมาชิกอื่นทั้งหมดที่ยังไม่ได้รับการประมวลผล

## ส่วนที่ 5 Time Complexity

ทุกกลุ่มต้องวิเคราะห์อย่างน้อย

* `enqueue()`
* `dequeue()`
* `peek()`
* `search()`
* `display()`

## ส่วนที่ 6 Space Complexity

วิเคราะห์กรณี Queue มีสมาชิกสูงสุด **n รายการ** โดยทั่วไปมี Space Complexity เป็น **O(n)** แต่ต้องพิจารณาข้อมูลประกอบของแต่ละ Algorithm ด้วย

## ส่วนที่ 7 Java Implementation

อนุญาตให้ใช้

* `Queue<T>`
* `ArrayDeque<T>`
* `LinkedList<T>`
* `PriorityQueue<T>`
* `Deque<T>`

แต่ต้องอธิบายเหตุผลในการเลือกใช้ Queue แต่ละชนิด

## ส่วนที่ 8 Test Cases

อย่างน้อย **6 Test Cases** โดยต้องมี:

1. Normal Case
2. Empty Queue
3. Single Item
4. Large Queue
5. Special/Edge Case (เช่น Priority เท่ากันหมด)
6. Cancel Case (ยกเลิกรายการที่อยู่กลาง Queue / ยกเลิกรายการที่ไม่มีอยู่จริง) — เพิ่มใหม่ตาม Requirement ร่วม

## ส่วนที่ 9 Algorithm Experiment

กำหนด **n = 100, 1,000, 10,000, 50,000**

วัด Execution Time ของ Algorithm A และ B ด้วยวิธีตามข้อกำหนดร่วม ได้แก่ `nanoTime`, Warm-up, Seed คงที่ และเฉลี่ย 5 รอบ แล้ววิเคราะห์ว่าผลสอดคล้องกับทฤษฎีหรือไม่

## ส่วนที่ 10 เปรียบเทียบ Algorithms

เปรียบเทียบ Algorithm A และ Algorithm B อย่างเป็นระบบ โดยพิจารณาด้านต่าง ๆ เช่น

* วิธีการทำงาน
* Time Complexity
* Space Complexity
* Execution Time
* ข้อดี
* ข้อจำกัด
* ความเหมาะสมในการใช้งาน

## สิ่งที่ต้องส่ง

1. Problem Analysis
2. Queue Design
3. Pseudocode Algorithm A
4. Pseudocode Algorithm B
5. Queue Trace อย่างน้อย 10 Operations
6. Correctness / Invariant
7. Time Complexity
8. Space Complexity
9. Java Program
10. Test Cases อย่างน้อย 6 กรณี
11. Experimental Comparison
12. สรุปเปรียบเทียบ Algorithm A และ B
13. รายงาน
14. Slide Presentation
15. GitHub Repository
