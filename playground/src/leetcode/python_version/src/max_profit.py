from typing import List


def max_profit(prices: List[int]) -> int:
    profit = 0
    for i in range(len(prices)):
        if (i + 1 < len(prices)) and (prices[i + 1] - prices[i] > 0):
            profit = profit + prices[i + 1] - prices[i]
    print(profit)


max_profit([2,1,4,5,2,9,7])