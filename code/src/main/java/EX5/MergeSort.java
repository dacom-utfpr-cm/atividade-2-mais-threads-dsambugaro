package EX5;
/*
 * Classe que ordena uma lista de comparaveis utilizando mergesort recursivo e threads
 *
 * @author Danilo Sambugaro created on 01/09/2019 inside the package - EX5
 *
 */

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements Runnable {

    // Vetor para ser utilizado pela Thread
    private List<Comparable> list = new ArrayList<Comparable>();

    public MergeSort(List<Comparable> list) {
        this.list = list; // Lista a ser ordenada
    }

    private List<Comparable> merge(final List<Comparable> left, final List<Comparable> right) {
        final List<Comparable> merged = new ArrayList<>(); // Lista que armazenará o merge ordenado das partes da lista sendo ordenada
        while (!left.isEmpty() && !right.isEmpty()) { // Enquanto houver itens para realizar o merge
            if (left.get(0).compareTo(right.get(0)) <= 0) { // Se o elemento da lista a esquerda for menor ou igual ao
                merged.add(left.remove(0));              // elemento na lista a direita, ele é adicionado a lista ordenado
            } else { // Caso contrario
                merged.add(right.remove(0)); // adiciona o elemento da lista a direita a lista ordenada
            }
        }
        merged.addAll(left); // Caso tenha itens restante em left, adicione ao vetor ordenado
        merged.addAll(right); // Caso tenha itens restante em right, adicione ao vetor ordenado
        return merged;
    }

    public void mergeSort(final List<Comparable> input) throws InterruptedException {
        if (input.size() != 1) {
            final List<Comparable> left = new ArrayList<Comparable>();
            final List<Comparable> right = new ArrayList<Comparable>();
            boolean leftRightSwitch = true;
            while (!input.isEmpty()) {
                // Enquanto houverem itens na lista
                // Adiciona intercaladamente os itens em listas novas
                // Dividindo a lista de entrada (input)
                // ao mesmo tempo remove os elementos da lista de entrada
                if (leftRightSwitch) {
                    left.add(input.remove(0));
                } else {
                    right.add(input.remove(0));
                }
                leftRightSwitch = !leftRightSwitch;
            }

            // Cria e executa uma thread para a metade esquerda da lista
            MergeSort mergeSortLeft = new MergeSort(left);
            Thread mergeSortLeftThread = new Thread(mergeSortLeft, "mergeSortThreadLeft");
            mergeSortLeftThread.start();

            // Cria e executa uma thread para a metade direita da lista
            MergeSort mergeSortRight = new MergeSort(right);
            Thread mergeSortRightThread = new Thread(mergeSortRight, "mergeSortThreadRight");
            mergeSortRightThread.start();

            // Aguarda as chamadas de cada metade da lista
            mergeSortLeftThread.join();
            mergeSortRightThread.join();

            // adiciona a lista ordenada lista de entrada
            input.addAll(merge(left, right));
        }
    }

    @Override
    public void run() {
        try {
            mergeSort(this.list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
