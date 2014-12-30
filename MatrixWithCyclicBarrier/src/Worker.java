


/**
* Created by Vladimir_Danilov on 26-Dec-14.
*/
public class Worker {

    private static final Runnable POISON_PILL = new Runnable() {
        @Override
        public void run() {

        }
    };

    final private BlockingQueueLockUnlock<Runnable> _tasks = new BlockingQueueLockUnlock<>();

    private class WorkerTask implements Runnable {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                task = _tasks.poll();
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

    public Worker() {
        new Thread(new WorkerTask()).start();
    }

    public void shutdown() {
        execute(POISON_PILL);
    }

    public void execute(Runnable task) {
        _tasks.offer(task);
    }
}
