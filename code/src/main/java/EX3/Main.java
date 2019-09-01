package EX3;
/*
 * Exercicio 3
 * Faça um programa em Java com threads que exiba os números primos entre 0 e 100000.
 *
 * @author Danilo Sambugaro created on 01/09/2019 inside the package - EX3
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Integer[]> intervals = new LinkedList<Integer[]>();
        List<printPrimeNumber> workers = new LinkedList<printPrimeNumber>();

        // Popula uma lista de intervalos de 0 a 100000 de 1000 em 1000
        for (int i = 0; i < 100000; i += 1000) {
            if (i == 0){
                Integer[] interval = { i , i+1000};
                intervals.add(interval);
            } else {
                Integer[] interval = { i+1 , i+1000};
                intervals.add(interval);
            }
        }

        // Cria uma lista com 4 threads iniciadas
        for (int i = 0; i < 4; i++) {
            printPrimeNumber worker = new printPrimeNumber();
            worker.setHasWork(true);
            worker.setName("Worker " + (i+1));
            worker.start();
            workers.add(worker);
        }

        while (!intervals.isEmpty()) {
            for (printPrimeNumber worker : workers) {
                if (worker.isFinished() && !intervals.isEmpty()) {
                    // Passa um novo intervalo a um worker disponível
                    System.out.println("Iniciando verificação do intervalo de " + intervals.get(0)[0] + " a " + intervals.get(0)[1] + " em " + worker.getName());
                    worker.setInterval(intervals.get(0)[0], intervals.get(0)[1]);

                    // Retira o intervalo da lista de intervalos disponíveis para execução
                    intervals.remove(0);
                }
            }
        }

        // Finaliza os workers definindo a variavel do loop para falsa
        for (printPrimeNumber worker : workers) {
            worker.setHasWork(false);
        }

    }
}
