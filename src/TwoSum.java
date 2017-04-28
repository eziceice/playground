import java.util.HashMap;

/**
 * Created by Ryan on 2017/4/26.
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length ; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i],i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        int[] result = twoSum(nums,target);
        System.out.println(result[0] + " " + result[1]);
    }
}
