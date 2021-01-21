from typing import List


def contains_duplicate(nums: List[int]) -> bool:
    return len(nums) == len(set(nums))