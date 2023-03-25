package com.marcosflobo.graphql;

import com.marcosflobo.model.Content;
import com.marcosflobo.repository.DbRepository;
import graphql.schema.DataFetcher;
import jakarta.inject.Singleton;

@Singleton
public class GraphQLDataFetchers {

  private final DbRepository dbRepository;

  public GraphQLDataFetchers(DbRepository dbRepository) {
    this.dbRepository = dbRepository;
  }

  public DataFetcher<Content> getContentBydIdDataFetcher() {
    return dataFetchingEnvironment -> {

      String id = dataFetchingEnvironment.getArgument("id");
      return dbRepository.findAllContents()
          .stream()
          .filter(content -> content.getId().equals(id))
          .findFirst()
          .orElse(null);
    };
  }
}
