package org.tain.tools.queue;

import java.util.LinkedList;

public class MonQueue<T> {

	private final LinkedList<T> queue = new LinkedList<>();
	
	public synchronized void set(T object) {
		this.queue.addLast(object);
		this.notifyAll();
	}
	
	public synchronized T get() {
		while (this.queue.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		return this.queue.removeFirst();
	}
	
	public synchronized void clear() {
		this.queue.clear();
	}
	
	public int size() {
		return this.queue.size();
	}
}
