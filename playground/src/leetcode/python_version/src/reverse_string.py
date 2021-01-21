from typing import List


def reverse_string(s: List[str]) -> None:
    # s.reverse()
    left = 0
    right = len(s) - 1
    while left < right:
        s[left], s[right] = s[right], s[left]
        left, right = left + 1, right - 1


reverse_string(["h", "e", "l", "l", "o"])
