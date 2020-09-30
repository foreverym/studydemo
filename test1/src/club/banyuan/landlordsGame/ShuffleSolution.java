package club.banyuan.landlordsGame;

import java.util.Arrays;
import java.util.Random;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/9/12 9:14 下午
 */
public class ShuffleSolution {

  private String[] nums;
  private String[] originalNums;

  public ShuffleSolution(String[] nums) {
    this.nums = nums;
    this.originalNums = Arrays.copyOf(nums, nums.length);
  }

  //重新设置
  public String[] reset() {
    return this.originalNums;
  }

  public String[] shuffle() {
    Random random = new Random();
    for (int i = 0; i < nums.length / 2; i++) {
      // 每次只需拿第一个元素进行交换即可
      swap(nums, 0, random.nextInt(nums.length));
    }
    return nums;
  }

  private void swap(String[] nums, int i, int j) {
    String temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }


}
