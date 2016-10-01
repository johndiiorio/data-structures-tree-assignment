package csci204;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;
import java.io.FileNotFoundException;

/**
 * This program creates a SteveTree. The Driver class makes use of the Node
 * class as well as the Tree class. The "test code" section is used to test the
 * Tree and Node classes. The Tree's toString() method prints the tree only in a
 * level order traversal. The program seems to work perfectly with no problems
 * in the code.
 * 
 * @author John DiIorio
 * @version 4-29-15
 * @since 4-24-15
 */
public class Driver {
	/**
	 * This is the main method which runs the code.
	 * 
	 * @param args
	 *            - Unused.
	 * @throws FileNotFoundException - Exception thrown if the scanner cannot find the specified file.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Open the scanner to read the file name
		Scanner scan = new Scanner(System.in);
		// Prompt user for file name and read it
		System.out.print("Enter filename to read: ");
		String filename = scan.nextLine();
		// Create the Vector list
		Vector<Integer> list = new Vector<Integer>();
		// Read in the values of the file into the list and print them out
		Scanner sFile = new Scanner(new File(filename));
		while (sFile.hasNextInt()) {
			list.add(sFile.nextInt());
		}
		// Get the max number of children and remove it from the list
		int maxChildren = list.get(0);
		if (maxChildren < 1) {
			scan.close();
			sFile.close();
			throw new IllegalArgumentException("Max children too small");
		}
		list.remove(0);
		System.out.println("Key values are: " + list.toString());

		// Create a Integer Tree and add each element of the list to it
		Tree<Integer> tree = new Tree<Integer>(maxChildren);
		for (Integer element : list) {
			// Add the element, otherwise it is a duplicate
			if (!tree.add(element)) {
				System.out.println("Could not add " + element
						+ " to the collection.");
			}
		}
		// Print out the max children and the tree via Level Order traversal
		System.out.println("Max children: " + maxChildren);
		System.out.println(tree);

		// Note: This only works for a Tree of Integers
		System.out.print("Find value: ");
		Node<Integer> node = tree.find(scan.nextInt());
		if (node == null) {
			System.out.println("No such value in collection.");
		} else {
			System.out.println("Found value in collection: " + node);
		}

		// Close the scanners
		scan.close();
		sFile.close();

	}

}
