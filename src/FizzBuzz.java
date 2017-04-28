import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 2017/4/28.
 */
public class FizzBuzz {
    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n ; i++) {
            if (i % 3 == 0 && i % 5 != 0)
                result.add("Fizz");
            else if (i % 5 == 0 && i % 3 != 0)
                result.add("Buzz");
            else if (i % 5 == 0 && i % 3 == 0)
                result.add("FizzBuzz");
            else
                result.add(i + "");
        }
        return result;
    }


    public static List<String> fizzBuzzNoMod(int n) {
        List<String> result = new ArrayList<>();
        int fizz = 0;
        int buzz = 0;
        for (int i = 1; i <= n ; i++) {
            fizz++;
            buzz++;
            if (fizz == 3 && buzz == 5) {
                result.add("FizzBuzz");
                fizz = 0;
                buzz = 0;
            } else if (fizz == 3) {
                result.add("Fizz");
                fizz = 0;
            } else if (buzz == 5) {
                result.add("Buzz");
                buzz = 0;
            } else
                result.add(i + "");
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> result = (ArrayList<String>) fizzBuzz(15);
        for (String s: result) {
            System.out.println(s);
        }
    }
}
