package leetcode;

/**
 * Created by Ryan on 2017/5/4.
 */
public class BestTimetoBuyandSellStock {
    public static int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int max = 0;
        int soFarMin = prices[0];
        for (int i = 0; i < prices.length; i++) {
            if (soFarMin > prices[i])
                soFarMin = prices[i];
            else {
                max = Math.max(max,prices[i] - soFarMin);
            }
        }

        return max;

    }

    public static void main(String[] args) {
        int[] ints = new int[] {7,1,5,3,6,4};
        System.out.println(maxProfit(ints));
    }
}
