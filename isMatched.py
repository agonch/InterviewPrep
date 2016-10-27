def is_matched(expression):
    stack = []
    d = { "{": "}", "[": "]", "(": ")" }
    for c in expression:
        if c == "{" or c == "[" or c == "(":
            stack.append(c)
        else:
            if len(stack) == 0:
                return False
            last = stack.pop()
            if c != d[last]:
                return False
    return len(stack) == 0
    

t = int(input().strip())
for a0 in range(t):
    expression = input().strip()
    if is_matched(expression) == True:
        print("YES")
    else:
        print("NO")
