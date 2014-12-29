import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vladimir_Danilov on 26-Dec-14.
 */
public class BlockingQueueLockUnlock<T> {

    final Lock lock = new ReentrantLock();
    final Condition notEmpty = lock.newCondition();
    final private Queue<T> _items = new LinkedList<>();

    public void offer(T item) {
        lock.lock();
        try {
            _items.offer(item);
            System.out.println("item offered");
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T poll() {
        lock.lock();
        try {
            System.out.println("item polled");
            while(_items.size() == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return _items.poll();
        } finally {
            lock.unlock();
        }
    }

}
