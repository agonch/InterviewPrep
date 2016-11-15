// Binary Tree Node
public class Node {
	public int value;
	public Node left;
	public Node right;

	public Node(int value) {
		this(value, null, null);
	}

	public Node(int value, Node left, Node right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public Node insertLeft(int value) {
		this.left = new Node(value);
		return this.left;
	}

	public Node insertRight(int value) {
		this.right = new Node(value);
		return this.right;
	}

	@Override
	public String toString() {
		return "" + this.value;
	}

	public Node copy() {
		Node newNode = new Node(this.value);

		if (this.left != null) {
			newNode.left = this.left.copy();
		}
		if (this.right != null) {
			newNode.right = this.right.copy();
		}
		return newNode;
	}

}
