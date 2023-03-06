//import java.util.*;
//
//class Task {
//    private int id;
//    private int arrivalTime;
//    private int burstTime;
//    private int priority;
//    private int remainingTime;
//
//    public Task(int id, int arrivalTime, int burstTime, int priority) {
//        this.id = id;
//        this.arrivalTime = arrivalTime;
//        this.burstTime = burstTime;
//        this.priority = priority;
//        this.remainingTime = burstTime;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public int getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public int getBurstTime() {
//        return burstTime;
//    }
//
//    public int getPriority() {
//        return priority;
//    }
//
//    public int getRemainingTime() {
//        return remainingTime;
//    }
//
//    public void setRemainingTime(int remainingTime) {
//        this.remainingTime = remainingTime;
//    }
//}
//
//public class Scheduler {
//    private List<Task> tasks;
//    private int currentTime;
//
//    public Scheduler() {
//        this.tasks = new ArrayList<>();
//        this.currentTime = 0;
//    }
//
//    public void addTask(Task task) {
//        tasks.add(task);
//    }
//
//    // First-come, first-served (FCFS) scheduling algorithm
//    public void FCFS() {
//        System.out.println("First-come, first-served (FCFS) scheduling algorithm");
//        int totalWaitingTime = 0;
//        int totalTurnaroundTime = 0;
//        int numTasks = tasks.size();
//
//        for (Task task : tasks) {
//            int waitingTime = currentTime - task.getArrivalTime();
//            totalWaitingTime += waitingTime;
//            int turnaroundTime = waitingTime + task.getBurstTime();
//            totalTurnaroundTime += turnaroundTime;
//            currentTime += task.getBurstTime();
//
//            System.out.println("Task " + task.getId() + " finished at time " + currentTime +
//                    ". Waiting time: " + waitingTime + ". Turnaround time: " + turnaroundTime);
//        }
//
//        double avgWaitingTime = (double) totalWaitingTime / numTasks;
//        double avgTurnaroundTime = (double) totalTurnaroundTime / numTasks;
//        System.out.println("Average waiting time: " + avgWaitingTime);
//        System.out.println("Average turnaround time: " + avgTurnaroundTime);
//    }
//
//    // Priority scheduling algorithm
//    public void priorityScheduling() {
//        System.out.println("Priority scheduling algorithm");
//        int totalWaitingTime = 0;
//        int totalTurnaroundTime = 0;
//        int numTasks = tasks.size();
//
//        tasks.sort(Comparator.comparingInt(Task::getPriority).reversed().thenComparing(Task::getArrivalTime));
//
//        while (!tasks.isEmpty()) {
//            Task task = tasks.get(0);
//            tasks.remove(0);
//
//            int waitingTime = currentTime - task.getArrivalTime();
//            totalWaitingTime += waitingTime;
//            int turnaroundTime = waitingTime + task.getBurstTime();
//            totalTurnaroundTime += turnaroundTime;
//
//            currentTime += task.getBurstTime();
//
//            System.out.println("Task " + task.getId() + " finished at time " + currentTime +
//                    ". Waiting time: " + waitingTime + ". Turnaround time: " + turnaroundTime);
//
//            // update remaining time for tasks that arrived during current task's burst time
//            for (Task t : tasks) {
//                if (t.getArrivalTime() <= currentTime) {
//                    t.setRemainingTime(t.getRemainingTime() - task.getBurstTime());
//                } else {
//                    break;
//                }
//            }
//
//            // add the updated tasks to the front of the list in priority order
//            tasks.addAll(0, tasks.stream().filter(t -> t.getRemainingTime() > 0).sorted(
//                    Comparator.comparingInt(Task::getPriority).reversed().thenComparing(Task::getArrivalTime)).toList());
//        }
//
//        double avgWaitingTime = (double) totalWaitingTime / numTasks;
//        double avgTurnaroundTime = (double) totalTurnaroundTime / numTasks;
//        System.out.println("Average waiting time: " + avgWaitingTime);
//        System.out.println("Average turnaround time: " + avgTurnaroundTime);
//    }
//
//    // Round-robin (RR) scheduling algorithm
//    public void roundRobin() {
//        System.out.println("Round-robin (RR) scheduling algorithm with quantum = 10ms");
//        int totalWaitingTime = 0;
//        int totalTurnaroundTime = 0;
//        int numTasks = tasks.size();
//        Queue<Task> queue = new LinkedList<>();
//
//        for (Task task : tasks) {
//            queue.add(task);
//        }
//
//        while (!queue.isEmpty()) {
//            Task task = queue.poll();
//            int remainingTime = task.getRemainingTime();
//
//            if (remainingTime <= 10) {
//                currentTime += remainingTime;
//                totalWaitingTime += currentTime - task.getArrivalTime() - task.getBurstTime();
//                totalTurnaroundTime += currentTime - task.getArrivalTime();
//                System.out.println("Task " + task.getId() + " finished at time " + currentTime +
//                        ". Waiting time: " + (currentTime - task.getArrivalTime() - task.getBurstTime()) +
//                        ". Turnaround time: " + (currentTime - task.getArrivalTime()));
//            } else {
//                currentTime += 10;
//                totalWaitingTime += 10;
//                remainingTime -= 10;
//                task.setRemainingTime(remainingTime);
//                queue.add(task);
//            }
//
//            // add new arrivals to the queue
//            for (Task t : tasks) {
//                if (t.getArrivalTime() <= currentTime && !queue.contains(t)) {
//                    queue.add(t);
//                }
//            }
//        }
//
//        double avgWaitingTime = (double) totalWaitingTime / numTasks;
//        double avgTurnaroundTime = (double) totalTurnaroundTime / numTasks;
//        System.out.println("Average waiting time: " + avgWaitingTime);
//        System.out.println("Average turnaround time: " + avgTurnaroundTime);
//    }
//
//    public static void main(String[] args) {
//        Scheduler scheduler = new Scheduler();
//
//        // create tasks
//        Task t1 = new Task(1, 0, 10, 3);
//        Task t2 = new Task(2, 1, 1, 1);
//        Task t3 = new Task(3, 2, 2, 4);
//        Task t4 = new Task(4, 3, 1, 5);
//        Task t5 = new Task(5, 4, 5, 2);
//
//        // add tasks to scheduler
//        scheduler.addTask(t1);
//        scheduler.addTask(t2);
//        scheduler.addTask(t3);
//        scheduler.addTask(t4);
//        scheduler.addTask(t5);
//
//        // run scheduling algorithms
//        scheduler.FCFS();
//        scheduler.priorityScheduling();
//        scheduler.roundRobin();
//    }
//}
