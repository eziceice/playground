import java.util.Arrays;

/**
 * Created by Ryan on 2017/5/3.
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] tempS = s.toCharArray();
        char[] tempT = t.toCharArray();
        Arrays.sort(tempS);
        Arrays.sort(tempT);
        for (int i = 0; i < s.length(); i++) {
            if (tempS[i] != tempT[i])
                return false;
        }
        return true;
    }

    public static boolean isAngramUsingArray(String s, String t) {
        int[] result = new int[26];
        for (int i = 0; i < s.length(); i++)
            result[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++)
            result[t.charAt(i) - 'a']--;
        for (int a:result) {
            if (a != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "ab";
        String b = "a";

        System.out.println(isAnagram(a,b));
        System.out.println(isAngramUsingArray(a,b));
    }
}
