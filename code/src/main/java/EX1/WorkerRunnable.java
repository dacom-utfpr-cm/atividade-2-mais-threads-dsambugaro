package EX1;
/*
 * Esta classe representa uma classe que ficará executando indefinicadamente com o intuito de se conseguir
 * observar o estado RUNNABLE
 *
 * @author Danilo Sambugaro created on 27/08/2019 inside the package - EX1
 */

public class WorkerRunnable implements Runnable {

    @Override
    public void run() {
        while (true) {
            int i = 0;

            while (i <= 999999999) {
                i++;
            }

            i = 0;
        }
    }

}
