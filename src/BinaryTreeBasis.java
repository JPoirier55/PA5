/**
 * Implements a simple binary tree class for use in CSU
 * CS200 Fall 2010 Lab 9.
 * Based on code downloaded from the Carrano and Pritchard
 * text web site in 2007. Modified to make greater use of
 * generics.
 * @author David Newman
 * @date 2010-10-14
 *
 * @param <T>
 * MODIFIED BY JAKE LORD 10/18/14
 */
public abstract class BinaryTreeBasis {
  protected TreeNode root;

  public BinaryTreeBasis() {
    root = null;
  }  // end default constructor

  public BinaryTreeBasis(Term rootItem) {
    root = new TreeNode(rootItem, null, null);
  }  // end constructor
  
  public TreeNode getRoot() {
	  return root;
  }

  public boolean isEmpty() {
// Returns true if the tree is empty, else returns false.
    return root == null;
  }  // end isEmpty

  public void makeEmpty() {
// Removes all nodes from the tree.
    root = null;
  }  // end makeEmpty

  public Term getRootItem() throws TreeException {
// Returns the item in the trees root.
    if (root == null) {
      throw new TreeException("TreeException: Empty tree");
    }
    else {
      return root.getItem();
    }  // end if
  }  // end getRootItem
  
  public static void main(String []args){
	  Term thisTerm = new Term ("This");
	  TreeNode myNode = new TreeNode(thisTerm);
	  System.out.println(myNode.getItem().getName());
	  Term another = new Term("here");
	  TreeNode otherNode = new TreeNode(another);
	  myNode.setLeft(otherNode);
	  System.out.println(myNode.getLeft().getItem().getName());
	  System.out.println();
  }

}  // end BinaryTreeBasis
