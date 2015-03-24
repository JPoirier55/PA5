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
 */
public class BinaryTree extends BinaryTreeBasis {
	
  public BinaryTree() {
	  super();
  }  // end default constructor

  public BinaryTree(Term rootItem) {
    super(rootItem);
  }  // end constructor

  public BinaryTree(Term rootItem, 
                    BinaryTree leftTree, 
                    BinaryTree rightTree) {
    root = new TreeNode(rootItem, null, null);
    attachLeftSubtree(leftTree);
    attachRightSubtree(rightTree);
  }  // end constructor

  public void setRootItem(Term newItem) {
    if (root != null) {
      root.setItem(newItem);
    }
    else {
      root = new TreeNode(newItem, null, null);
    }  // end if
  }  // end setRootItem

  public void attachLeft(Term newItem) {
    if (!isEmpty() && root.getLeft() == null) {
      // assertion: nonempty tree; no left child
      root.setLeft(new TreeNode(newItem, null, null));
    }  // end if
  }  // end attachLeft

  public void attachRight(Term newItem) {
    if (!isEmpty() && root.getRight() == null) {
      // assertion: nonempty tree; no right child
      root.setRight(new TreeNode(newItem, null, null));
    }  // end if
  }  // end attachRight

  public void attachLeftSubtree(BinaryTree leftTree) 
                                throws TreeException {
    if (isEmpty()) {
      throw new TreeException("TreeException:  Empty tree");
    }
    else if (root.getLeft() != null) {
      // a left subtree already exists; it should have been 
      // deleted first
      throw new TreeException("TreeException: " + 
                           "Cannot overwrite left subtree");
    }
    else {
      // assertion: nonempty tree; no left child
      root.setLeft(leftTree.root);
      // don't want to leave multiple entry points into 
      // our tree
      leftTree.makeEmpty(); 
    }  // end if
  }  // end attachLeftSubtree

  public void attachRightSubtree(BinaryTree rightTree)  
                                 throws TreeException {
    if (isEmpty()) {
      throw new TreeException("TreeException:  Empty tree");
    }
    else if (root.getRight() != null) {
      // a right subtree already exists; it should have been 
      // deleted first
      throw new TreeException("TreeException: " + 
                          "Cannot overwrite right subtree");
    }
    else {
      // assertion: nonempty tree; no right child
      root.setRight(rightTree.root);
      // don't want to leave multiple entry points into 
      // our tree
      rightTree.makeEmpty(); 
    }  // end if
  }  // end attachRightSubtree
  
  protected BinaryTree(TreeNode rootNode) {
    root = rootNode;
  }  // end protected constructor

  public BinaryTree detachLeftSubtree()  
                         throws TreeException {
    if (isEmpty()) {
      throw new TreeException("TreeException:  Empty tree");
    }
    else {
      // create a new binary tree that has root's left 
      // node as its root
      BinaryTree leftTree;
      leftTree = new BinaryTree(root.getLeft());
      root.setLeft(null);
      return leftTree;
    }  // end if
  }  // end detachLeftSubtree

  public BinaryTree detachRightSubtree() 
                         throws TreeException {
    if (isEmpty()) {
      throw new TreeException("TreeException:  Empty tree");
    }
    else {
      BinaryTree rightTree;
      rightTree = new BinaryTree(root.getRight());
      root.setRight(null);
      return rightTree;
    }  // end if
  }  // end detachRightSubtree
  
  public static void main(String args[]){
	  Term hello = new Term("sure");
	  BinaryTree myTree = new BinaryTree(hello);
	  System.out.println(myTree.getRoot().getItem().getName());
	  Term hi = new Term("fme");
	  Term bye = new Term("please");
	  myTree.attachLeft(hi);
	  System.out.println(myTree.getRoot().getLeft().getItem().getName());
	  myTree.attachRight(hi);
	BinaryTree newTree = new BinaryTree(bye);
	newTree.attachRight(hello);
	myTree.getRoot().getRight().setRight(newTree.root);
	System.out.println(myTree.getRoot().getRight().getRight().getRight().getItem().getName());
	
	  
  }

} // end BinaryTree
