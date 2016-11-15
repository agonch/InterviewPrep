import java.util.*;

/*
 * In order traversal Iterator over a Binary Tree.
 */
public class BinaryTreeIterator implements Iterator<Node> {
	
	private Stack<Node> stack = new Stack<>();

	public BinaryTreeIterator(Node root) {
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	public boolean hasNext() {
		return stack.size() > 0;
	}

	public Node next() {
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		}
		Node to_ret = stack.pop();
		Node curr = to_ret;
		
		if (curr.right != null) {
			curr = curr.right;
			while (curr != null) {
				stack.push(curr);
				curr = curr.left;
			}
		}

		return to_ret;	
	}

}