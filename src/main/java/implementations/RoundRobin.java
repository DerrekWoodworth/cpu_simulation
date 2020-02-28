
package implementations;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import interfaces.CpuScheduler;
import objects.OurProcess;

public class RoundRobin extends CpuScheduler {
	private ArrayList<OurProcess> myProcesses = new ArrayList<>();
	private int timeQuantum;
	private int nextTime;

    public RoundRobin(int timeQuantum) {
		super();
		this.myProcesses = new ArrayList<>();
		this.timeQuantum = timeQuantum;
	}

    @Override
    public void addProcess(OurProcess toAdd) {
        myProcesses.add(toAdd);
    }

    public OurProcess getCurrentProcess()
    {

      return myProcesses.size() > 0 ? myProcesses.get(0) : new OurProcess(1,2);
    }

    public void schedule()
    {
        // If the current process needs to be preempted
        if(time == nextTime) {
            // Add to the end of the queue
                myProcesses.add(myProcesses.remove(0));
                nextTime += timeQuantum;
        }

        // Check if the process finished
        removeIfFinished();
    }

    public void removeIfFinished() {
      List<OurProcess> toRemove = myProcesses.stream()
               .filter(p -> p.getHasLeft() == 0).collect(Collectors.toList());

      toRemove.stream().forEach(process -> {
                   if(process == null)
                       System.out.println("This should not happen");
                   myProcesses.remove(process);
                   addToFinished(process);
               });


    }
    public boolean hasMore() {
        return myProcesses.size() > 0;
    }
}