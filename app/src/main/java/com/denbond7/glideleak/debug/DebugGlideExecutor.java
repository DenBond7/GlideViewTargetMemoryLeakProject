package com.denbond7.glideleak.debug;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.bumptech.glide.load.engine.executor.Prioritized;

@SuppressWarnings("all")
public class DebugGlideExecutor extends ThreadPoolExecutor {
	private static final String TAG = "PriorityExecutor";
	private final BlockingQueue<Runnable> queue;
	private final AtomicInteger ordering = new AtomicInteger();

	public DebugGlideExecutor(int poolSize, BlockingQueue<Runnable> queue) {
		super(poolSize, poolSize, (long)0, TimeUnit.MILLISECONDS, queue, new DebugThreadFactory());
		this.queue = queue;
	}

	@Override protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
		return new DebugLoadTask<T>(runnable, value, ordering.getAndIncrement());
	}

	@Override protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		if (t == null && r instanceof Future<?>) {
			Future<?> future = (Future<?>)r;
			if (future.isDone() && !future.isCancelled()) {
				try {
					future.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class DebugThreadFactory implements ThreadFactory {
		int threadNum = 0;
		@Override public Thread newThread(Runnable runnable) {
			final Thread result = new DebugGlideThread(runnable, threadNum);
			threadNum++;
			return result;
		}
		/** Extracted anon inner class to named one to make sure closure doesn't interfere -> no change in behavior */
		private static class DebugGlideThread extends Thread {
			public DebugGlideThread(Runnable runnable, int threadNum) {
				super(runnable, "debug-fifo-pool-thread-" + threadNum);
			}
			@Override public void run() {
				android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
				super.run();
			}
		}
	}

	static class DebugLoadTask<T> extends FutureTask<T> implements Comparable<DebugLoadTask<?>> {
		private final int priority;
		private final int order;
		private final Runnable runnable;
		/** toString of runnable for matching in heap dump against logs */
		private final String string;

		public DebugLoadTask(Runnable runnable, T result, int order) {
			super(runnable, result);
			this.runnable = runnable;
			if (!(runnable instanceof Prioritized)) {
				throw new IllegalArgumentException("FifoPriorityThreadPoolExecutor must be given Runnables that "
						+ "implement Prioritized");
			}
			priority = ((Prioritized)runnable).getPriority();
			this.order = order;
			this.string = runnable.toString();
		}

		@Override public String toString() {
			return super.toString() + " " + runnable.toString();
		}
		
		@Override public boolean equals(Object o) {
			if (o instanceof DebugLoadTask) {
				DebugLoadTask<?> other = (DebugLoadTask<?>)o;
				return order == other.order && priority == other.priority;
			}
			return false;
		}

		@Override public int hashCode() {
			int result = priority;
			result = 31 * result + order;
			return result;
		}

		@Override public int compareTo(DebugLoadTask<?> loadTask) {
			int result = priority - loadTask.priority;
			if (result == 0) {
				result = order - loadTask.order;
			}
			return result;
		}
	}
}
