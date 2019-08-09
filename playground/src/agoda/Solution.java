package agoda;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        int[] test = new int[] {2, 7, 11, 15};
//        int target = 9;
    }



    public Object[] findNumbers(int[] nums, int target)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i <nums.length; i++)
        {
            map.put(i, nums[i]);
        }

        List<Integer> result = new ArrayList<>();
        int i = 0;

        for (Map.Entry<Integer, Integer> entry: map.entrySet())
        {
            if (result.isEmpty() && map.containsValue(target - entry.getValue()))
            {
                result.add(entry.getKey());
                i = target - entry.getValue();
            }

            if (entry.getValue() == i && !result.isEmpty())
            {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);
        return result.toArray();
    }
}
