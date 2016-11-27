class Change:
	def __init__(self):
		# memoization, avoid solving duplicate subproblems
		self.memo = {}

	# top down approach
	def denominations_possibilities(self, amount, denominations, so_far = []):
		
		# base case
		if amount == 0:
			return [so_far]
		if amount < 0 or len(denominations) == 0:
			return []

		# check our memo, see if we've already solved this subproblem
		memo_key = str((amount, denominations))
		if memo_key in self.memo:
			return self.memo[memo_key]

		# print "checking ways to make %i with %s" % (amount_left, denominations_left)

		current_coin, rest_denominations = denominations[0], denominations[1:]

		# see how many possibilities we can get
		# for each number of times to use current_coin
		possibilities = []
		times_coin_used = 0
		while amount >= 0:
			possibilities += self.denominations_possibilities(\
				    amount, rest_denominations, so_far + [current_coin] * times_coin_used)
			amount -= current_coin
			times_coin_used += 1

		# save the answer in our memo so we don't compute it again
		self.memo[memo_key] = possibilities
		return possibilities

	# bottom up approach
	def denominations_possibilities_iterative(self, amount, denominations):
		# index is the amount and the value at each index is ways of getting that amount
		ways_of_doing_n_cents = [[]] * (amount + 1)

		# base case, one way to create amount zero
		ways_of_doing_n_cents[0] = []

		# The number of new ways we can make a higher_amount when we account
		# for a new coin is simply ways_of_doing_n_cents[higher_amount - coin]
		for coin in denominations:
			# ways of doing cents 1, 2, 3, ...
			for higher_amount in xrange(coin, amount + 1):
				for ways_we_know in ways_of_doing_n_cents[higher_amount - coin]:
					print ways_we_know
					ways_of_doing_n_cents += ways_we_know + [coin]

		print ways_of_doing_n_cents
		return ways_of_doing_n_cents[amount]

for arr in Change().denominations_possibilities_iterative(4, [1, 2, 3]):
	print " ", arr
print ""
for arr in Change().denominations_possibilities_iterative(5, [1, 3, 5]):
	print " ", arr