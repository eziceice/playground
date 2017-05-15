import java.util.HashSet;

/**
 * Created by Ryan on 2017/5/4.
 */
public class HappyNumber {
    public static boolean isHappy(int n) {
        int currentDig;
        int sum;
        HashSet<Integer> inLoop = new HashSet<>();

        while (inLoop.add(n)) {
            sum = 0;
            while (n > 0) {
                currentDig = n%10;
                sum += currentDig * currentDig;
                n = n/10;
            }

            if (sum != 1)
                n = sum;
            else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }
}
