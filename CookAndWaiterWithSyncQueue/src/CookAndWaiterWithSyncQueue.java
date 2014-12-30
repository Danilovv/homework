import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Vladimir_Danilov on 30-Dec-14.
 */
public class CookAndWaiterWithSyncQueue {

    static final BlockingQueue<String> queue = new SynchronousQueue<>();
    static ExecutorService service = Executors.newCachedThreadPool();
    static String[] menu = {"Steak", "Pasta", "IceCream", "Soup"};

    static class Cook implements Runnable {

        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                int pause = 1000 + random.nextInt(2000);
                Utils.pause(pause);
                String readyMeal = menu[random.nextInt(menu.length)];
                try {
                    queue.put(readyMeal);
                    System.out.printf("Cook: %s done! %n", readyMeal);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static class Waiter implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String tookMeal = queue.take();
                    System.out.printf("Waiter: got %s. %n", tookMeal);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        service.execute(new Cook());
        service.execute(new Waiter());
    }
}
