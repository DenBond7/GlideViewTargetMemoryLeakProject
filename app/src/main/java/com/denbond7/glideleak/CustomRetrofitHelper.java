package com.denbond7.glideleak;

import com.denbond7.glideleak.net.RetrofitHelper;
import com.google.gson.GsonBuilder;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 16:56
 *         E-mail: DenBond7@gmail.com
 */
public class CustomRetrofitHelper extends RetrofitHelper {
  public static final String BASE_URL = "https://raw.githubusercontent.com/DenBond7/GlideViewTargetMemoryLeakProject/master";
  private static CustomRetrofitHelper ourInstance = new CustomRetrofitHelper();

  private CustomRetrofitHelper() {
    super(new RestAdapter.Builder()
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setConverter(new GsonConverter(new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .serializeNulls()
            .create()))
        .setEndpoint(BASE_URL));
  }

  public static CustomRetrofitHelper getInstance() {
    return ourInstance;
  }
}
