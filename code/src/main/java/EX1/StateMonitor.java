package EX1;
/*
 * Exercicio 1
 * Faça um programa em Java que consulte periodicamente o estado de um conjunto de threads.
 *
 * @author Danilo Sambugaro created on 27/08/2019 inside the package - EX1
 *
 */

import java.util.LinkedList;
import java.util.List;

public class StateMonitor {

    public static void main(String[] args) throws InterruptedException {
        // Criando instancia das threads para serem monitoradas
        WorkerNew workerNew = new WorkerNew();
        WorkerRunnable workerRunnable = new WorkerRunnable();
        WorkerWaiting workerWaiting = new WorkerWaiting();
        WorkerTerminated workerTerminated = new WorkerTerminated();
        WorkerTimedWaiting workerTimedWaiting = new WorkerTimedWaiting();
        Thread workerNewThread = new Thread(workerNew, "WorkerNew");
        Thread workerRunnableThread = new Thread(workerRunnable, "WorkerRunnable");
        Thread workerWaitingThread = new Thread(workerWaiting, "WorkerWaiting");
        Thread workerTerminatedThread = new Thread(workerTerminated, "WorkerTerminated");
        Thread workerTimedWaitingThread = new Thread(workerTimedWaiting, "WorkerTimedWaiting");

        // Iniciando Threads necessárias
        workerRunnableThread.start();
        workerWaitingThread.start();
        workerTimedWaitingThread.start();
        workerTerminatedThread.start();

        // Aguardando Threads necessárias
        workerTerminatedThread.join();

        // Adicionando threads em uma lista
        List<Thread> monitoredThreads = new LinkedList<Thread>();

        monitoredThreads.add(workerNewThread);
        monitoredThreads.add(workerRunnableThread);
        monitoredThreads.add(workerWaitingThread);
        monitoredThreads.add(workerTerminatedThread);
        monitoredThreads.add(workerTimedWaitingThread);

        while (true) {
            for (Thread t : monitoredThreads) {
                System.out.println("Thread: " + t.getName() + " Estado: " + t.getState());

            }

            System.out.println("\n");
            Thread.sleep(1000);
        }
    }
}
