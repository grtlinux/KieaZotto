package org.tain.utils;

public class Sleep {

	public static void run(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
}
