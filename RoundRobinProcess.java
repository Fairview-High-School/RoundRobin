public class RoundRobinProcess {
    Queue<Process> processQueue; //processes that have not arrived yet
    Queue<Process> waitQueue; //processes that have arrived and are waiting for CPU time
    Process runningProcess; //current running process
    int quantumLength;
    ArrayList<Process> completedProcesses;
    ArrayList<Integer> completionTimes;

    public RoundRobinProcess(Queue<Process> processQueue, int quantumLength) {
        this.processQueue = processQueue;
        this.quantumLength = quantumLength;
        waitQueue = new LinkedList<Process>();
        completedProcesses = new ArrayList<>();
        completionTimes = new ArrayList<>();
    }

    /*This method will takes Processes out of the processQueue at their arrival time, and adds them to the waitQueue
     * methods are dequeued from the waitQueue FIFO, and are given one quantumLength to run (if they need it)
     * completed processes are added to the completedProcesses list (and the completion time stored in a parallel list)
     * uncompleted processes are moved to the back of the wait queue
     * at the end of the method, processQueue and waitQueue should be empty, runningProcess will be null
     * and each process and its corresponding end time will be stored in completedProcesses and completionTimes lists
     */
    public void scheduleTimeUnits() {
        int timeUnit = 0; //current time unit in simulation - goes up by 1 each iteration
        int runTime = 0; //current amaount of time one process has been running -- compared against the quantumLength
        
        //keep repeating as long as 
            // there is a process that that has not yet arrived
            // there is a process in the wait queue
            // OR a process is still running
        while ((!processQueue.isEmpty() || !waitQueue.isEmpty() || runningProcess != null))
        {   
            System.out.print(timeUnit + ": ");
            
            //If a process arrives at this time, put it in the wait queue
            if (!processQueue.isEmpty())
                if (processQueue.peek().getArrivalTime() == timeUnit)
                    waitQueue.add(processQueue.remove());

            //if there is no running process, dequeue one from the wait queue
            //and set the runTime to 0
            if (runningProcess == null) {
                if (!waitQueue.isEmpty())
                    runningProcess = waitQueue.remove();
                    runTime = 0;
            }
            
            //if we now have a running process
            if (runningProcess != null)
            {
                System.out.print(runningProcess.getName());
                //add one to the runtime for this process
                runTime++;
                //decrease its remaining execution time
                runningProcess.decreaseRemainingExecutionTime();
                
                //if its remaining execution time is 0
                //it is no longer the running process - track its completion
                if (runningProcess.getRemainingExecutionTime() == 0) {
                    System.out.print (" (Complete)");
                    completedProcesses.add(runningProcess);//add to completed processes
                    completionTimes.add(timeUnit);//store its completioni time
                    //it is no longer the running process
                    runningProcess = null; 
                }

                //if its current runTime has reached the quantumLength
                //it is no longer the running process - put it back in the waitQueue             
                else if (runTime == quantumLength) {
                    waitQueue.add(runningProcess);
                    runningProcess = null;
                    runTime = 0;
                }            
            }
            System.out.println();
            
            //each iteration of the while loop, count up the timeUnit
            timeUnit++;
        }
    }

    public void displayStats() {
        int totalWaitTime = 0;
        for (int i=0; i<completedProcesses.size(); i++)
        {
            int turnAroundTime = completionTimes.get(i) - completedProcesses.get(i).getArrivalTime() + 1;
            int waitTime = turnAroundTime - completedProcesses.get(i).getExecutionTime();
            totalWaitTime += waitTime;
            System.out.println(completedProcesses.get(i).getName() + 
                                " turn around time: " + turnAroundTime +
                                " wait time: " + waitTime);                   
        }
        System.out.println("Average wait time: " + (double)totalWaitTime/completedProcesses.size());
    }
	/* Now, write the code to process the Queue of Processes using the Round Robin algorithm.
	 * Display each time segment, and the process that is executing at that time segment.
	 * Indicate when each process is complete.
	 * Display the total turn around time, and total wait time for each process.
	 * Also display the average wait time.  (the average of the wait time of all processes)
	 
	 * I would recommend creating a RoundRobinProcess constructor, and calling any necessary
	 * methods on it to create the appropriate result.
	 */
	public static void main(String[] args) throws FileNotFoundException {
        String filename = "data.txt";
        RoundRobinReader rr = new RoundRobinReader(filename);

        System.out.println(processQueue);
		
		Queue<Process> processQueue = rr.getProcessQueue();
		int quantumLength = rr.getQuantumLength();
		

		RoundRobinProcess rr = new RoundRobinProcess(processQueue, quantumLength);
        rr.scheduleTimeUnits();  // modify to use whatever methods you write
        rr.displayStats(); // modify to use whatever methods you write	 
    }

}