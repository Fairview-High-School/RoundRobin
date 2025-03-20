/* Process.java
 * Represents a single Process.  Keeps track of its arrivalTime, an identifier(name)
 * and the total time that it needs to execute.
 * 
 * You may want to create additional instance variable(s) if the RoundRobin algorithm
 * requires that each Process track its own additional value(s).  If so, additional changes
 * such as initializing the variable in the constructor, and adding accessor and/or mutator
 * method(s) for the new instance value may be needed.
 */
public class Process {
    private int arrivalTime;
    private String name;
    private int executionTime;

    public Process(int arrivalTime, String name, int executionTime) {
        this.arrivalTime = arrivalTime;
        this.name = name;
        this.executionTime = executionTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getName() {
        return name;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return "arrivalTime=" + arrivalTime +
                ", name='" + name + '\'' +
                ", executionTime=" + executionTime;
    }
}