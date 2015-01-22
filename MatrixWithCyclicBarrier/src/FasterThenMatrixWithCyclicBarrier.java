import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vladimir_Danilov on 30-Dec-14.
 */
public class FasterThenMatrixWithCyclicBarrier {
    static ExecutorService service = Executors.newCachedThreadPool();
    static Lock lock = new ReentrantLock();
    static double sum = 0.0;
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        System.out.println("Started");
        final double[][] matrix = generateMatrix(10000, 10000);

        CyclicBarrier barrier = new CyclicBarrier(matrix.length, new Runnable() {
            @Override
            public void run() {
                System.out.println("Summary = " + sum);
                long finishTime = System.currentTimeMillis();
                long elapsed = finishTime - startTime;
                System.out.println("Elapsed time is " + elapsed);
            }
        });
        int cores = Runtime.getRuntime().availableProcessors();
        final int numberOfThreads;
        numberOfThreads = (cores <= matrix.length) ? matrix.length : cores;

        {
            int i = 0;
            int j = 0;
            do {

                i++;
                j++;
                if (i >= numberOfThreads) i = 0;
            } while (j < matrix.length);
            System.out.printf("j = %d, i = %d %n", j, i);
        }



        /*{
            int i = 0;
            int j = 0;
            do {
                futures[i].add(service.submit(new RowTask(matrix[j])));
                i++;
                j++;
                if (i >= numberOfThreads) i = 0;
            } while (j < matrix.length);
            System.out.printf("j = %d, i = %d %n", j, i);
        }

        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(new MyRunnable(futures[i], barrier));
        }*/
    }

    static double[][] generateMatrix(int rows, int columns) {
        double[][] matrix = new double[rows][columns];
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }

    static double calculate(double d) {
        double result;
        result = Math.pow (Math.sin(Math.pow(d, Math.E))
                / Math.cos(Math.pow(d, Math.E)), Math.PI);
        return result;
    }

    static class RowTask implements Callable<Double> {
        private double[] _row;

        public RowTask(double[] row) {
            _row = row;
        }

        @Override
        public Double call() {
            double sum = 0.0;
            for(int i=0; i < _row.length; i++) {
                sum += calculate(_row[i]);
            }
            return sum;
        }
    }

    private static class MyRunnable implements Runnable {
        Double [] _vector;

        public MyRunnable(Double[] vector) {
            _vector = vector;
        }

        @Override
        public void run() {
            for(Double doubleNum : _vector) {

                    sum += doubleNum;

            }
        }
    }
}
