package multiThread;



class Warehouse {
    private boolean hasProduct = false;

    // Consumer method: waits if no product is available
    public synchronized void consume(String consumerName) {
        while (!hasProduct) {
            try {
                System.out.println(consumerName + ": Product unavailable. Waiting...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(consumerName + " consumed the product successfully!");
        hasProduct = false; // Reset product status after consumption
    }

    // Producer method: produces a product and wakes up waiting consumers
    public synchronized void produce() {
        try {
            System.out.println("Producer: Manufacturing a new product...");
            Thread.sleep(1500); // Simulate production time
            hasProduct = true;
            System.out.println("Producer: Product is ready! Notifying consumers...");

            // Use notifyAll() to wake up all waiting consumer threads
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ProducerConsumerApp {
    public static void main(String[] args) throws InterruptedException {
        Warehouse warehouse = new Warehouse();

        // Three consumer threads
        Thread c1 = new Thread(() -> warehouse.consume("Consumer-1"));
        Thread c2 = new Thread(() -> warehouse.consume("Consumer-2"));
        Thread c3 = new Thread(() -> warehouse.consume("Consumer-3"));

        // Start all consumers so they go into waiting state
        c1.start();
        c2.start();
        c3.start();

        Thread.sleep(1000); // Small pause to let consumers wait

        // One producer thread
        Thread producer = new Thread(() -> warehouse.produce());
        producer.start();
    }
}
