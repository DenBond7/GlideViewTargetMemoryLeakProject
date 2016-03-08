package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net;

import com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.https.CustomSSLSocketFactory;
import com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.https.WithoutValidationHostnameVerifier;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author Denis Bondarenko
 *         Date: 10.12.2015
 *         Time: 17:05
 *         E-mail: DenBond7@gmail.com
 */
public class OkHttpHelperWithoutHttpsCheck {
  private static OkHttpHelperWithoutHttpsCheck ourInstance = new OkHttpHelperWithoutHttpsCheck();
  private OkHttpClient okHttpClient;

  private OkHttpHelperWithoutHttpsCheck() {
    okHttpClient = new OkHttpClient();
    try {
      okHttpClient.setHostnameVerifier(new WithoutValidationHostnameVerifier());
      okHttpClient.setSslSocketFactory(new CustomSSLSocketFactory());
      okHttpClient.setConnectTimeout(2, TimeUnit.MINUTES);
      okHttpClient.setReadTimeout(2, TimeUnit.MINUTES);
      okHttpClient.setWriteTimeout(2, TimeUnit.MINUTES);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static OkHttpHelperWithoutHttpsCheck getInstance() {
    return ourInstance;
  }

  public OkHttpClient getOkHttpClient() {
    return okHttpClient;
  }
}
