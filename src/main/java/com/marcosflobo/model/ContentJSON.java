package com.marcosflobo.model;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class ContentJSON {

  private final String text;
  private final String imgurl;

  public ContentJSON(String text, String imgurl) {
    this.text = text;
    this.imgurl = imgurl;
  }

  public String getText() {
    return text;
  }

  public String getImgurl() {
    return imgurl;
  }
}
