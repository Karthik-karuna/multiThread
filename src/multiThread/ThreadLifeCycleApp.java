package multiThread;



public class ThreadLifeCycleApp {
    public static void main(String[] args) throws InterruptedException {
        Thread orderThread = new Thread(() -> {
            try {
                // RUNNABLE state during processing
                System.out.println("2. Inside run() - Thread state: " + Thread.currentThread().getState());
                System.out.println("Processing online order...");

                // Going to sleep for 2 seconds -> TIMED_WAITING state
                Thread.sleep(2000);
                System.out.println("Order processing continued after sleep.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 1. Created thread -> NEW state
        System.out.println("1. After creation - Thread state: " + orderThread.getState());

        orderThread.start();
        // Give it a moment to enter sleep
        Thread.sleep(500);
        System.out.println("3. During sleep - Thread state: " + orderThread.getState());

        // Wait for thread to finish completely
        orderThread.join();
        System.out.println("4. After completion - Thread state: " + orderThread.getState());
    }
}