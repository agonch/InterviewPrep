public List<Node> reverse(Node overallRoot) {
	List<Node> list = new ArrayList<Node>();
	reverse(overallRoot, null, null, list);
	return list;
}

private void reverse(Node root, Node left, Node right, List<Node> list) {
	if (root != null) {
		if(root.left == null && root.right == null) {
			list.add(root);
		} else {
			reverse(root.left, null, root, list);
			reveres(root.right, root, null, previous);
		}
		root.left = left;
		root.right = right;
	} 
}