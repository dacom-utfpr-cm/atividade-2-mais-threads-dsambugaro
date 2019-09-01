package EX2;
/*
 * Classe que representa uma thread simples que rodará indefinidamente
 *
 * @author Danilo Sambugaro created on 01/09/2019 inside the package - EX2
 *
 */

public class Worker implements Runnable{

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
