package com.bcs.zsg.zextra.backend.helper;

import java.util.LinkedList;

/**
 * The Queue class define the blocking queue methods, which are common in concurrent programming. 
 * These methods, which wait for elements to appear or for space to become available.
 * This class ordered elements in a FIFO (first-in-first-out) manner.
 */
public class Queue {
	/* Linked list structure to realize the queue */
	protected LinkedList<Object> _list;
	/* Default queue size */
	protected int _maxQueueSize;
	
	protected Object lock = new Object();
	
	/**
	 * Default construction
	 */
	public Queue() {
		this(1024);
	}
	
	/**
	 * Constructs a Queue instance by given queue size.
	 * @param maxQueueSize
	 */
	public Queue(int maxQueueSize) {
		_list = new LinkedList<Object>();
		_maxQueueSize = maxQueueSize;
	}
	
	/**
	 * Insert data into queue
	 * @param item
	 * @throws QueueException
	 */
	public void enqueue(Object item) throws QueueException {
		synchronized (lock) {
			if (_maxQueueSize != 0 && _maxQueueSize == _list.size()) {
				throw new QueueException();
			}
			_list.addLast(item);
		}
	}
	
	/**
	 * Carry out the object in queue
	 * @return
	 */
	public Object dequeue() {
		synchronized (lock) {
			Object obj = peek();
			if (obj != null) {
				_list.removeFirst();
			}
			return obj;
		}
	}
	
	/**
	 * Check Queue whether there is data or not
	 * @return
	 */
	private Object peek() {
		if (_list.size() == 0) return null;
		return _list.getFirst();
	}
	
	/**
	 * Get current queue size
	 * @return
	 */
	public int queueSize() {
		return _list.size();
	}
	
	/**
	 * Check whether queue size is full
	 * @return
	 */
	public boolean isFull() {
		if (_list.size() == _maxQueueSize) return true;
		else return false;
	}
	
	/**
	 * Check whether queue size is empty
	 * @return
	 */
	public boolean isEmpty() {
		if (_list.size() == 0) return true;
		else return false;
	}
	
	/**
	 * Get empty size of queue
	 * @return
	 */
	public int emptySize() {
		return _maxQueueSize - _list.size();
	}
	
	/**
	 * Get max queue size
	 * @return
	 */
	public int maxSize() {
		return _maxQueueSize;
	}
}
