package EX1;
/*
 * Esta classe representa uma thread que ficará esperando por um periodo de tempo por uma thread executando o
 * WorkerRunnable, com o intuito de se conseguir observar o estado TIMED_WAITING
 *
 * @author Danilo Sambugaro created on 27/08/2019 inside the package - EX1
 *
 */

public class WorkerTimedWaiting implements Runnable{
    @Override
    public void run() {

        WorkerRunnable workerRunnable = new WorkerRunnable();
        Thread workerRunnableThread = new Thread(workerRunnable);
        workerRunnableThread.start();

        while (true) {
            try {
                workerRunnableThread.join(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
