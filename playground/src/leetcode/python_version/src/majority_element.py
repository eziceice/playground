from typing import List


def majority_element(nums: List[int]) -> int:
    # element_map = {}
    # if len(nums) == 1:
    #     return nums[0]
    # for i in nums:
    #     if i in element_map.keys():
    #         element_map[i] = element_map[i] + 1
    #         print(element_map[i])
    #         if element_map[i] >= len(nums) / 2:
    #             return i
    #     else:
    #         element_map[i] = 1
    nums.sort()
    return nums[len(nums) // 2]
