
package implementations;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.CpuScheduler;
import objects.OurProcess;

public class FairShareScheduling extends CpuScheduler {
    private ArrayList<OurProcess> myProcesses = new ArrayList<>();
    private static int TOTAL_CYCLES = 100;
    private int cpuShare;
    private int nextTime;

    public FairShareScheduling() {
        super();
        this.myProcesses = new ArrayList<>();
    }

    @Override
    public void addProcess(OurProcess toAdd) {
        myProcesses.add(toAdd);
        cpuShare = TOTAL_CYCLES / myProcesses.size();
        nextTime = time + cpuShare;

    }

    public OurProcess getCurrentProcess()
    {
        return myProcesses.size() > 0 ? myProcesses.get(0) : new OurProcess(1,2);
    }

    public void schedule()
    {
        // If the current process needs to be preempted
        if(time >= nextTime) {
            // Add to the end of the queue
            myProcesses.add(myProcesses.remove(0));
            nextTime += cpuShare;
        }

        // Check if the process finished
        removeIfFinished();
    }

    public void removeIfFinished() {
        List<OurProcess> toRemove = myProcesses.stream()
                .filter(p -> p.getHasLeft() == 0).collect(Collectors.toList());

        toRemove.stream().forEach(process -> {
            myProcesses.remove(process);
            addToFinished(process);
        });
    }

    public boolean hasMore() {
        return myProcesses.size() > 0;
    }
}