package com.denbond7.glideleak;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
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
    builder.setMemoryCache(new LruResourceCache(1000 * 1000 * 3));
    builder.setBitmapPool(new BitmapPool() {
      @Override
      public int getMaxSize() {
        return 0;
      }

      @Override
      public void setSizeMultiplier(float v) {

      }

      @Override
      public boolean put(Bitmap bitmap) {
        return false;
      }

      @Override
      public Bitmap get(int i, int i1, Bitmap.Config config) {
        return null;
      }

      @Override
      public Bitmap getDirty(int i, int i1, Bitmap.Config config) {
        return null;
      }

      @Override
      public void clearMemory() {

      }

      @Override
      public void trimMemory(int i) {

      }
    });
  }

  @Override
  public void registerComponents(Context context, Glide glide) {
    OkHttpClient client = OkHttpHelperWithoutHttpsCheck.getInstance().getOkHttpClient();
    glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
  }
}
