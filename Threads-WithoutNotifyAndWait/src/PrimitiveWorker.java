import java.util.LinkedList;
import java.util.Queue;

/**
* Created by Vladimir_Danilov on 26-Dec-14.
*/
public class PrimitiveWorker {

    private static final Runnable POISON_PILL = new Runnable() {
        @Override
        public void run() {

        }
    };

    final private Queue<Runnable> _tasks = new LinkedList<>();

    private class WorkerTask implements Runnable {
        @Override
        public void run() {

            Runnable task;
            while (true) {

                while(!hasNewTask()) {

                }

                synchronized (_tasks) {
                    task = _tasks.poll();
                }

                if(task == POISON_PILL) {
                    break;
                }

                try {
                    task.run();
                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public PrimitiveWorker() {
        new Thread(new WorkerTask()).start();
    }

    public void shutdown() {
        execute(POISON_PILL);
    }

    private boolean hasNewTask() {
        return !_tasks.isEmpty();
    }

    public void execute(Runnable task) {
        synchronized (_tasks) {
            _tasks.offer(task);
//            _tasks.notify();
            System.out.println("added task");
        }
    }
}
