package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryan on 2017/5/3.
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length ; i++) {
            if (result.containsKey(nums[i]))
                result.put(nums[i],result.get(nums[i]) + 1);
            else
                result.put(nums[i], 1);
        }

        int re = 0;
        for (Map.Entry<Integer,Integer> temp: result.entrySet()) {
            if (temp.getValue() > nums.length/2) {
                re = temp.getKey();
                break;
            }
        }
        return re;
    }

    public int marjorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

}
