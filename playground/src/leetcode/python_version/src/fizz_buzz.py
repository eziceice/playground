from typing import List


def fizz_buzz(n: int) -> List[str]:
    i = 1
    items = []
    while i <= n:
        if i % 3 == 0 and i % 5 == 0:
            items.append('FizzBuzz')
        elif i % 3 == 0:
            items.append('Fizz')
        elif i % 5 == 0:
            items.append('Buzz')
        else:
            items.append(i)
        i = i + 1
    print(items)
    return items


fizz_buzz(15)
