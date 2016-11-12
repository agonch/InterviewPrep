"""
Write a function to see if a binary tree ↴ is "superbalanced" 
(a new tree property we just made up).
A tree is "superbalanced" if the difference between the depths 
of any two leaf nodes ↴ is no greater than one.
"""

class Node:
	def __init__(self, value):
		self.value = value
		self.left = None
		self.right = None

	def insert_left(self, value):
		self.left = Node(value)
		return self.left

	def insert_right(self, value):
		self.right = Node(value)
		return self.right

	def is_child(self):
		return self.left is None and self.right is None


def print_tree_sideways(node, indent):
	if node is None:
		print('   ' * indent + str('nil'))
		return
	if node.left is None and node.right is None:
		print('   ' * indent + str(node.value))
		return
	print_tree_sideways(node.right, indent + 1)
	print('   ' * indent + str(node.value))
	print_tree_sideways(node.left, indent + 1)


def make_tree():
	head = Node(5)
	head.insert_left(1).insert_right(3)
	right = head.insert_right(8)
	right.insert_left(6)
	right.insert_right(9).insert_right(10)
	return head

# Recursively visits each node and returns min and max height tree
def get_min_max_heights(node):
	if node is None:
		return (0, 0)
	min_left, max_left = get_min_max_heights(node.left)
	min_right, max_right = get_min_max_heights(node.right)
	return (1 + min(min_left, min_right), 
			1 + max(max_left, max_right))

def is_super_balanced(head):
	min_height, max_height = get_min_max_heights(head)
	print('min height: ', min_height)
	print('max height: ', max_height)
	return abs(max_height - min_height) < 2

head = make_tree()
print_tree_sideways(head, 0)
print('Is super balanced? ', is_super_balanced(head))
