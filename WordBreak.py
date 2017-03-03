"""
Problem:
  Can a string with no punctuation or spacing be split into a list 
  of valid words? Use dynamic programming, do it in O(n^2). Example:
        wordBreak('ilikesamsung') = true
        wordBreak('lilikesmng') = false
  because "i", "like", "samsung" are words.
    Input: S    = input string
           dict = dictionary of valid words
    Define T to be the table you memoize the subproblems in.

-- 2 approaches:

1) Working backwards, where T[i] indicates whether 
   S[i…n-1] can be split into a sequence of words.

2) T[i] indicates S[0...i-1] can be split into a sequence of words.

"""

dict_ = ["mobile","samsung","sam","sung","man","mango",
                           "icecream","and","go","i","like","ice","cream"]
longest_word_len = max(map(len, dict_))


# Approach 2

def wordBreak_1(S):
    """
    http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/
    """
    return None

# Appraoch 1

def wordBreak_2(S):
    N = len(S)
    T = [False] * (N + 1)
    T[N] = True
    for i in range(N, -1, -1):
        if T[0]:
            # We are done!
            return True
        if not T[i]:
            # No point of checking for previous word
            # since S[i...N-1] can't be split into words
            continue
        for j in range(i-1, -1, -1):
            # Find first word S[i-1 : i], S[i-2 : i], ..., S[0 : i]
            # If found, S[j:i] is a word and S[i...N-1] can be split
            # (we already checked for T[i] to be true)

            # In other words, consider all suffixes ending at i-1
            suffix = S[j:i]
            if suffix in dict_:
                T[j] = True

    return False


def wordBreak_3(S):
    N = len(S)
    T = [False] * (N + 1)
    T[N] = True
    for i in range(N - 1, -1, -1):
        # get next word starting at position i
        for j in range(i, min(i + longest_word_len, N + 1)):
            word = S[i:j]
            if word in dict_:
                if T[i + len(word)]:
                    # S[i:j] is word and S[j...N-1] can be split into words
                    # ( since T[i + len_word] = T[i + j] )
                    # Thus, T[i] is set to true
                    T[i] = True
                    # no point of going on
                    break
    return T[0]



tests = [["ilikesamsung", True], 
        ["iceilikesamsung", True],
        ["iceilikesamcreamsung", True],
        ["samango", False],
        ["andi", True],
        ["i", True],
        ["false", False], 
        ["iiiiiiii", True],
        ["", True], 
        ["ilikelikeimangoiii", True], 
        ["samsungandmango", True], 
        ["samsungandmangok", False],
        ["mobilike", False]]


print("Printing errors:")
for t, expected in tests:
    test_res = wordBreak_3(t)
    if test_res is not expected:
        print("--> Actual: ", test_res, " Expected val: ", expected, " for", t)
print("Done.")
