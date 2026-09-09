package com.bcs.zsg.zextra.backend.helper;

/**
 * This exception may be thrown by methods that have detected full queue or empty queue 
 * when add or poll the data in queue.
 */
public class QueueException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a QueueException by default
	 */
	public QueueException() {
		super();
	}
	
	/**
	 * Constructs a QueueException with given additional information
	 * @param s
	 */
	public QueueException(String s) {
		super(s);
	}
}
