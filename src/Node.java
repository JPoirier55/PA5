/**
 * Implements a simple node object for use in lists for use in CSU
 * CS200 Fall 2010 Lab 9.
 * Based on code downloaded from the Carrano and Pritchard
 * text web site in 2007. Modified to make greater use of
 * generics.
 * @author David Newman
 * @date 2010-10-14
 *
 * @param <T>
 * MODIFIED BY JAKE LORD 10/18/14
 */public class Node {
  private Term item;
  private Node next;

  public Node(Term newItem) {
    item = newItem;
    next = null;
  } // end constructor

  public Node(Term newItem, Node nextNode) {
    item = newItem;
    next = nextNode;
  } // end constructor

  public void setItem(Term newItem) {
    item = newItem;
  } // end setItem

  public Term getItem() {
    return item;
  } // end getItem

  public void setNext(Node nextNode) {
    next = nextNode;
  } // end setNext

  public Node getNext() {
    return next;
  } // end getNext

} // end class Node

