import implementations.FairShareScheduling;
import implementations.LotteryScheduling;
import implementations.MultilevelFeedbackScheduler;
import implementations.RoundRobin;
import objects.ProcessGenerator;
import objects.Runner;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        System.out.println("RoundRobin");
        // CPU scheduling method --- Round Robin
        RoundRobin rr = new RoundRobin(5);

        // Classes to handle generating processes
        ProcessGenerator pg = new ProcessGenerator();

        // Runner to simulate running processes
        Runner runner = new Runner(pg,rr);
        // Add ten processes
        IntStream.range(0,10).forEach(e -> runner.addProcess());
        // Step 20 times
        IntStream.range(0,20).forEach(e -> runner.step());
        // Add ten more processes
        IntStream.range(0,10).forEach(e -> runner.addProcess());
        runner.runUntilDone();
        runner.printFinsihedProcessStats();
        System.out.println();

        System.out.println("MultilevelFeedbackScheduler");
        // CPU scheduling method -- MFS
        MultilevelFeedbackScheduler multilevelFeedbackScheduler = new MultilevelFeedbackScheduler(new int[]{5, 15, 10});

        // Classes to handle generating processes
        ProcessGenerator pgMFS = new ProcessGenerator();

        // Runner to simulate running processes
        Runner runnerMFS = new Runner(pgMFS,multilevelFeedbackScheduler);
        // Add ten processes
        IntStream.range(0,10).forEach(e -> runnerMFS.addProcess());
        // Step 20 times
        IntStream.range(0,20).forEach(e -> runnerMFS.step());
        // Add ten more processes
        IntStream.range(0,10).forEach(e -> runnerMFS.addProcess());
        runner.runUntilDone();
        runner.printFinsihedProcessStats();
        System.out.println();

        System.out.println("Fair Share");
        // CPU scheduling method -- Fair Share
        FairShareScheduling fairShareScheduling = new FairShareScheduling();

        // Classes to handle generating processes
        ProcessGenerator pgFSS = new ProcessGenerator();

        // Runner to simulate running processes
        Runner runnerFFS = new Runner(pgFSS,fairShareScheduling);
        // Add ten processes
        IntStream.range(0,10).forEach(e -> runnerFFS.addProcess());
        // Step 20 times
        IntStream.range(0,20).forEach(e -> runnerFFS.step());
        // Add ten more processes
        IntStream.range(0,10).forEach(e -> runnerFFS.addProcess());
        runner.runUntilDone();
        runner.printFinsihedProcessStats();
        System.out.println();

        System.out.println("Lottery Scheduler");
        // CPU scheduling method -- Lottery Scheduler
        LotteryScheduling lotteryScheduling = new LotteryScheduling();

        // Classes to handle generating processes
        ProcessGenerator pgLS = new ProcessGenerator();

        // Runner to simulate running processes
        Runner runnerLS = new Runner(pgLS, lotteryScheduling);
        // Add ten processes
        IntStream.range(0,10).forEach(e -> runnerLS.addProcess());
        // Step 20 times
        IntStream.range(0,20).forEach(e -> runnerLS.step());
        // Add ten more processes
        IntStream.range(0,10).forEach(e -> runnerLS.addProcess());
        runner.runUntilDone();
        runner.printFinsihedProcessStats();







    }
}
