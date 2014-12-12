package com.baristamatic.exceptions;

/**
 * <p> Title: InvertoryException.java </p>
 * <p> Description: Thrown upon encountering inventory shortages.  
 *
 * </p>
 * <p> 2008</p>
 * @author RHolland
 *
 *
 */ 
public class InventoryException extends Exception {

	/**
	 * 
	 */
	public InventoryException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InventoryException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public InventoryException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public InventoryException(Throwable arg0) {
		super(arg0);
	}

}
