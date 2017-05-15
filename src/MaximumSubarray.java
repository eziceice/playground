/**
 * Created by Ryan on 2017/5/15.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = 0;
        /**
         * 已知了前k个元素的最大子序列和为max（已经被记录下来了），
         * 以及一个临时和sum，如果添加了第k+1这个元素，由于是连续子序列这个限制，
         * 所以如果k+1这个元素之前的和是小于0的,
         * 那么对于增加k+1这个元素从而去组成最大子序列是没有贡献的，
         * 所以可以把sum 置0。
         */
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0)
                sum = 0;
            sum += nums[i];
            max = Math.max(sum,max);
        }
        return max;
    }
}
