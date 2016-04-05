package com.denbond7.glideleak;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Denis Bondarenko
 *         Date: 08.03.2016
 *         Time: 16:36
 *         E-mail: DenBond7@gmail.com
 */
public class ImagesContainer {
  @Expose
  @SerializedName("images")
  private List<String> images;

  public List<String> getImages() {
    return images;
  }
}
