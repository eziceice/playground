package leetcode;

/**
 * Created by Ryan on 2017/5/15.
 */
public class ClimbingStairs {
    public static int climbStairs(int n) {
        /**
         * 动态规划问题
         * 对于每一个点来说
         * 要么就是最后一步到达这个点的方法all[n-1]
         * 要么就是最后两步到达这个点的方法all[n-2]
         * 于是取这两者之和为总共到达该点的方法
         */
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int[] all = new int[n+1];
        all[0] = 0;
        all[1] = 1;
        all[2] = 2;
        for (int i = 3; i < n+1 ; i++)
            all[i] = all[i-1] + all[i-2];
        return all[n];
    }
}
