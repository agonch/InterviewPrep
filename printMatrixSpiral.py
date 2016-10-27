# http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/

def printMatrixSpiral(m, n, matrix):
	# m rows, n columns, prints data of matrix in spiral form
	row_i = col_i = 0
	while row_i < m and col_i < n:
		# Iterate next upper row
		for i in range(col_i, n):
			print(matrix[row_i][i], end=" ")
		# Iterate next right column
		for i in range(row_i + 1, m):
			print(matrix[i][n - 1], end=" ")
		# Iterate next bottom row
		if row_i + 1 < m:
			for i in range(n - 2, col_i, -1):
				print(matrix[m - 1][i], end=" ")
		# Iterate next left column
		if col_i + 1 < n:
			for i in range(m - 1, row_i, -1):
				print(matrix[i][col_i], end=" ")
		row_i += 1
		col_i += 1
		m -= 1
		n -= 1
	print('\n')


# creates an m by n matrix (m rows and n columns), increasing numerically row by row
def makeSquareMatrix(m, n):
	matrix = []
	for row in range(m):
		matrix.append(list(range(row * n + 1, row * n + n + 1)))
	return matrix


def test(m, n):
	print('Input:')
	matrix = makeSquareMatrix(m, n)
	for row in matrix:
		print(row)
	print('Spiral output:')
	printMatrixSpiral(m, n, matrix)

test(1, 4) 
test(2, 4)
test(4, 4)
test(11, 3) 
test(3, 6) 
test(3, 7) 
