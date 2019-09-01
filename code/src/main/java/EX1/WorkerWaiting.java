package EX1;
/*
 *
 * Esta classe representa uma thread que ficará esperando indefinidamente por uma thread executando o WorkerRunnable,
 * com o intuito de se conseguir observar o estado WAITING
 *
 * @author Danilo Sambugaro created on 27/08/2019 inside the package - EX1
 *
 */

public class WorkerWaiting implements Runnable {

    @Override
    public void run() {
        WorkerRunnable workerRunnable = new WorkerRunnable();
        Thread workerRunnableThread = new Thread(workerRunnable);
        workerRunnableThread.start();

        try {
            workerRunnableThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
