package objects;

public class Process {
    private final String id;
    private final int burstTime;

    public int getHasLeft() {
        return hasLeft;
    }

    // Keep of track of how long the process has left
    private int hasLeft;

    // When the process was added to the queue, first hit the cpu, and when it finished
    private int added, start = -1, end = -1;

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    private int waitingTime, turnAroundTime;

    public Process(String id, int burstTime, int added) {
        this.id = id;
        this.burstTime = burstTime;
        this.added = added;

        hasLeft = burstTime;
    }

    /**
     *  "Runs" the process for one time unit
     * @return
     * Returns true if the process needs more time, false if it is finished
     */
    public boolean runForOneTick(int time) {
        if(start == -1) {
            start = time;
        } else if (hasLeft == 1) {
            end = time + 1;
            // Generate the Waiting time and the turnAround time
            waitingTime = end - start;
            turnAroundTime = end - added;
        }
        return hasLeft == 0 ? false : --hasLeft > 0;
    }

}
