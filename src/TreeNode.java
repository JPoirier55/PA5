/**
 * Implements a simple node for trees for use in CSU
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
public class TreeNode {
  private Term item;
  private TreeNode leftChild;
  private TreeNode rightChild;

  public TreeNode(Term newItem) {
  // Initializes tree node with item and no children.
    item = newItem;
    leftChild  = null;
    rightChild = null;
  }  // end constructor
    
  public TreeNode(Term newItem, 
                  TreeNode left, TreeNode right) {
  // Initializes tree node with item and
  // the left and right children references.
    item = newItem;
    leftChild  = left;
    rightChild = right;
  }  // end constructor

  public Term getItem() {
  // Returns the item field.
    return item;
  }  // end getItem

  public void setItem(Term newItem) {
  // Sets the item field to the new value newItem.
  item  = newItem;
  }  // end setItem

  public TreeNode getLeft() {
  // Returns the reference to the left child.
    return leftChild;
  }  // end getLeft

  public void setLeft(TreeNode left) {
  // Sets the left child reference to left.
    leftChild  = left;
  }  // end setLeft

  public TreeNode getRight() {
  // Returns the reference to the right child.
    return rightChild;
  }  // end getRight

  public void setRight(TreeNode right) {
  // Sets the right child reference to right.
    rightChild  = right;
  }  // end setRight
}  // end TreeNode