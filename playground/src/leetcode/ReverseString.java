package leetcode;

/**
 * Created by Ryan on 2017/4/28.
 */
public class ReverseString {
    public static String reverseString(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] result = s.toCharArray();
        while (left < right) {
            char temp = result[left];
            result[left] = result[right];
            result[right] = temp;
            left++;
            right--;
        }
        return new String(result);
    }

    public static String reverseStringStringBuilder(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseString("hello"));
    }
}
