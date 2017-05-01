import java.util.*;

/**
 * Created by Ryan on 2017/5/1.
 */
public class FirstUniqueCharacterInaString {
    public static int firstUniqChar(String s) {
        char[] temp = s.toCharArray();
        LinkedHashMap<Character,Integer> map = new LinkedHashMap<>();
        char key = 0;

        for (int i = 0; i < temp.length; i++) {
            if (map.containsKey(temp[i]))
                map.put(temp[i],map.get(temp[i]) + 1);
            else
                map.put(temp[i], 1);
        }

        for (Map.Entry<Character,Integer> re: map.entrySet()) {
            if (re.getValue() == 1) {
                key = re.getKey();
                break;
            }
        }

        if (key != 0) {
            for (int i = 0; i < s.length(); i++) {
                if (key == s.charAt(i))
                    return i;
            }
        }

        return -1;
    }

    public static int simpleFirstUniqueCharacter(String s) {
        int[] all = new int[26];

        for (int i = 0; i < s.length(); i++)
            all[s.charAt(i) - 'a']++;

        for (int i = 0; i < s.length() ; i++) {
            if (all[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(firstUniqChar(s));

        String a = "loveleetcode";
        System.out.println(firstUniqChar(a));

        String c = "sssssssssssssssssssbbbbbbbbbbbbbbbdddddddddddddddddddda";
        System.out.println(firstUniqChar(c));
    }

}
