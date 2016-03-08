package com.denbond7.glideviewtargetmemoryleak.viewtargetmemoryleakapp;

import retrofit.http.GET;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 16:35
 *         E-mail: DenBond7@gmail.com
 */
public interface ApiService {
  String REQUEST_HEADER_NAME_COOKIE = "Cookie";

  @GET("/glide_test.json")
  public ImagesContainer getImages();
}
