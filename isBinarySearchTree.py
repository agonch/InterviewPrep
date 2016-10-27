# http://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/

def check_binary_search_tree_(root):
    if root is None:
        return True
    return checkBST(root, -float('inf'), float('inf'))

def checkBST(root, min_, max_):
    if root is None:
        return True
    return root.data > min_ and root.data < max_ and checkBST(root.left, min_, root.data) and checkBST(root.right, root.data, max_)
            