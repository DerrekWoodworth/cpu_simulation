package implementations;

import interfaces.CpuScheduler;
import objects.OurProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotteryScheduling extends CpuScheduler {
    private ArrayList<OurProcess> listOfProecess;
    private ArrayList<OurProcess> lotteryDraw;
    private Random random;
    private OurProcess luckyProcess;

    public LotteryScheduling() {
        listOfProecess = new ArrayList<OurProcess>();
        lotteryDraw = new ArrayList<OurProcess>();
        random = new Random();
    }
    public void addProcess(OurProcess toAdd) {
        listOfProecess.add(toAdd);
        lotteryDraw.add(toAdd);
    }

    public OurProcess getCurrentProcess() {
        if (luckyProcess.getHasLeft() == 0)
            schedule();
        return luckyProcess;
    }

    public void schedule() {
        // IF there is no process scheduled
        if(luckyProcess != null)
            removeIfFinished(luckyProcess);
        // Randomly select the process
        int indexOfProcess = random.nextInt(lotteryDraw.size());

        // Get the process
        luckyProcess = lotteryDraw.get(indexOfProcess);

        // Because it got scheduled, reduce its lottery chances back to one
        while(lotteryDraw.remove(luckyProcess)); // Delete it from the lottery

        // Add all of the processes to the lottery
        listOfProecess.stream()
                .forEach(lotteryDraw::add);
    }

    @Override
    public boolean hasMore() {
        return false;
    }

    public void removeIfFinished(OurProcess process) {
        if(process.getHasLeft() == 0) {
            while(lotteryDraw.remove(luckyProcess));
            listOfProecess.remove(process);
            addToFinished(process);
        }
    }
}
