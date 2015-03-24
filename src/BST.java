import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class BST {
	TreeNode root;
	int count;
	BinaryTree myTree;
	public int depth=1;
	
	public BST(){
		 root = null;
		 count = 0;
	}
	
	public int size(){
		
		InorderIterator iter = new InorderIterator(myTree);
		iter.setInorder();
		while(iter.hasNext()){
			iter.next();
			count++;
		}
			
		
		return count;
	}
	
	public void add(String documentName, String word){
		if(root==null){
			Term theRoot = new Term(word);
			theRoot.incFrequency(documentName);
			myTree = new BinaryTree (theRoot);
			root = myTree.root;
		}else{
			addHelp(myTree.root,word, documentName);
		}
	}
	
	
	public void addHelp(TreeNode n, String word, String docName){
	
		if (n.getItem().getName().compareTo(word)<0 ){
			
			if(n.getRight()==null){
				Term right = new Term(word);
				right.incFrequency(docName);
				TreeNode t1 = new TreeNode(right);
				n.setRight(t1);
			
			}else{
				addHelp(n.getRight(),word,docName);
			}
		}else if (n.getItem().getName().compareTo(word)>0){
			//System.out.println("Made it here");
			if(n.getLeft()==null){
				Term left = new Term(word);
				left.incFrequency(docName);
				TreeNode t2 = new TreeNode(left);
				n.setLeft(t2);
			
			}else{
				addHelp(n.getLeft(),word,docName);
			}
		}else{
			//System.out.println("Made it here");
			n.getItem().setTotalFrequency(n.getItem().getTotalFrequency()+1);
			n.getItem().incFrequency(docName);
		}

	}

	
		  public Term get(String word, Boolean printDepth) {
			  depth = 1;
			  String lower = word.toLowerCase();
		      Term found= findInTree(root, lower);
		      if(printDepth == true)
		    	  System.out.println("  "+"At depth "+depth);
		      return found;
		  }
		    public Term findInTree(TreeNode node, String word) {
		        if(node==null) return null;
		        if(node.getItem().getName().compareTo(word)>0) {
		            depth++;
		            return findInTree(node.getLeft(), word);
		        }
		        else if (node.getItem().getName().compareTo(word)<0) {
		            depth++;
		            return findInTree(node.getRight(), word);
		        }
		        else
		            return node.getItem();
		    }
		    
		    public static void main (String args[]){
		    	BST t1 = new BST();
		    	t1.add("Document", "word");
		    	t1.add("Document", "word");
		    	t1.add("Document", "new");
		    	System.out.println(t1.myTree.root.getItem().getName()+" "+t1.myTree.root.getItem().getTotalFrequency());
		    	System.out.println(t1.myTree.root.getLeft().getItem().getName());
		    }

}
