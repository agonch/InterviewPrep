teststr = "3[a2[c]]2[def]"

def isDigit(c):
	return '0' <= c <= '9'
	
def decode(str, i):
	res = ""
	while i < len(str):
		c = str[i]
		if isDigit(c):
			n = int(c)
			i, innerStr = decode(str, i + 2)
			for n_i in xrange(n):
				res += innerStr
		elif c == ']':
			return (i, res)
		else:
			res += c
		i += 1
	return res
	
print(teststr)
print(decode(teststr, 0))

