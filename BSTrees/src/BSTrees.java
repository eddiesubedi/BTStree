import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class BSTrees {
	public static void main(String[] args) {
		final BSTree tree = new BSTree();
		
		final GuiSetup gui = new GuiSetup();
		gui.outputTextPane.setText("Inorder:\nPostorder:\nPreorder:");
		gui.btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int input = Integer.parseInt(gui.inputTextField.getText());
					tree.insert(input);
					gui.outputTextPane.setText("Inorder:        "+tree.inOrder()+"\nPostorder:    "+tree.postOrder()+"\nPreorder:      "+tree.preOrder());
					gui.inputTextField.setText("");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(gui,"Invalid Input","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gui.btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int input = Integer.parseInt(gui.inputTextField.getText());
					tree.remove(input);
					gui.outputTextPane.setText("Inorder:        "+tree.inOrder()+"\nPostorder:    "+tree.postOrder()+"\nPreorder:      "+tree.preOrder());
					gui.inputTextField.setText("");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(gui,"Invalid Input","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}

class BSTree {

	protected BSTNode rootnode; // define root node
	public String inOrder="";
	public String preOrder="";
	public String postOrder="";
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

	String preOrder() {
		preOrder="";
		preOrderTraversal(rootnode);
		return preOrder;
	}

	public void preOrderTraversal(BSTNode node) {
		if (node != null) {
//			System.out.println(node.value);
			preOrder=preOrder+node.value+" ";
			preOrderTraversal(node.left);
			preOrderTraversal(node.right);
		}
	}

	public String postOrder() {
		postOrder="";
		postOrderTraversal(rootnode);
		return postOrder;
	}

	private void postOrderTraversal(BSTNode node) {
		if (node != null) {
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
//			System.out.println(node.value);
			postOrder=postOrder+node.value+" ";
		}
	}

	// Returns tree in INORDER traversal
	private void inOrder(BSTNode node) {
		if (node != null) {
			inOrder(node.left);
			inOrder=inOrder+node.value+" ";
//			System.out.println(node.value);
			inOrder(node.right);
		}
	}

	String inOrder() {
		inOrder="";
		inOrder(rootnode);
		return inOrder;
	}

	// Check for Tree is Empty or not
	boolean isEmpty() {
		return rootnode == null;
	}
}

class GuiSetup extends JFrame {
	private static final long serialVersionUID = 1L;
	public JTextField inputTextField;
	public JButton btnAdd;
	public JButton btnRemove;
	public JTextPane outputTextPane;

	public GuiSetup() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}
		getContentPane().setLayout(new CardLayout());
		setupGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setupGUI() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel inputPanel = new JPanel();
		contentPane.add(inputPanel, BorderLayout.SOUTH);

		inputTextField = new JTextField();
		inputPanel.add(inputTextField);
		inputTextField.setColumns(10);

		btnAdd = new JButton("Insert");
		inputPanel.add(btnAdd);

		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		inputPanel.add(btnRemove);

		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.CENTER);
		bottomPanel.setLayout(new CardLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		bottomPanel.add(scrollPane, "");

		outputTextPane = new JTextPane();
		scrollPane.setViewportView(outputTextPane);

		setTitle("Merge Sort");
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4),
				Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		setVisible(true);
		setResizable(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
	}
}
