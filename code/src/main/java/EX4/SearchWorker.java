package EX4;
/*
 * Thread que imprime os números primos em um determidado intervalo
 *
 * @author Danilo Sambugaro created on 01/09/2019 inside the package - EX3
 *
 */

public class SearchWorker extends Thread {

    private Integer[] vet;
    private int minNum;
    private int maxNum;
    private boolean hasWork = false;
    private boolean finished = true;
    private Integer target;
    private Integer targetPos = -1;
    private boolean founded;

    public SearchWorker(Integer[] vet, Integer target) {
        this.vet = vet;
        this.target = target;
    }

    public void setInterval(int min, int max) throws Exception {
        if (min <= max) {
            this.minNum = min;
            this.maxNum = max;
            finished = false;
        } else {
            throw new Exception("Min must be less than max");
        }
    }

    public Integer getTargetPos() {
        return targetPos;
    }

    public boolean isFounded() {
        return founded;
    }

    public int getMinNum() {
        return minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }


    @Override
    public void run() {

        if (!founded) {

            for (int i = minNum; i < maxNum; i++) {
                if (vet[i].equals(target)) {
                    founded = true;
                    targetPos = i;
                    break;
                }

                if (this.isInterrupted()) {
                    break;
                }
            }
        }
    }
}
