package multiThread;



class BankAccount {
    private int balance = 5000;

    public synchronized void withdraw(int amount) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " trying to withdraw ₹" + amount);

        if (balance >= amount) {
            System.out.println(name + " is processing withdrawal...");
            balance -= amount;
            System.out.println(name + " completed withdrawal. Remaining Balance: ₹" + balance);
        } else {
            System.out.println(name + " withdrawal failed. Insufficient balance!");
        }
    }
}

public class BankSyncApp {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Runnable task = () -> account.withdraw(4000);

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}