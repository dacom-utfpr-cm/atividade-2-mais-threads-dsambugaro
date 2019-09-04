package EX4;
/*
 * Exercicio 4
 * Faça um programa em Java que realize uma busca paralela em um vetor de inteiros. Informe para o método: valor procurado, vetor de inteiros e o número de threads.
 *
 * @author Danilo Sambugaro created on 01/09/2019 inside the package - EX4
 *
 */

import java.util.LinkedList;
import java.util.List;

public class ParallelSearch {


    public static void main(String[] args) throws Exception {
        Integer[] vet = new Integer[1000];

        for (int i = 0; i < 1000; i++) {
            vet[i] = i;
        }

        Integer target = 666;

        int pos = search(vet, target, 3);

        if (pos >= 0) {
            System.out.println("Elemento " + target + " encontrado na posição " + pos + " do vetor");
        } else {
            System.out.println("Elemento " + target + " não encontrado no vetor");
        }


    }

    // Retorna o indice do primeiro elemento encontrado ou -1 caso não encontre
    public static int search(Integer[] vet, Integer target, Integer qtWorkers) throws Exception {


        int length = vet.length;

        if (length <= qtWorkers) {
            throw new Exception("Too many threads");
        }

        int intervalSize = length / qtWorkers;
        int rest = (intervalSize * qtWorkers) == length ? 0 : length - (intervalSize * qtWorkers);

        List<Integer[]> intervals = new LinkedList<Integer[]>();

        // Popula uma lista de intervalos para a pesquisa
        for (int i = 0; i < length; i += intervalSize) {
            Integer[] interval = { i , i+intervalSize-1};
            intervals.add(interval);
        }

        List<SearchWorker> workers = new LinkedList<SearchWorker>();

        for (int i = 0; i < 4; i++) {
            SearchWorker worker = new SearchWorker(vet, target);
            worker.setName("Worker " + (i + 1));
            workers.add(worker);
        }

        while (!intervals.isEmpty()) {
            for (SearchWorker worker : workers) {
                if (!worker.isAlive() && !intervals.isEmpty()) {

                    // Verifica se o valor máximo do intervalo está dentro do tamanho do vetor
                    Integer maxInterval = intervals.get(0)[1];
                    if (maxInterval > vet.length) {
                        maxInterval = vet.length;
                    }

                    // Passa um intervalo a um worker e o inicia
                    System.out.println("Iniciando busca no intervalo de " + intervals.get(0)[0] + " a " + maxInterval + " em " + worker.getName());
                    worker.setInterval(intervals.get(0)[0], maxInterval);
                    worker.start();

                    // Retira o intervalo da lista de intervalos disponíveis para execução
                    intervals.remove(0);
                }

            }
        }

        // Aguarda as pesquisas terminarem
        for (SearchWorker w : workers) {
            w.join();
        }

        // Verifica se algum worker encontrou o elemento
        for (SearchWorker worker : workers) {
            if (worker.isFounded()) {
                System.out.println(worker.getName() + " encontrou o elemento");
                Integer targetPos = worker.getTargetPos();

                // Retorna a posição da primeira ocorrência encontrada do elemento
                return targetPos;
            }
        }

        // Retorna -1 caso não encontre o elemento no vetor
        return -1;
    }
}
