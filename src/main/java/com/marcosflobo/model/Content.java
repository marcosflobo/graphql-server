package com.marcosflobo.model;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Content {

  private final String id;
  private final String title;
  private final ContentJSON content;

  public Content(String id, String title, ContentJSON content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public ContentJSON getContent() {
    return content;
  }
}
