package csci204;

/**
 * The Node class is used by the Tree class to store each element and the
 * children.
 * 
 * @author John DiIorio
 * @param <E>
 *            - Generic type that extends Comparable
 */
public class Node<E extends Comparable<E>> {

	protected Object[] children;
	private Object value;

	/**
	 * Constructor for the Node class. Takes in a value for the node and the
	 * maximum number of children. The maximum number of children is used to
	 * create the array of Objects.
	 * 
	 * @param value
	 *            - Object that the Node will store.
	 * @param maxChildren
	 *            - Integer that stores the maximum number of children allowed
	 *            for each Node.
	 */
	public Node(Object value, int maxChildren) {
		children = new Object[maxChildren];
		this.value = value;
	}

	/**
	 * This method returns the value of the Node.
	 * 
	 * @return E - Generic value of the node.
	 */
	@SuppressWarnings("unchecked")
	public E getValue() {
		return (E) value;
	}

	/**
	 * This method returns the child's value in the children array at a given
	 * index.
	 * 
	 * @param index
	 *            - int index to examine in the childen array
	 * @return E - Generic value of the child at the given index.
	 */
	public E getChildValue(int index) {
		return getChildNode(index).getValue();
	}

	/**
	 * This method returns the child's Node in the children array at a given
	 * index.
	 * 
	 * @param index
	 *            - int index to examine in the children array.
	 * @return Node - Returns the child node of the children array at the
	 *         given index.
	 */
	@SuppressWarnings("unchecked")
	public Node<E> getChildNode(int index) {
		return (Node<E>) children[index];
	}

	/**
	 * This method returns the number of children in the the children array.
	 * 
	 * @return int - Number of children in the children array.
	 */
	public int numChildren() {
		// For loops through all the children, counting them
		int sum = 0;
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null) {
				sum++;
			}
		}
		return sum;
	}

	/**
	 * Represents the Node as a String, bounded by brackets.
	 * 
	 * @return String - String to be returned by this method.
	 */
	@Override
	public String toString() {
		return "<" + value + ">";
	}
}
