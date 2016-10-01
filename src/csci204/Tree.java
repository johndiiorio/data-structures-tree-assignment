package csci204;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to maintain the Tree via adding, finding, and printing.
 * 
 * @author John DiIorio
 * @param <E>
 *            - Generic type that extends Comparable
 */
public class Tree<E extends Comparable<E>> {

	private Node<E> root;
	private int numItems;
	private int maxChildren;

	/**
	 * Constructor for the Tree class. Takes in the maximum number of children
	 * the tree allows and makes sure it is not less than 1.
	 * 
	 * @param maxChildren
	 *            - Maximum number of children the tree allows.
	 */
	public Tree(int maxChildren) {
		// Makes sure maxChildren is at least 1
		if (maxChildren < 1) {
			throw new IllegalArgumentException();
		}
		this.maxChildren = maxChildren;
	}

	/**
	 * This method first checks special cases of the tree, then calls the
	 * addRecursive method to insert the element.
	 * 
	 * @param element
	 *            - Generic element to be inserted into the tree.
	 * @return boolean - Tells if the add method was successful (not a duplicate
	 *         value).
	 */
	public boolean add(E element) {
		// Does not allow null into Tree
		if (element == null) {
			throw new IllegalArgumentException("Key is null");
		}
		// If the tree is empty, set the Node to the root
		if (numItems == 0) {
			root = new Node<E>(element, maxChildren);
			numItems++;
			return true;
		}
		// Tree is not empty, add element recursively, incrementing if it
		// successfully added
		if (addRecursive(element, root)) {
			numItems++;
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method inserts the element into the Tree using the SteveTree
	 * specific rules.
	 * 
	 * @param element
	 *            - Element to be inserted into the tree.
	 * @param node
	 *            - Node to start recursing upon.
	 * @return boolean - Tells if the add method was successful (not a duplicate
	 *         value).
	 */
	@SuppressWarnings("unchecked")
	private boolean addRecursive(E element, Node<E> node) {
		// Checks if the Node is a duplicate value
		if (checkIfDuplicate(element, node)) {
			return false;
		}
		// If element is smaller than any child, go down the tree
		for (int i = 0; i < node.children.length; i++) {
			if (node.children[i] != null
					&& element.compareTo(node.getChildValue(i)) < 0) {
				return addRecursive(element, (Node<E>) node.children[i]);
			}
		}
		// Otherwise, try and add it in the next avalible spot
		for (int i = 0; i < node.children.length; i++) {
			if (node.children[i] == null) {
				node.children[i] = new Node<E>(element, maxChildren);
				return true;
			}
		}
		// Else, add it at the end of the free children
		return addRecursive(element,
				(Node<E>) node.children[node.children.length - 1]);
	}

	/**
	 * This method takes in an element and node and checks if the node or any of
	 * the children are duplicate values.
	 * 
	 * @param element
	 *            - Element to cross-check if it is a duplicate value.
	 * @param node
	 *            - Node to cross-check if it is a duplicate value.
	 * @return boolean - This returns true if there is a duplicate value,
	 *         returns false if not.
	 */
	private boolean checkIfDuplicate(E element, Node<E> node) {
		// Check if current node is a duplicate
		if (element == node.getValue()) {
			return true;
		}
		// Check if any of the children are duplicates
		for (int i = 0; i < node.children.length; i++) {
			if (node.children[i] != null && element == node.getChildValue(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method returns the number of items in the tree.
	 * 
	 * @return int - Returns number of items in the tree.
	 */
	public int size() {
		return numItems;
	}

	/**
	 * This method takes in an E element to find in the Tree. It calls the
	 * helper method, findRecursive, passing along the element.
	 * 
	 * @param element
	 *            - E Element to find in the tree.
	 * @return Node - Returns the node if it found it in the tree, otherwise
	 *         it returns null.
	 */
	public Node<E> find(E element) {
		if (element == null) {
			throw new IllegalArgumentException("Key is null");
		}
		// Return null if tree is empty
		if (numItems == 0) {
			return null;
		}
		return findRecursive(element, root);
	}

	/**
	 * This method takes in an E element and recurses upon a given node to find
	 * the node in the Tree. The method finds the Node based upon the same
	 * specific rules given for the addRecursive method.
	 * 
	 * @param element
	 *            - E Element to find in the tree.
	 * @param node
	 *            - Node to recurse upon in the tree.
	 * @return Node<E> - Returns the node if it found it in the tree, otherwise
	 *         it returns null.
	 */
	@SuppressWarnings("unchecked")
	private Node<E> findRecursive(E element, Node<E> node) {
		// Cannot add null to list
		if (node == null) {
			return null;
		}
		// If it is the current node, return it
		if (element == node.getValue()) {
			return node;
		}
		// Search through all children, and go down the child node if it is
		// smaller
		for (int i = 0; i < node.children.length; i++) {
			if (node.children[i] != null
					&& element.compareTo(node.getChildValue(i)) <= 0) {
				return findRecursive(element, (Node<E>) node.children[i]);
			}
		}
		// Otherwise, check all children
		for (int i = 0; i < node.children.length; i++) {
			if (node.children[i] == element) {
				return node.getChildNode(i);
			}
		}
		// Else, recurse similar to the addRecursive() method
		return findRecursive(element,
				(Node<E>) node.children[node.children.length - 1]);
	}

	/**
	 * This method returns the tree, represented by a level order traversal, in
	 * a String.
	 * 
	 * @return String - String to be returned by the method.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		// Prepare the String and create the Queue
		String str = "{\n";
		Queue<Node<E>> queue = new LinkedList<Node<E>>();

		// Tree is empty, otherwise add the root to the tree
		if (root == null) {
			return "{\nTree is empty\n}";
		} else {
			queue.add(root);
		}
		// While the queue is not empty, remove the node and queue the children
		// of that node
		while (!queue.isEmpty()) {
			Node<E> tmpNode = queue.remove();
			str += tmpNode.toString();

			for (int i = 0; i < tmpNode.children.length; i++) {
				if (tmpNode.children[i] != null) {
					queue.add((Node<E>) tmpNode.children[i]);
				}
			}
		}
		// Return the String
		return str + "\n}";
	}
}
