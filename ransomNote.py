from collections import defaultdict

def ransom_note(magazine, rasom):
    counts = defaultdict(int)
    i = 0
    for r_s in rasom:
        if r_s not in counts or counts[r_s] == 0:
            while i < len(magazine) and magazine[i] != r_s:
                counts[magazine[i]] += 1
                i += 1
            if i == len(magazine):
                return False
            if magazine[i] == r_s:
                counts[magazine[i]] += 1
                i += 1
        counts[r_s] -= 1        
    return True

m, n = map(int, input().strip().split(' '))
magazine = input().strip().split(' ')
ransom = input().strip().split(' ')
answer = ransom_note(magazine, ransom)
if(answer):
    print("Yes")
else:
    print("No")
    
