package com.marcosflobo.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceResolver;
import jakarta.inject.Singleton;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Factory
public class GraphQLFactory {

  @Bean
  @Singleton
  public GraphQL graphQL(ResourceResolver resourceResolver, GraphQLDataFetchers graphQLDataFetchers) {

    SchemaParser schemaParser = new SchemaParser();
    TypeDefinitionRegistry typeDefinitionRegistry = new TypeDefinitionRegistry();

    // Read the GraphQL schema definition
    Optional<InputStream> graphQlSchemaFile = resourceResolver.getResourceAsStream("classpath:schema.graphqls");
    if (graphQlSchemaFile.isPresent()) {
      // parse the schema into the registry object
      typeDefinitionRegistry.merge(
          schemaParser.parse(new BufferedReader(new InputStreamReader(graphQlSchemaFile.get())))
      );

      // Lets wire the Schema definition with the right data fetcher
      RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
          .type(TypeRuntimeWiring.newTypeWiring("Query")
              .dataFetcher("contentById", graphQLDataFetchers.getContentBydIdDataFetcher()))
          .build();

      // Finally, let's "join" the wire and the GraphQLSchema to ge returned on the GraphQL object
      SchemaGenerator schemaGenerator = new SchemaGenerator();
      GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(
          typeDefinitionRegistry,
          runtimeWiring);

      log.info("Returning GraphQL object from Schema found");
      return GraphQL.newGraphQL(graphQLSchema).build();
    } else {
      // GraphQL file not found, so we return an empty schema
      log.warn("The GraphQL schema was not found, returning an empty GraphQL object");
      return new GraphQL.Builder(GraphQLSchema.newSchema().build()).build();
    }
  }
}
