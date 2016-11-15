import java.util.*;

public class Test_BinaryTreeIterator {

	public static void main(String[] args) {
		List<Node> test_trees = getTestTrees();
		for (Node root : test_trees) {
			
			System.out.println("Tree:");
			printTreeSideWays(root);
			
			BinaryTreeIterator iter = new BinaryTreeIterator(root);			
			List<Integer> inorderActual = new ArrayList<>();
			while(iter.hasNext()) {
				inorderActual.add(iter.next().value);
			}

			System.out.println("In order traversal using iterator:");
			System.out.println(inorderActual);
			
			List<Integer> inorderExpected = getInOrderTraversal(root);
			System.out.println("Expected: \n" + inorderExpected);
			
			if (!inorderExpected.equals(inorderActual)) {
				System.out.println("\n\nFAIL (check last case)");
				System.exit(0);
			}
			System.out.println("\n");
		}
		System.out.println("PASSED");
	}

	private static List<Node> getTestTrees() {
		List<Node> trees = new ArrayList<Node>();
		int val = 1;

		// get all trees of height 1
		Node root = new Node(val++);
		trees.add(root);

		// get all trees of height 2
		Node n = new Node(val++);
		n.insertLeft(val++);
		trees.add(n);
		
		n = new Node(val++);
		n.insertRight(val++);
		trees.add(n);

		n = new Node(val++);
		n.insertLeft(val++);
		n.insertRight(val++);
		trees.add(n);

		// get all trees of height 3
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				n = new Node(val++);
				n.left = trees.get(i).copy();
				n.right = trees.get(j).copy();
				trees.add(n);
			}
		}

		// get some trees of height 4
		int size = trees.size();
		for (int i = 4; i < size; i++) {
			for (int j = 4; j < size; j++) {
				n = new Node(val++);
				n.left = trees.get(i).copy();
				n.right = trees.get(j).copy();
				trees.add(n);
			}
		}

		return trees;
	}



	// Prints the tree in a sideways indented format.
	public static void printTreeSideWays(Node root) {
		printTreeSideWays(root, 0);
	}

	// Recursively print given subtree sideways at the
    // given level of indentation.
	private static void printTreeSideWays(Node node, int level) {
        int indentation_spaces = 2;

        if (node != null) {
            // root != null
            printTreeSideWays(node.right, level + 1);
            for (int i = 0; i < level * indentation_spaces; i++) {
                System.out.print(" ");
            }
            System.out.println(node.value);
            printTreeSideWays(node.left, level + 1);
        }
    }

	private static List<Integer> getInOrderTraversal(Node root) {
    	List<Integer> l = new ArrayList<>();
    	getInOrderTraversal(root, l);
    	return l;	
    }

    private static void getInOrderTraversal(Node root, List<Integer> l) {
    	if (root != null) {
    		getInOrderTraversal(root.left, l);
    		l.add(root.value);
    		getInOrderTraversal(root.right, l);
    	}
    }
}