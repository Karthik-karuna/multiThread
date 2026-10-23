package multiThread;



class ExamPortal {
    private boolean resultsPublished = false;

    public synchronized void waitForResults(String studentName) {
        while (!resultsPublished) {
            try {
                System.out.println(studentName + " is waiting for exam results...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(studentName + ": Yay! Checked my results and passed!");
    }

    public synchronized void publishResults() {
        System.out.println("Teacher: Publishing exam results for everyone...");
        resultsPublished = true;
        notifyAll();
        System.out.println("Teacher: Called notifyAll()! All students notified.");
    }
}

public class ExamResultsApp {
    public static void main(String[] args) throws InterruptedException {
        ExamPortal portal = new ExamPortal();

        Thread s1 = new Thread(() -> portal.waitForResults("Student-A"));
        Thread s2 = new Thread(() -> portal.waitForResults("Student-B"));
        Thread s3 = new Thread(() -> portal.waitForResults("Student-C"));

        s1.start();
        s2.start();
        s3.start();

        Thread.sleep(1000); // Give students time to go into waiting state

        Thread teacher = new Thread(() -> portal.publishResults());
        teacher.start();
    }
}