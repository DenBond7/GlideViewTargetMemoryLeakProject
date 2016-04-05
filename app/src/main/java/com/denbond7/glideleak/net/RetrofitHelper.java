package com.denbond7.glideleak.net;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * @author Denis Bondarenko
 *         Date: 27.01.2015
 *         Time: 14:40
 *         E-mail: DenBond7@gmail.com
 */
public class RetrofitHelper {
  private OkHttpHelper okHttpHelper;
  private RestAdapter restAdapter;

  public RetrofitHelper(RestAdapter.Builder builder) {
    okHttpHelper = OkHttpHelper.getInstance();
    if (okHttpHelper != null) {
      OkClient okClient = new OkClient(okHttpHelper.getOkHttpClient());
      restAdapter = builder.setClient(okClient).build();
    }
  }

  public OkHttpHelper getOkHttpHelper() {
    return okHttpHelper;
  }

  public RestAdapter getRestAdapter() {
    return restAdapter;
  }
}
