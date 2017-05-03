/**
 * Created by Ryan on 2017/5/3.
 */
public class ExcelSheetColumnNumber {
    /**
     *
     *
     Given a column title as appear in an Excel sheet, return its corresponding column number.
     For example:
        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
     */


    /**
     * 把字符串看成是一个26进制数，A-Z相当于1-26，
     * 那么直接按照进制转换的规则去做就行。
     * 比如ABC=1×262+2×261+3×260=731。
     */
    public static int titleToNumber(String s) {
        int result = 0;
        int power = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            result += power * (s.charAt(i) - 'A' + 1);
            power *= 26;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "AA";
        System.out.println(titleToNumber(s));

    }
}
