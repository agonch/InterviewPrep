public List<Node> reverse(Node overallRoot) {
	List<Node> list = new ArrayList<Node>();
	reverse(overallRoot, null, list);
	return list;
}

private void reverse(Node root, Node previous, List<Node> list) {
	if (root != null) {
		if(root.left == null && root.right == null) {
			list.add(root);
		} else {
			reverse(root.left, root, list);
			reveres(root.right, root, previous);
		}
		if (previous != null) {
			if (previous.left == root) {
				root.right = previous;
				root.left = null;
			} else if (previous.right == root) {
				root.left = previous;
				root.right = null;
			}
		} else {
			root.left = null;
			root.right = null;
		}
	} 
}