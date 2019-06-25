//package algorithms4.Sort;
//
//import edu.princeton.cs.algs4.StdRandom;
//
///**
// * Created by Ryan on 2017/4/7.
// */
//public class QuickSort{
//    public static void sort(Comparable[] a) {
//        StdRandom.shuffle(a);
//        sort(a,0,a.length - 1);
//    }
//
//    public static void sort(Comparable[] a, int lo, int hi) {
//        if (hi <= lo)
//            return;
//        int j = partition(a,lo,hi);
//        sort(a,lo,j-1);
//        sort(a,j+1,hi);
//    }
//
//    public static int partition(Comparable[] a, int lo, int hi) {
//        int i = lo;
//        int j = hi+1;
//        Comparable v = a[lo];
//        while (true) {
//            while (a[++i].compareTo(v) < 0)
//                if (i == hi) break;
//            while (v.compareTo(a[--j]) < 0)
//                if (j == lo) break;
//            if (i >= j) break;
//            exch(a,i,j);
//        }
//        exch(a,lo,j);
//        return j;
//    }
//
//    public static void exch(Comparable[] a, int i, int j) {
//        Comparable temp = a[i];
//        a[i] = a[j];
//        a[j] = temp;
//    }
//
//
//    public static void threePartitionSort(Comparable[] a, int lo, int hi) {
//        if (hi <= lo)
//            return;
//        int lt = lo;
//        int i = lo + 1;
//        int gt = hi;
//        Comparable v = a[lo];
//        while (i <= gt) {
//            int cmp = a[i].compareTo(v);
//            if (cmp < 0)
//                exch(a, i++, lt++);
//            else if (cmp > 0)
//                exch(a, i, gt--);
//            else
//                i++;
//        }
//        threePartitionSort(a,lo,lt-1);
//        threePartitionSort(a,gt + 1, hi);
//    }
//
//    /**
//     * 找出一组数中第K个小的元素
//     */
//
//    public Comparable select(Comparable[] a, int k) {
//        StdRandom.shuffle(a);
//        int lo = 0, high = a.length - 1;
//        while (high > lo) {
//            int j = partition(a, lo,high);
//            if (j == k) break;
//            if (j > k)
//                high = j - 1;
//            if (j < k)
//                lo = j + 1;
//        }
//        return a[k];
//    }
//}
