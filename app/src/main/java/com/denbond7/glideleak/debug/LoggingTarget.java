package com.denbond7.glideleak.debug;

import java.util.Locale;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.*;

@SuppressWarnings("all")
public class LoggingTarget<Z> extends WrappingTarget<Z> {
	private final String tag;
	private final int level;
	/** hashCode of this for matching in heap dump against logs */
	private final String hash;
	/** hashCode of target for matching in heap dump against logs */
	private final String targetHash;
	public LoggingTarget(Target<Z> target) {
		this("LoggingTarget", Log.VERBOSE, target);
	}
	public LoggingTarget(String tag, int logLevel, Target<Z> target) {
		super(target);
		this.tag = tag;
		this.level = logLevel;
		hash = String.format(Locale.ROOT, "%08x", System.identityHashCode(this));
		targetHash = String.format(Locale.ROOT, "%08x", System.identityHashCode(target));
	}

	private void log(String method, Object... args) {
		log(method, null, args);
	}
	private void log(String method, Throwable error, Object... args) {
		StringBuilder sb = new StringBuilder()
				.append(target.getClass().getCanonicalName())
				.append('@').append(targetHash)
				.append('/').append(hash)
				.append('.').append(method);
		appendArgs(sb, args);
		sb.append(' ').append('[').append(target).append(']');
		if (error != null) {
			sb.append('\n');
			sb.append(Log.getStackTraceString(error));
		}
		Log.println(level, tag, sb.toString());
	}
	static void appendArgs(StringBuilder sb, Object... args) {
		sb.append('(');
		boolean first = true;
		for (Object arg : args) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(arg);
		}
		sb.append(')');
	}

	@Override public void getSize(final SizeReadyCallback cb) {
		log("getSize", cb);
		super.getSize(new SizeReadyCallback() {
			@Override public void onSizeReady(int width, int height) {
				log("onSizeReady", cb, width, height);
				cb.onSizeReady(width, height);
			}
		});
	}

	@Override public void onLoadStarted(Drawable placeholder) {
		log("onLoadStarted", placeholder);
		super.onLoadStarted(placeholder);
	}
	@Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
		log("onLoadFailed", e, e, errorDrawable);
		super.onLoadFailed(e, errorDrawable);
	}
	@Override public void onResourceReady(Z resource, GlideAnimation<? super Z> glideAnimation) {
		log("onResourceReady", resource, glideAnimation);
		super.onResourceReady(resource, glideAnimation);
	}
	@Override public void onLoadCleared(Drawable placeholder) {
		log("onLoadCleared", placeholder);
		super.onLoadCleared(placeholder);
	}

	@Override public Request getRequest() {
		log("getRequest");
		return super.getRequest();
	}
	@Override public void setRequest(Request request) {
		log("setRequest", request);
		super.setRequest(request);
	}

	@Override public void onStart() {
		log("onStart");
		super.onStart();
	}
	@Override public void onStop() {
		log("onStop");
		super.onStop();
	}
	@Override public void onDestroy() {
		log("onDestroy");
		super.onDestroy();
	}
}
