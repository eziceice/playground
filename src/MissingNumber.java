/**
 * Created by Ryan on 2017/5/4.
 */
public class MissingNumber {
    public static int missingNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length ; i++) {
            result ^= nums[i] ^ i;
        }
        return result ^ nums.length;
    }
}
