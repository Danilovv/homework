

/**
 * Created by Vladimir_Danilov on 26-Dec-14.
 */
public class WorkerExample {

    public static void main(String[] args) {

        System.out.println("MAIN START");

        //PrimitiveWorker primitiveWorker = new PrimitiveWorker();
        Worker worker = new Worker();

        worker.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from "
                        + Thread.currentThread().getName());
            }
        });

        worker.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Another hello from "
                        + Thread.currentThread().getName());
            }
        });

        class Task implements Runnable {
            @Override
            public void run() {
                System.out.println("task started");
                Utils.pause(5000);
                System.out.println("task finished");
            }
        }

        System.out.println("task 1");
        worker.execute(new Task());
        System.out.println("task 2");
        worker.execute(new Task());
        System.out.println("MAIN FINISHED");
//
        worker.shutdown();

    }

}



