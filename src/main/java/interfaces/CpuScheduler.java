package interfaces;

import objects.OurProcess;

import java.util.ArrayList;
import java.util.List;

public abstract class CpuScheduler {
    private ArrayList<OurProcess> finished;
    protected int time;
    public CpuScheduler() {
        finished = new ArrayList<OurProcess>();
        time = 0;
    }
    public abstract void addProcess(OurProcess toAdd);
    public abstract OurProcess getCurrentProcess();
    public abstract void schedule();
    public void tick() {
        time++;
    }
    protected void addToFinished(OurProcess process) {
        finished.add(process);
    }
    public ArrayList<OurProcess> getFinishedProcesses(){
        return finished;
    }
    public abstract boolean hasMore();
}
