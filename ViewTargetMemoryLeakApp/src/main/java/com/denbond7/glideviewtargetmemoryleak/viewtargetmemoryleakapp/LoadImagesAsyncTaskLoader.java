package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.RetrofitHelper;
import retrofit.RetrofitError;

import java.util.List;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 16:28
 *         E-mail: DenBond7@gmail.com
 */
public class LoadImagesAsyncTaskLoader extends AsyncTaskLoader<List<String>> {

  public static final String RESPONSE_HEADER_NAME_SET_COOKIE = "Set-Cookie";
  private RetrofitHelper retrofitHelper;
  private ApiService apiService;

  public LoadImagesAsyncTaskLoader(Context context, RetrofitHelper retrofitHelper) {
    super(context);
    this.retrofitHelper = retrofitHelper;
    onContentChanged();
  }

  @Override
  public void onStartLoading() {
    if (takeContentChanged())
      forceLoad();
  }

  @Override
  public List<String> loadInBackground() {
    if (retrofitHelper != null && retrofitHelper.getRestAdapter() != null) {
      apiService = retrofitHelper.getRestAdapter().create(ApiService.class);

      if (apiService != null) {
        try {
          ImagesContainer imagesContainer = apiService.getImages();
          if (imagesContainer != null) {
            return imagesContainer.getImages();
          }
        } catch (RetrofitError retrofitError) {
          retrofitError.printStackTrace();
        }
      }
    }

    return null;
  }

  @Override
  public void onStopLoading() {
    cancelLoad();
  }

}
