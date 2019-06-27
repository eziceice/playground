package leetcode;

import java.util.HashSet;

/**
 * Created by Ryan on 2017/4/27.
 */
public class LongestSubstringWithoutRepeating {
    public static int lengthOfLongestSubstring(String s) {
        int length = 0;
        int left = 0;
        int right = 0;
        HashSet<Character> hashSet = new HashSet<>();
        while (right < s.length()) {
            /**
             * 如果不是重复的字符就一直添加并记录长度
             */
            if (!hashSet.contains(s.charAt(right))) {
                hashSet.add(s.charAt(right++));
                length = Math.max(length,hashSet.size());
            } else
            /**
             * 如果遇到重复的字符就从左边开始删除字符直到不再添加重复字符或者size超出
             */
                hashSet.remove(s.charAt(left++));
        }
        return length;
    }

    public static void main(String[] args) {
       // String a = "abcabcbb";
        //String b = "bbb";
        String c = "pwwkew";
        String e = "aab";
       // System.out.println(lengthOfLongestSubstring(a));
       // System.out.println(lengthOfLongestSubstring(b));
        System.out.println(lengthOfLongestSubstring(c));
        System.out.println(lengthOfLongestSubstring(e));
    }
}
