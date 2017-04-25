import java.util.Arrays;

/**
 * Created by Ryan on 2017/4/25.
 */
public class KthElement {
    public static int partitionGet(int[] a, int k) {
        int high = a.length - 1;
        int low = 0;
        while (low < high) {
            int j = partition(a,low,high);
            if (j > k)
                high = j - 1;
            else if (j < k)
                low = j + 1;
            else
                break;
        }
        System.out.println(a[k]);
        return a[k];
    }


    public static int partition(int[] a,int low,int high) {
        int i = high;
        int j = low;
        int v = a[low];
        while (true) {
            while (a[++j] < v) {
                if (j == a.length - 1)
                    break;
            }

            while (a[i--] > v) {

            }

            if (i <= j)
                break;
            exch(i,j,a);
        }

        exch(j,low,a);
        return i;
    }

    public static void exch(int a, int b,int[] c) {
        int temp = c[b];
        c[b] = c[a];
        c[a] = temp;
    }

    public static int sortGet(int[] a, int k) {
        Arrays.sort(a);
        System.out.println(a[k]);
        return a[k];
    }


    public static void main(String[] args) {
        int[] a = {8,7,6,5,4,3,2,1,0};
        sortGet(a, 4);

        a = new int[]{8,7,6,5,4,3,2,1,0};
        partitionGet(a,4);
    }
}
