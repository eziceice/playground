/**
 * Created by Ryan on 2017/5/15.
 */
public class RemoveDupliatesfromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int index = 1;
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] != nums[i+1]) {
                nums[index] = nums[i+1];
                index++;
            }
        }
        return index;
    }
}
