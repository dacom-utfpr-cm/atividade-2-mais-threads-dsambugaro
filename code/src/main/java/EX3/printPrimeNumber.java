package EX3;
/*
 * Thread que imprime os números primos em um determidado intervalo
 *
 * @author Danilo Sambugaro created on 01/09/2019 inside the package - EX3
 *
 */

public class printPrimeNumber extends Thread {

    private int minNum;
    private int maxNum;
    private boolean hasWork = false;
    private boolean finished = true;

    public void setInterval(int min, int max) throws Exception {
        if (min <= max) {
            this.minNum = min;
            this.maxNum = max;
            finished = false;
        } else {
            throw new Exception("Min must be less than max");
        }
    }

    public int getMinNum() {
        return minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setHasWork(boolean hasWork) {
        this.hasWork = hasWork;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isPrime(int num) {

        if (num == 1) {
            return false;
        }

        int remaining;

        for (int i = 2; i <= num / 2; i++) {
            remaining = num % i;
            if (remaining == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void run() {
        while (hasWork) {

            for (int i = minNum; i < maxNum; i++) {
                if (isPrime(i)) {
                    System.out.println(i + " é primo");
                }
            }

            finished = true;

            if (this.isInterrupted()) {
                break;
            }
        }
    }
}
