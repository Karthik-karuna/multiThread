package multiThread;



class Restaurant {
    private boolean isFoodReady = false;

    public synchronized void waitForFood() {
        while (!isFoodReady) {
            try {
                System.out.println("Customer: Food is not ready yet. Waiting...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Customer: Received notification! Eating the food now.");
    }

    public synchronized void prepareFood() {
        try {
            System.out.println("Chef: Preparing food...");
            Thread.sleep(2000); // Simulate cooking time
            isFoodReady = true;
            System.out.println("Chef: Food is ready! Calling notify()...");
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class FoodOrderApp {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        Thread customer = new Thread(() -> restaurant.waitForFood());
        Thread chef = new Thread(() -> restaurant.prepareFood());

        customer.start();
        chef.start();
    }
}
