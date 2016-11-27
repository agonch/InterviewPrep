# Python program to find maximum product subarray
 
# Returns the product of max product subarray.
# Assumes that the given array always has a subarray
# with product more than 1

def maxsubarrayproduct(arr):
	max_ending_here = min_ending_here = max_so_far = 1
	
	# optimizations left out for simplicity
	#   - if e == 0, set both __ending_here to 1
	#   - if e is positive, update max_ending_here (and min if negative)
	#   - if e is negative, update max_ending_here to max(min_ending_here*e, 1)
	#                        and min_ending_here to the prev max_ending_here*e

	# Invariant after ith iteration:
    # max_ending_here is always 1 or some positive product ending with arr[i]
    # min_ending_here is always 1 or some negative product ending with arr[i]
	for e in arr:
		temp = max_ending_here
		max_ending_here = max(e, max_ending_here * e, min_ending_here * e)
		min_ending_here = min(e, temp * e, min_ending_here * e)
		max_so_far = max(max_so_far, max_ending_here)

	return max_so_far

print "Maximum product subarray is",maxsubarrayproduct([1, -2, -3, 0, 7, -8, -2]), " should be 112"
print "Maximum product subarray is",maxsubarrayproduct([6, -3, -10, 0, 2]), " should be 180"   # {6, -3, -10}
print "Maximum product subarray is",maxsubarrayproduct([-1, -3, -10, 0, 60]), " should be 60" # {60}
print "Maximum product subarray is",maxsubarrayproduct([-2, -3, 0, -2, -40]), " should be 80" # {-2, -40}
print "Maximum product subarray is",maxsubarrayproduct([-2, 3, -3, 0, -2, -40]), " should be 80"
print "Maximum product subarray is",maxsubarrayproduct([-2, 3, -3, 0, -2, 1, -40]), " should be 80"
print "Maximum product subarray is",maxsubarrayproduct([-2, 3, -3, 0, -2, -40, 1, 0]), " should be 80"
print "Maximum product subarray is",maxsubarrayproduct([-2, 3, -3, 0, -2, -40, 1, 0, 80]), " should be 80"
print "Maximum product subarray is",maxsubarrayproduct([-2, 3, -3, 0, -2, -40, -10]), " should be 400"
print "Maximum product subarray is",maxsubarrayproduct([-2, 3, -3, 0, -2, -40, 2, -10]), " should be 800"
print "Maximum product subarray is",maxsubarrayproduct([-2, 3, -3, 0, -2, -1, -40, 2, -10]), " should be 1600"