def is_anagram(s: str, t: str) -> bool:
    # return sorted(s) == sorted(t)
    if len(s) != len(t):
        return False
    s_map = {}
    for i in list(s):
        if i in s_map.keys():
            s_map[i] = s_map[i] + 1
        else:
            s_map[i] = 1

    for i in list(t):
        if i in s_map.keys():
            if s_map[i] == 0:
                return False
            else:
                s_map[i] = s_map[i] - 1
        else:
            return False
    return True