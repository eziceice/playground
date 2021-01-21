from typing import List


def single_number(nums: List[int]) -> int:
    items = {}
    for i in nums:
        if i in items.keys():
            del items[i]
        else:
            items[i] = 1
    print(list(items.keys())[0])
    # # 2*(a+b+c)−(a+a+b+b+c)=c
    # print(2 * sum(set(nums)) - sum(nums))


single_number([2, 2, 1])
single_number([4, 1, 2, 1, 2])
single_number([1])
