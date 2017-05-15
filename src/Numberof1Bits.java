/**
 * Created by Ryan on 2017/5/15.
 */
public class Numberof1Bits {
    public static int hammingWeight(int n) {
        int counts = 0;
        while (n != 0) {
            counts = counts + (n & 1);
            n = n>>>1;
        }
        return counts;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(2147483647));
        System.out.println(Integer.MAX_VALUE);
    }
}
