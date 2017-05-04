import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ryan on 2017/5/4.
 */
public class IntersectionofTwoArraysII {
    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums1.length ; i++) {
            if (map.containsKey(nums1[i]))
                map.put(nums1[i],map.get(nums1[i]) + 1);
            else
                map.put(nums1[i], 1);
        }

        for (int i = 0; i < nums2.length ; i++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                map.put(nums2[i],map.get(nums2[i]) - 1);
                result.add(nums2[i]);
            }
        }

        int[] re = new int[result.size()];
        for (int i = 0; i < result.size(); i++)
            re[i] = result.get(i);
        return re;
    }

    public static void main(String[] args) {
        int[] intsA = new int[] {2,2,2,3};
        int[] intsB = new int[] {2,2,3,3,1,1};
        int[] result = intersect(intsA,intsB);


        for (int i = 0; i < result.length ; i++) {
            System.out.print(result[i]);
        }
    }
}
