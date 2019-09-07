package com.test;

public class ResourceNotFoundException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5265167426869051119L;

	public ResourceNotFoundException(String msg) {
        super("NotFound:"+msg);
    }
	
	
}
