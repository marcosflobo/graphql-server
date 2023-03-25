package com.marcosflobo.repository;

import com.marcosflobo.model.Content;
import com.marcosflobo.model.ContentJSON;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@Singleton
public class DbRepository {

  private static final List<Content> contents = Arrays.asList(
      new Content("1", "First content",
          new ContentJSON("This is a text", "https://"))
  );

  public List<Content> findAllContents() {
    return contents;
  }

  public List<ContentJSON> findAllContentJSON() {
    return contents.stream().map(Content::getContent).collect(Collectors.toList());
  }
}
