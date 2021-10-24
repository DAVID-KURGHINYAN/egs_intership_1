package com.createthread;

public class JobExecutor {
    private static Thread[] jobs;

    public static void runAllJobs(Thread[] mJobs) {
        jobs = mJobs;
        handleRunJobs();
    }

    private static void handleRunJobs() {
        for (Thread job : jobs) {
            job.start();
            try {
                job.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread[] jobs = new Thread[]{new Thread1(), new Thread2(), new Thread1(), new Thread2(), new Thread1()};
        JobExecutor.runAllJobs(jobs);
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            super.run();

            for (int i = 0; i < 11; i++) {
                try {
                    Thread.sleep(500);
                    System.out.print("*");
                    if (i == 10) {
                        System.out.println();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(500);
                System.out.println("*");
                Thread.sleep(500);
                System.out.println("*");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
