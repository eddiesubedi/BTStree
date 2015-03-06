public class BSTrees{
	public static void main(String[] args) {
		BSTree tree = new BSTree();
		tree.insert(36);
		tree.insert(22);
		tree.insert(10);
		tree.insert(44);
		tree.insert(42);
		tree.insert(16);
		tree.insert(25);
		tree.insert(3);
		tree.insert(23);
		tree.insert(24);
		tree.remove(42);
		tree.remove(23);
		tree.remove(22);
		tree.preOrder();
	}
}
class BSTree {

	protected BSTNode rootnode; // define root node

	class BSTNode {
		public BSTNode left;
		public BSTNode right;
		public BSTNode root;
		public int value;

		public BSTNode(int k) {
			left = right = root = null; // initially set left, right and root to
										// null

			value = k;
		}

		public String toString() {
			return "" + value;
		}
	}

	// Insert mehod
	public void insert(int k) {
		// create new node
		BSTNode n = new BSTNode(k);
		// recursively call insert mehod
		insertBST(this.rootnode, n);
	}

	public void insertBST(BSTNode t, BSTNode x) {

		if (t == null) {
			this.rootnode = x;
		} else {
			if (x.value < t.value) // if entered value x < existing node value
									// then go to left
			{
				if (t.left == null) {
					t.left = x;
					x.root = t;

				} else {
					insertBST(t.left, x);
				}

			} else if (x.value > t.value) // if entered value x > existing node
											// value then go to right
			{
				if (t.right == null) {
					t.right = x;
					x.root = t;

				} else {
					insertBST(t.right, x);
				}
			} else {
				x.root = t;

			}
		}
	}

	// method to remove node from BST
	public void remove(int k) {
		// First we must find the node, after this we can delete it.
		removeBST(this.rootnode, k);
	}

	public void removeBST(BSTNode t, int x) {
		if (t == null) {

			return;
		} else {
			if (t.value > x) {
				removeBST(t.left, x);
			} else if (t.value < x) {
				removeBST(t.right, x);
			} else if (t.value == x) {
				removeFoundNode(t);
			}
		}
	}

	public void removeFoundNode(BSTNode x) {
		BSTNode r;

		if (x.left == null || x.right == null) {
			// the rootnode is deleted
			if (x.root == null) {
				this.rootnode = null;
				x = null;
				return;
			}
			r = x;
		} else {

			r = successor(x);
			x.value = r.value;
		}

		BSTNode t;
		if (r.left != null) {
			t = r.left;
		} else {
			t = r.right;
		}

		if (t != null) {
			t.root = r.root;
		}

		if (r.root == null) {
			this.rootnode = t;
		} else {
			if (r == r.root.left) {
				r.root.left = t;
			} else {
				r.root.right = t;
			}
		}
		r = null;
	}

	// find the node in a way to violating node
	public BSTNode successor(BSTNode x) {
		if (x.right != null) {
			BSTNode r = x.right;
			while (r.left != null) {
				r = r.left;
			}
			return r;
		} else {
			BSTNode t = x.root;
			while (t != null && x == t.right) {
				x = t;
				t = x.root;
			}
			return t;
		}
	}

	// Search a node
	public void find(BSTNode n) {
		int l = 0;
		int r = 0;
		int t = 0;
		if (n.left != null) {
			l = n.left.value;
		}
		if (n.right != null) {
			r = n.right.value;
		}
		if (n.root != null) {
			t = n.root.value;
		}
		if (n.left != null) {
			find(n.left);
		}
		if (n.right != null) {
			find(n.right);
		}
	}
	public void preOrder() {
        preOrderTraversal(rootnode);
     }

     public void preOrderTraversal(BSTNode node) {
        if (node!= null) {
           System.out.println(node.value);
           preOrderTraversal(node.left);
           preOrderTraversal(node.right);
        }
     }
	public void postOrder() {
		postOrderTraversal(rootnode);
	}

	private void postOrderTraversal(BSTNode node) {
		if (node != null) {
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
			System.out.println(node.value);
		}
	}

	// Returns tree in INORDER traversal
	private void inOrder(BSTNode node) {
		if (node != null) {
			inOrder(node.left);
			System.out.println(node.value);
			inOrder(node.right);
		}
	}

	void inOrder() {
		inOrder(rootnode);
	}

	// Check for Tree is Empty or not

	boolean isEmpty() {
		return rootnode == null;
	}
}
