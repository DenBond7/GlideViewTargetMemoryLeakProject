package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp.net.models;

import java.util.LinkedHashMap;

/**
 * @author Denis Bondarenko
 *         Date: 27.01.2015
 *         Time: 14:16
 *         E-mail: DenBond7@gmail.com
 */
public abstract class APIRequest<T> {
  private RequestType requestType;
  private LinkedHashMap<String, String> parametersMap;
  private String urlPath;
  private T responseType;

  public APIRequest(T type) {
    this.responseType = type;
    this.requestType = RequestType.GET;
    parametersMap = new LinkedHashMap<>();
  }

  public RequestType getRequestType() {
    return requestType;
  }

  public void setRequestType(RequestType requestType) {
    this.requestType = requestType;
  }

  public LinkedHashMap<String, String> getParametersMap() {
    return parametersMap;
  }

  public void setParametersMap(LinkedHashMap<String, String> parametersMap) {
    this.parametersMap = parametersMap;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public void setUrlPath(String urlPath) {
    this.urlPath = urlPath;
  }

  public T getResponseType() {
    return responseType;
  }

  public void setResponseType(T responseType) {
    this.responseType = responseType;
  }
}
