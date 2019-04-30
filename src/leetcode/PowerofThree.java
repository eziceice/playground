package leetcode;

/**
 * Created by Ryan on 2017/5/15.
 */
public class PowerofThree {
    public static boolean isPowerOfThree(int n) {
        if (n > 1) {
            while (n % 3 == 0)
                n = n / 3;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(9));
    }
}
