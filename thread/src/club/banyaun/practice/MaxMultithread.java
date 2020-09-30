package club.banyaun.practice;

public class MaxMultithread {

  /**
   * 计算数组元素的sin值之后，返回最大值。
   *
   * @param arr 目标数组
   * @return sin(数组元素)的最大值
   * @throws InterruptedException 不应该出现此异常
   */
  public static double max(int[] arr, int numThreads) throws InterruptedException {

    MaxThread[] ts = new MaxThread[numThreads];
    double [] maxNums = new double[numThreads];
    int len = arr.length;
    double max = 0;

    for (int i = 0; i < numThreads; i++) {
      ts[i] = new MaxThread(arr, (i * len) / numThreads, ((i + 1) * len / numThreads));
      ts[i].start();
    }

    for (int i = 0; i < numThreads; i++) {
      ts[i].join();
      maxNums[i] = ts[i].getMax();
    }

    for (int i = 0; i < numThreads-1; i++) {
      if (Double.compare(Math.sin(arr[0]), Math.sin(arr[i + 1])) < 0) {
        arr[0] = arr[i + 1];
      }
      max = arr[0];
    }

    return max;
  }

}


class MaxThread extends Thread {

  private int lo, hi;
  private int[] arr;
  private double max = 0;

  public MaxThread(int[] arr, int lo, int hi) {
    this.lo = lo;
    this.hi = hi;
    this.arr = arr;
  }

  @Override
  public void run() {
    for (int i = lo; i < hi-1; i++) {
      if (Double.compare(Math.sin(arr[lo]), Math.sin(arr[i + 1])) < 0) {
        arr[0] = arr[i + 1];
      }
      max = arr[lo];
    }
  }

  public double getMax() {
    return max;
  }

}
