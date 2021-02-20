#!python2.7
from random import choice
from collections import defaultdict
from sets import Set
import string
import sys

allowed_chars = string.ascii_uppercase # do not include whitespace characters
print("allowed_chars:", allowed_chars)

def gen_random_str(length=5):
	return "www.url.com/" + ''.join(choice(allowed_chars) for i in range(length))

size_of_strings = sys.getsizeof(gen_random_str())
file_size_max = 200 * 1000 * 1000  # 200 MB
num_strings_to_generate = file_size_max / size_of_strings
print("Generating ", num_strings_to_generate, " strings...")
seen_strings = Set()
all_strings = []
duplicates = []

# Generate random strings, and aggregate duplicates.
for i in range(num_strings_to_generate):
		new_str = gen_random_str()
		all_strings.append(new_str)
		if new_str in seen_strings:
			duplicates.append(new_str)
		else:
			seen_strings.add(new_str)

print("Writing out to files...")

with open('LongListOfStrings.txt', 'w') as string_f:
	# 'w' opens a file for writing only. Overwrites the file if the file exists. 
	# If the file does not exist, creates a new file for writing.
	string_f.write('\n'.join(all_strings))

with open('ExpectedDuplicates.txt', 'w') as dups_f:
	duplicates.sort()
	dups_f.write('\n'.join(duplicates))
	
print("num duplicates: ", len(duplicates))
