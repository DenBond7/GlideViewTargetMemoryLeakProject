package com.denbond7.glideleak.net;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author Denis Bondarenko
 *         Date: 27.01.2015
 *         Time: 14:41
 *         E-mail: DenBond7@gmail.com
 */
public class OkHttpHelper {
  private static OkHttpHelper ourInstance = new OkHttpHelper();
  private OkHttpClient okHttpClient;

  private OkHttpHelper() {
    okHttpClient = new OkHttpClient();
    try {
      okHttpClient.setConnectTimeout(2, TimeUnit.MINUTES);
      okHttpClient.setReadTimeout(2, TimeUnit.MINUTES);
      okHttpClient.setWriteTimeout(2, TimeUnit.MINUTES);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static OkHttpHelper getInstance() {
    return ourInstance;
  }

  public OkHttpClient getOkHttpClient() {
    return okHttpClient;
  }
}
