/**
 * Created by Ryan on 2017/5/1.
 */
public class MoveZeros {
    public static void moveZeroes(int[] nums) {
        int position = 0;

        /**
         * 压缩数组为只有非0位
         */
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] != 0)
                nums[position++] = nums[i];
        }

        /**
         * 从后补全数组 - 替换为0
         */
        while (position < nums.length) {
            nums[position++] = 0;
        }
    }

    public static void moveZerosToFront(int[] nums) {
        int position = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] != 0)
                nums[position--] = nums[i];
        }

        while (position >= 0)
            nums[position--] = 0;
    }

    public static void main(String[] args) {
        int[] ints = new int[] {3,4,5,6,7,8,9,1,20,0,1};
        //moveZeroes(ints);
        moveZerosToFront(ints);
        for (int i = 0; i < ints.length ; i++) {
            System.out.println(ints[i]);
        }


    }
}
