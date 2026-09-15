# Round Robin Lab — Process Scheduling

**IB Computer Science 2 (HL) · Bridge P3 · A1.3.3**

This is the first task in the course where the operating-system idea and the code
you write are the same object. The scheduling you traced on the board on Day 18 is
the scheduling your program has to perform.

---

## 1. You have not done queues yet. That is fine.

`Queue.java` is written for you, complete. You do not have to build one and you do
not have to know how it works inside — that comes later in the year, in the Abstract
Data Types unit, where you will write your own.

A queue is a line. Items join at the back, items leave from the front. First in,
first out. Five operations, and you will use all five:

| Operation | What it does |
|---|---|
| `enqueue(item)` | join the back of the line |
| `dequeue()` | remove and return the item at the front |
| `peek()` | look at the front item **without** removing it |
| `isEmpty()` | true when the line is empty |
| `size()` | how many items are in line |

`Queue<Process>` holds `Process` objects the same way `ArrayList<String>` holds
Strings.

**Start by running `QueueDemo.java`.** It is two minutes long and it is the whole
queue lesson:

```
javac QueueDemo.java
java QueueDemo
```

Predict each line before you run it.

**Why a queue and not an array?** Because round robin *is* a queue. The operating
system keeps the processes that are ready to run in a **ready queue**. It takes the
one at the front, runs it for one quantum, and if it is not finished it puts it at
the back — behind everybody who was waiting. That single sentence is the algorithm,
and `dequeue` … `enqueue` is that sentence in Java.

⚠ **There are two queues in this program, and mixing them up is the commonest way
to get this lab wrong.**

- the **arrivals** queue — processes that have not shown up yet. `RoundRobinReader`
  hands you this one, already in arrival order.
- the **ready** queue — processes that have arrived and are waiting for the CPU.
  **You build this one.**

---

## 2. The files

| File | Status |
|---|---|
| `Queue.java` | **given, complete** — do not modify |
| `RoundRobinReader.java` | **given, complete** — reads the data file, do not modify |
| `QueueDemo.java` | **given** — run it first |
| `Process.java` | **given except two methods** — `getTurnaroundTime()` and `getWaitTime()` are yours |
| `RoundRobinProcess.java` | **yours** — `scheduleTimeUnits()` and `displayStats()` |
| `data.txt`, `day18.txt` | test data |
| `expected_data.txt`, `expected_day18.txt` | what your program must print for each |

Compile and run:

```
javac *.java
java RoundRobinProcess              (uses data.txt)
java RoundRobinProcess day18.txt    (uses day18.txt)
```

---

## 3. The data file format

```
4              how many processes
3              the quantum — the length of one time slice
0,P1,12        arrival time , name , execution time (its burst)
2,P2,9
7,P3,4
11,P4,5
```

Assume one arrival per time unit at most. Names are unique.

---

## 4. What your program prints

One line per time unit, then the statistics. `data.txt` must produce exactly this
(it is in `expected_data.txt`, so you can compare):

```
0: P1
1: P1
2: P1
3: P2
...
23: P2 (Complete)
24: P3 (Complete)
25: P1
26: P1
27: P1 (Complete)
28: P4
29: P4 (Complete)
TOTAL TIME and WAIT TIME for each process
P2 turn around time: 22 wait time: 13
P3 turn around time: 18 wait time: 14
P1 turn around time: 28 wait time: 16
P4 turn around time: 19 wait time: 14
Average wait time: 14.25
```

Note that the statistics are listed **in the order the processes completed**, not
in the order they arrived.

**Definitions — these are the OS definitions, get them right on paper first:**

- **turnaround time** = time from **arrival** to **completion**. How long the
  process was in the system.
- **wait time** = turnaround time − execution time. How much of that time it spent
  *not* running.
- **average wait time** = the mean wait time over all processes.

**Tie-break rule:** a process that arrives at exactly the moment a time slice ends
joins the ready queue **ahead of** the process that was just pre-empted. (Neither
data file depends on this, but it is the convention we traced in class.)

If the ready queue is empty and processes are still to come, the CPU is idle for
that time unit. Print `IDLE`.

---

## 5. ⚠ Your self-check

`day18.txt` is the process set we traced on the board on Day 18 — four processes,
all arriving at time 0, quantum 4, bursts 5, 3, 8 and 6.

**Your program must print an average wait time of 10.75 for it.**

We worked that number out by hand together. If your program prints anything else,
the program is wrong, not the trace. The full expected output is in
`expected_day18.txt`.

Then do what a real test does: **make two data files of your own**, work them out
by hand, and check your program against your own arithmetic. Hand those files in
with the lab.

---

## 6. What to hand in

1. `RoundRobinProcess.java` and `Process.java` with your code in them.
2. Your two test data files, plus the hand-worked trace for each — on paper or as
   a text file.
3. One or two sentences: round robin gave the **worst** average wait of the three
   algorithms we compared on Day 18. Say why an interactive operating system uses
   it anyway.

That last question is the one that is worth marks on Paper 1.

---

## 7. If you get stuck

- **Average wait far too low** → a process kept the CPU past its quantum. The
  requeue step is the one people drop.
- **`NoSuchElementException`** → you called `dequeue()` on an empty queue. Guard
  every dequeue with `isEmpty()`.
- **Average prints as a whole number** → integer division. Cast:
  `(double) totalWait / count`.
- **Infinite loop** → your loop condition never becomes false. Every time round the
  loop, either the clock advances or a process finishes.
- **Off by one on completion time** → a process that finishes during the time unit
  labelled 27 has a completion time of 28. It occupied the CPU *through* unit 27.
