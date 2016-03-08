package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.RetrofitHelper;
import com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.models.APIRequest;
import com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.models.APIResponse;

/**
 * @author Denis Bondarenko
 *         Date: 27.01.2015
 *         Time: 14:35
 *         E-mail: DenBond7@gmail.com
 */
public abstract class APIAsyncTaskLoader<T> extends AsyncTaskLoader<APIResponse<T>> {
  private APIRequest apiRequest;
  private RetrofitHelper retrofitHelper;

  public APIAsyncTaskLoader(Context context, RetrofitHelper retrofitHelper, APIRequest apiRequest) {
    super(context);
    this.retrofitHelper = retrofitHelper;
    this.apiRequest = apiRequest;
    onContentChanged();
  }

  @Override
  public void onStartLoading() {
    if (takeContentChanged())
      forceLoad();
  }

  @Override
  public APIResponse<T> loadInBackground() {
    if (retrofitHelper != null && apiRequest != null) {
      APIResponse<T> typeAPIResponse = new APIResponse(getResponse(retrofitHelper, apiRequest));
      return typeAPIResponse;
    }

    return null;
  }

  @Override
  public void onStopLoading() {
    cancelLoad();
  }

  public APIRequest getApiRequest() {
    return apiRequest;
  }

  public abstract T getResponse(RetrofitHelper retrofitHelper, APIRequest apiRequest);
}
