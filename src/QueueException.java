/**
 * Implements a simple exception class for use in CSU
 * CS200 Fall 2010 Lab 9.
 * Based on code downloaded from the Carrano and Pritchard
 * text web site in 2007.
 * @author David Newman
 * @date 2010-10-14
 *
 */
@SuppressWarnings("serial")
public class QueueException extends RuntimeException {

  public QueueException(String s) {
    super(s);
  }  // end constructor
}  // end QueueException