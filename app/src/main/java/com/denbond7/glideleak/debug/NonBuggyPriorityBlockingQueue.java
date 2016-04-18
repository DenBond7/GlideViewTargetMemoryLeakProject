package com.denbond7.glideleak.debug;

import java.lang.reflect.Field;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import android.os.Build.*;

public class NonBuggyPriorityBlockingQueue extends LoggingPriorityBlockingQueue {
	// KITKAT constant is not available because target is 16;
	private static volatile boolean fix1044 = VERSION.SDK_INT < 19;
	private static final Field LOCK_FIELD = getField("lock");
	private static final Field QUEUE_FIELD = getField("queue");

	private static Field getField(String name) {
		if (!fix1044) {
			return null;
		}
		try {
			Field field = PriorityBlockingQueue.class.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (Exception ex) {
			fix1044 = false;
			return null;
		}
	}
	private ReentrantLock getLock() {
		if (!fix1044) {
			return null;
		}
		try {
			return (ReentrantLock)LOCK_FIELD.get(this);
		} catch (Exception e) {
			fix1044 = false;
			return null;
		}
	}
	private final ReentrantLock lock;
	public NonBuggyPriorityBlockingQueue() {
		lock = getLock();
	}
	

	@Override public Runnable take() throws InterruptedException {
		Runnable result = super.take();
		if (fix1044 && isEmpty()) {
			lock.lock();
			try {
				Object[] queue = (Object[])QUEUE_FIELD.get(this);
				if (queue[0] == result) {
					queue[0] = null;
				}
			} catch (Exception e) {
				fix1044 = false;
			} finally {
				lock.unlock();
			}
		}
		return result;
	}
}
