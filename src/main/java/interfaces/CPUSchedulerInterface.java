package interfaces;

public interface CPUSchedulerInterface {
    void addProcess(Process toAdd);
    Process getNextProcess();
    Process[] getAllFinishedProcesses();
    void schedule();
}
