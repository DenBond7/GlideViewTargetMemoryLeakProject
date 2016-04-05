package com.denbond7.glideleak.debug;

import java.util.*;
import java.util.concurrent.*;

import android.util.Log;

@SuppressWarnings("all")
public class LoggingPriorityBlockingQueue extends PriorityBlockingQueue<Runnable> {
	private void log(String method, Object... args) {
		StringBuilder sb = new StringBuilder().append(this).append('.').append(method);
		LoggingTarget.appendArgs(sb, args);
		sb.append(' ').append("size=").append(super.size());

		Log.println(Log.ASSERT, "Queue", sb.toString());
	}

	@Override public boolean add(Runnable runnable) {
		log("add", runnable);
		return super.add(runnable);
	}
	@Override public boolean offer(Runnable runnable) {
		log("offer", runnable);
		return super.offer(runnable);
	}
	@Override public void put(Runnable runnable) {
		log("put", runnable);
		super.put(runnable);
	}
	@Override public boolean offer(Runnable runnable, long timeout, TimeUnit unit) {
		log("offer", runnable, timeout, unit);
		return super.offer(runnable, timeout, unit);
	}
	@Override public Runnable poll() {
		log("poll");
		return super.poll();
	}
	@Override public Runnable take() throws InterruptedException {
		log("take");
		return super.take();
	}
	@Override public Runnable poll(long timeout, TimeUnit unit) throws InterruptedException {
		log("poll", timeout, unit);
		return super.poll(timeout, unit);
	}
	@Override public Runnable peek() {
		log("peek");
		return super.peek();
	}
	@Override public Comparator<? super Runnable> comparator() {
		log("comparator");
		return super.comparator();
	}
	@Override public int size() {
		log("size");
		return super.size();
	}
	@Override public int remainingCapacity() {
		log("remainingCapacity");
		return super.remainingCapacity();
	}
	@Override public boolean remove(Object o) {
		log("remove", o);
		return super.remove(o);
	}
	@Override public boolean contains(Object o) {
		log("contains", o);
		return super.contains(o);
	}
	@Override public Object[] toArray() {
		log("toArray");
		return super.toArray();
	}
	@Override public int drainTo(Collection<? super Runnable> c) {
		log("drainTo", c);
		return super.drainTo(c);
	}
	@Override public int drainTo(Collection<? super Runnable> c, int maxElements) {
		log("drainTo", c, maxElements);
		return super.drainTo(c, maxElements);
	}
	@Override public void clear() {
		log("clear");
		super.clear();
	}
	@Override public <T> T[] toArray(T[] a) {
		log("toArray", a);
		return super.toArray(a);
	}
}
