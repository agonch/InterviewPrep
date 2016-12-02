from math import ceil

# you can write to stdout for debugging purposes, e.g.
# print "this is a debug message"

def solution(X):
    if X is None:
        return None
    X_str = str(X)
    if len(X_str) is 0:
        return None
    largest = None
    for i in range(len(X_str) - 1):
        avg = ceil((int(X_str[i]) + int(X_str[i + 1])) / 2)
        substituted_X = int(X_str[:i] + str(avg) + X_str[i+2:])
        if largest is None:
            largest = substituted_X
        else:
            largest = max(largest, substituted_X)
    return largest


print(solution(623315))