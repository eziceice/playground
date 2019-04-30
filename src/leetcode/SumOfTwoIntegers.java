package leetcode;

/**
 * Created by Ryan on 2017/4/30.
 */
public class SumOfTwoIntegers {
    /**
     * 真值表 X XOR Y
     * X Y OUTPUT
     * 0 0 0
     * 1 0 1
     * 1 1 0
     * 0 1 1
     *
     * 真值表 X AND Y
     * X Y OUTPUT
     * 0 0 0
     * 0 1 0
     * 1 0 0
     * 1 1 1
     */
    public int getSum(int a, int b) {
        int sum = a ^ b;
        int carry = (a & b) << 1;
        if (carry != 0)
            return getSum(sum, carry);
        return sum;
    }
}
