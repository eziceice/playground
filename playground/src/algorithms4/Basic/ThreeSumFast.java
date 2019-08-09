package algorithms4.Basic;

import java.util.Arrays;

/**
 * Created by Ryan on 2017/4/5.
 */
public class ThreeSumFast {
    public static int count(int[] a) throws Exception {
        int[] b = a;
        int count = 0;
        Arrays.sort(b);
        for (int i = 0; i < b.length ; i++) {
            for (int j = 1; j < b.length; j++) {
                if (b[i] > 0 && b[j] > 0 && (b[i] + b[j]) < 0)
                    throw new Exception();
                if (b[i] < 0 && b[j] < 0 && (b[i] + b[j]) > 0)
                    throw new Exception();
                if (Arrays.binarySearch(b, (-b[i] - b[j])) > j)
                    count++;
            }
        }
        return count;
    }
}
