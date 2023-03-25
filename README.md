# graphql-server
This is a GraphQL Server that extracts the data from a PostgreSQL database.

This server will expose a GraphQL API to read the information from a database. This exercise does
not include the INSERt/DELETE/UPDATE of the data from the API.

## Database
In this exercise, the database is not present, and it's replaced by a in-memory list of elements.

## Run the server
### Run the server
```shell
./gradlew run
```

### Test the server
```shell
curl --location --request POST 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"query{contentById(id: \"1\") {id, title}}","variables":{}}'
```

## Micronaut 3.8.7 Documentation

- [User Guide](https://docs.micronaut.io/3.8.7/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.8.7/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.8.7/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


## Feature graphql documentation

- [Micronaut GraphQL documentation](https://micronaut-projects.github.io/micronaut-graphql/latest/guide/index.html)


