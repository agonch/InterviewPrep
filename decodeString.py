"""
Anton Goncharenko

Given a string of form "3[a2[c]]2[def2[2[g]]]", decode it to return a string like "accaccaccdefdefggggdefgggg".
The rule is to copy the string within two matching brackets the number times specified left of the bracket.
This rule is recursive.
	"3[ a 2[c] ]   2[ def 2[2[g]] ]"
	 3x "acc"      2x"def gggg"
	 accaccacc     defggggdefgggg   
"""

def isDigit(c):
	return '0' <= c <= '9'

def decodeString(str):
	return decode(str, 0)
	
def decode(str, i):
	res = ""
	while i < len(str):
		c = str[i]
		if isDigit(c):
			n = 0
			while str[i] != '[':
				n = n*10 + int(str[i])
				i += 1
			i, innerDecode = decode(str, i + 1) # +1 to skip '['
			res += innerDecode * n
		elif c is ']':
			return i, res
		else:
			res += c
		i += 1
	return res
	


teststr = "11[a2[c]]2[def2[2[g]]]"
print(teststr)
print(decodeString(teststr))

