import java.util.Iterator;
import java.util.LinkedList;

/**
 * Stub file for students to fill in. CSU CS200 Fall 2014 Lab 7.
 * @author David Newman (Fall 2010), modified AEH (Fall 2014)
 *
 * @param <E>
 */
public class InorderIterator implements Iterator {
	
	//TODO: Create your needed instance variables.
	// a queue tracks the order for visiting the tree nodes
	private BinaryTreeBasis binTree;
	private TreeNode currentNode;
	private LinkedList<TreeNode> queue;
	BinaryTree myTree;
	/**
	 * Construct a new iterator object.
	 * @param binTree
	 */
	public InorderIterator(BinaryTree bTree) {
		//TODO: Initialize your instance variables.
		binTree = bTree;
		queue = new LinkedList <TreeNode>();
		
	}
	

	/* (non-Javadoc)
	 * Return true iff the iterator has more objects yet to return.
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		//TODO: Fix this up so it does the right thing.
		return !queue.isEmpty();
	}

	/* (non-Javadoc)
	 * Return the first object that has not yet been returned.
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Term next() 
				throws java.util.NoSuchElementException {
			currentNode = queue.remove();
			return currentNode.getItem();
		
	
		
	}

	/* (non-Javadoc)
	 * This is an illegal operation for this iterator.
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove(){
	}

	/*
	 * Put the correct order of nodes onto the queue
	 */
	public void setInorder() {
		queue.clear();
		inorder(binTree.root);
		
	}
	private void inorder(TreeNode treeNode) {
		
		if (treeNode != null) {
			inorder(treeNode.getLeft());
			queue.add(treeNode);
			inorder(treeNode.getRight());
		}
	}
}
