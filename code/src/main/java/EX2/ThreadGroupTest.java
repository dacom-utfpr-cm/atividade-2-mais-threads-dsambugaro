package EX2;
/*
 * Exercicio 2
 * Faça um programa em Java para testar um conjunto de operações sobre um grupo de threads. Use o ThreadGroup.
 *
 * @author Danilo Sambugaro created on 01/09/2019 inside the package - EX2
 *
 */

import java.util.List;

public class ThreadGroupTest {

    public static void main(String[] args) {

        ThreadGroup threadGroup = new ThreadGroup("Group 1");

        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            Thread workerThread = new Thread(threadGroup, worker, "Worker " + i);
            workerThread.start();
        }

        System.out.println("Estimativa de Threads ativas no grupo " + threadGroup.getName() + ": " + threadGroup.activeCount());
        System.out.println("Prioridade máxima do grupo " + threadGroup.getName() + ": " + threadGroup.getMaxPriority());
        System.out.println("Alterando prioridade máxima do grupo " + threadGroup.getName());
        threadGroup.setMaxPriority(2);
        System.out.println("Nova prioridade máxima do grupo " + threadGroup.getName() + ": " + threadGroup.getMaxPriority());
        System.out.println("O grupo " + threadGroup.getName() + " é Daemon: " + (threadGroup.isDaemon() ? "Sim":"Não"));
        System.out.println("Interrompendo as threads do grupo " + threadGroup.getName());
        threadGroup.interrupt();

    }

}
