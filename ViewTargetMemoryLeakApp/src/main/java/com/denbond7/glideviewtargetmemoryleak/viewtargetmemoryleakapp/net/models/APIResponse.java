package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.models;

/**
 * @author Denis Bondarenko
 *         Date: 27.01.2015
 *         Time: 14:16
 *         E-mail: DenBond7@gmail.com
 */
public class APIResponse<T> {
  private T result;
  private APIError apiError;

  public APIResponse(T result) {
    this(result, null);
  }

  public APIResponse(T result, APIError apiError) {
    this.result = result;
    this.apiError = apiError;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public APIError getApiError() {
    return apiError;
  }

  public void setApiError(APIError apiError) {
    this.apiError = apiError;
  }
}
