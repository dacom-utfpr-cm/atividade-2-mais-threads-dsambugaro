package EX5;
/*
 * Exercicio 5
 * Faça um programa multithreaded em Java que ordene um vetor usando o Merge Sort recursivo. Faça com que a thread
 * principal dispare duas threads para classificar cada metade do vetor.
 *
 * @author Danilo Sambugaro created on 03/09/2019 inside the package - EX5
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException {
        int vectorSize = 100;
        Random rand = new Random();

        // Lista de comparaveis, tentando manter mais genérica
        List<Comparable> list = new ArrayList<Comparable>();

        for (int i = 0; i < vectorSize; i++) {
            // Gera números aleatórios entre 0 e vectorSize * 10 e os adiciona na lista
            list.add(rand.nextInt(vectorSize*10));
        }

        MergeSort mergeSort = new MergeSort(list);
        Thread mergeSortThread = new Thread(mergeSort, "mergeSortThread");
        mergeSortThread.start();
        mergeSortThread.join();


        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ", ");
        }
    }

}
