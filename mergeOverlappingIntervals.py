# Merge Overlapping Intervals

# Given a set of time intervals in any order, merge all overlapping intervals i
# nto one and output the result which should have only mutually exclusive intervals.

from operator import itemgetter

# 1. Sort intervals, increasing order of starting time
# 2. Push the first interval onto stack.
# 3. For each interval do the following
#   a. If current interval does not overlap with the stack top, push it.
#   b. If does overlap and ending time of current interval is more than 
#      that of stack top, update stack top ending time.
# 4. stack contains the merged intervals. 

# pre: len(arr) > 0
def merge_overlapping_intervals(arr):
	arr.sort(key=itemgetter(0)) # sort by 1st element
	merged_tuples = []
	merged_tuples.append(arr[0])
	
	for next_start, next_end in arr[1:]:
		(stack_start, stack_end) = merged_tuples[-1]
		if next_start <= stack_end:
			merged_tuples[-1] = (stack_start, max(stack_end, next_end))
		else:
			merged_tuples.append((next_start, next_end))

	return merged_tuples


# Examples
#    [(1, 2), (2, 3)]  -->  [(1, 3)]
#    [(6, 8), (1, 9), (2, 4), (4, 7)]  -->  [(1, 9)]
#    [(0, 1), (3, 5), (4, 8), (10, 12), (9, 10)]   -->   [(0, 1), (3, 8), (9, 12)]
#    [(1, 10), (2, 6), (3, 5), (7, 9)]  --> [(1, 10)]

print " result ", merge_overlapping_intervals([(1, 2), (2, 3)])
print " result ", merge_overlapping_intervals([(6, 8), (1, 9), (2, 4), (4, 7)])
print " result ", merge_overlapping_intervals([(0, 1), (3, 5), (4, 8), (10, 12), (9, 10)])
print " result ", merge_overlapping_intervals([(1, 10), (2, 6), (3, 5), (7, 9)])