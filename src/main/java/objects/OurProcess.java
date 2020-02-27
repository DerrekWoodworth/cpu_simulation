package objects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OurProcess {
    private final int id;
    private final int burstTime;

    // Keep of track of how long the process has left
    private int hasLeft;

    // When the process was added to the queue, first hit the cpu, and when it finished
    private int added, start, end;

    private int waitingTime, turnAroundTime;

    public OurProcess(int id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;

        start = end = -1;
        hasLeft = burstTime;
    }

    public int getHasLeft() {
        return hasLeft;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public OurProcess addToScheduler(int added) {
        this.added = added;
        return this;
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

    public String toString() {
       List<Integer> fields = Arrays.asList(id, added, start, end, waitingTime, turnAroundTime);
       List<String> fieldsAsString = fields.stream().map(String::valueOf).collect(Collectors.toList());
        return String.join(" ,", fieldsAsString);
    }

}
