package algorithms4.Basic;

import java.util.Arrays;

/**
 * Created by Ryan on 2017/4/5.
 */
public class TwoSumFast {
    public static int count(int[] a) {
        int[] b = a;
        Arrays.sort(b);
        int count = 0;
        for (int i = 0; i < b.length ; i++) {
            if (Arrays.binarySearch(b,-b[i]) > i)
                count++;
        }
        return count;
    }
}
