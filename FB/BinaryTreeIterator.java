import java.util.*;

/*
 * In order traversal Iterator over a Binary Tree.
 */
public class BinaryTreeIterator implements Iterator<Node> {
	
	private Stack<Node> stack = new Stack<>();

	public BinaryTreeIterator(Node root) {
		stack.push(root);
		addAllLeftOf(root);
	}

	// Iteratre all left nodes starting at node.left (until null reached).
	private void addAllLeftOf(Node node) {
		node = node.left;
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}

	public boolean hasNext() {
		return stack.size() > 0;
	}

	/*
	 * How this works:
	 * Instead of keeping track of what state you are in (traversing left
	 * subtree, current value, or right subtree), enforce the stack to always
	 * traverse as far left as it can go.
	 */
	public Node next() {
		if (!this.hasNext())
			throw new NoSuchElementException();

		Node to_ret = stack.pop();
		Node curr = to_ret;
		
		if (curr.right != null) {
			stack.push(curr.right);
			addAllLeftOf(curr.right);
		}
		return to_ret;	
	}

}