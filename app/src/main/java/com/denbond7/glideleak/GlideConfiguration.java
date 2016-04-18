package com.denbond7.glideleak;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.engine.cache.MemoryCacheAdapter;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.denbond7.glideleak.debug.*;
import com.denbond7.glideleak.net.OkHttpHelperWithoutHttpsCheck;
import com.squareup.okhttp.OkHttpClient;

import java.io.InputStream;

/**
 * @author Denis Bondarenko
 *         Date: 16.11.2015
 *         Time: 11:21
 *         E-mail: DenBond7@gmail.com
 */
public class GlideConfiguration implements GlideModule {
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {
    builder.setMemoryCache(new MemoryCacheAdapter());
    builder.setBitmapPool(new BitmapPoolAdapter());
    builder.setDiskCacheService(new DebugGlideExecutor(1, new NonBuggyPriorityBlockingQueue()));
  }

  @Override
  public void registerComponents(Context context, Glide glide) {
    OkHttpClient client = OkHttpHelperWithoutHttpsCheck.getInstance().getOkHttpClient();
    glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
  }
}
