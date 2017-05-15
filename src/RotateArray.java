import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Ryan on 2017/4/24.
 */
public class RotateArray {
    /**
     * 第一个循环是需要交换几个数字
     * 第二个循环是将最后的数字移到第一位并且将其他数字后置
     * @param a
     * @param k
     */
    public static void bubbleRotate(int[] a, int k) {
        if (k > a.length)
            k = k % a.length;
        for (int i = 0; i < k; i++) {
            for (int j = a.length - 1; j > 0; j--) {
                int temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp;
            }
        }

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }

    public static void copyRotate(int[] a, int k) {
        if (k > a.length)
            k = k % a.length;
        int[] b = new int[a.length];
        System.arraycopy(a,a.length-k,b,0,k);
        System.arraycopy(a,0,b,k,a.length-k);

        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]);
        }
    }

    public static void collectionRotate(int[] a, int k) {
        if (k > a.length)
            k = k % a.length;
        Integer[] temp = new Integer[a.length];
        for (int i = 0; i < a.length ; i++) {
            temp[i] = a[i];
        }

        Collections.rotate(Arrays.asList(temp),k);

        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i]);
        }
    }

    /**
     * 分治法 divide and conquer
     * @param a
     * @param k
     */

    public static void reverseRotate(int[] a, int k) {
        if (k > a.length)
            k = k % a.length;
        reverse(a, 0, a.length - 1);
        reverse(a, 0 , k-1);
        reverse(a, k, a.length -1);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }

    public static void reverse(int[] a, int start, int end) {
        while (start < end) {
            int temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        copyRotate(a,4);
        System.out.print("\n");

        a = new int[] {1,2,3,4,5};
        bubbleRotate(a,4);
        System.out.println();

        a = new int[] {1,2,3,4,5};
        collectionRotate(a,4);
        System.out.println();

        a = new int[] {1,2,3,4,5};
        reverseRotate(a,4);
    }
}
