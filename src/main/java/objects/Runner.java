package objects;

import interfaces.CpuScheduler;
import interfaces.ProcessGeneratorInterface;

public class Runner {
    private ProcessGeneratorInterface processGenerator;
    private CpuScheduler cpuScheduler;

    // Running details
    private int currentTime;

    public Runner(ProcessGeneratorInterface processGenerator, CpuScheduler cpuScheduler) {
        this.processGenerator = processGenerator;
        this.cpuScheduler = cpuScheduler;

        currentTime = 0;
    }

    public void addProcess() {
        cpuScheduler.addProcess(
                processGenerator.generateProcess().addToScheduler(currentTime)
        );
    }
    public void step() {
        cpuScheduler.schedule();
        OurProcess currentlyRunning = cpuScheduler.getNextProcess();
        currentlyRunning.runForOneTick(currentTime);

        currentTime++;
    }

    public void printFinsihedProcessStats(){
        cpuScheduler.getFinishedProcesses().stream()
                .forEach(System.out::println);
    }

}
