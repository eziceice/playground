import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan on 2017/4/29.
 */
public class SingleNumber {
    public static int singleNumber(int[] nums) {
        HashMap<Integer,Integer> result = new HashMap<>();
        int value = 0;
        for (int i = 0; i < nums.length ; i++) {
            if (result.containsKey(nums[i]))
                result.put(nums[i],result.get(nums[i]) + 1);
            else
                result.put(nums[i],1);
        }

        for (Map.Entry<Integer,Integer> s : result.entrySet()) {
            if (s.getValue() == 1)
                value = s.getKey();
        }
        return value;
    }

    /**
     *
     * XOR in java
     * 0 ^ N = N
     * N ^ N = 0
     * @param nums
     * @return
     */

    public static int singleNumberNoExtraSpace(int[] nums) {
        int re = 0;
        for (int a: nums) {
            re ^= a;
        }
        return re;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {1,1,2,3,4,3,4,5,5,6,6};
        System.out.println(singleNumber(nums));

        nums = new int[] {1,1,2,3,4,3,4,5,5,6,6};
        System.out.println(singleNumberNoExtraSpace(nums));
    }
}
