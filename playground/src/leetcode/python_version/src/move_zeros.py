from typing import List


def move_zeros(nums: List[int]) -> None:
    # length = len(nums)
    # for num in nums:
    #     if length == 0:
    #         break
    #     if num == 0:
    #         nums.append(nums.pop(nums.index(num)))
    #         length = length - 1
    delete = 0
    length = len(nums)
    i = 0
    while i < length:
        if i == len(nums):
            break
        if nums[i] == 0:
            nums.remove(nums[i])
            delete = delete + 1
        else:
            i = i + 1
    for i in range(delete):
        nums.append(0)


move_zeros([0, 0, 1])
