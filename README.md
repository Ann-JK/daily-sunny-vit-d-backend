### Preparing PostgreSQL and creating database
1. Make sure docker engine is running and run docker container with the following command in the terminal: 
<br><code>docker run --name postgres-dvit -e POSTGRES_PASSWORD=vitamind -p 5432:5432 -d postgres:alpine</code>
   <br>This command will start PostgreSQL container listening for connection on localhost:5432:5432.
  - Username: postgres
  - Password: vitamind
2. Enter the container with the following command: <br><code>docker exec -it postgres-dvit bash
   </code>
3. Enter psql: <br><code>psql -U postgres</code>
4. Create database: <br><code>create database dvit_db;</code>

---

## Micronaut 4.2.4 Documentation

- [User Guide](https://docs.micronaut.io/4.2.4/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.2.4/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.2.4/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Maven Plugin documentation](https://micronaut-projects.github.io/micronaut-maven-plugin/latest/)
## Feature slf4j-simple-logger documentation

- [https://github.com/GoodforGod/slf4j-simple-logger](https://github.com/GoodforGod/slf4j-simple-logger)


## Feature data-jdbc documentation

- [Micronaut Data JDBC documentation](https://micronaut-projects.github.io/micronaut-data/latest/guide/index.html#jdbc)


## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#nettyHttpClient)


## Feature liquibase documentation

- [Micronaut Liquibase Database Migration documentation](https://micronaut-projects.github.io/micronaut-liquibase/latest/guide/index.html)

- [https://www.liquibase.org/](https://www.liquibase.org/)


## Feature maven-enforcer-plugin documentation

- [https://maven.apache.org/enforcer/maven-enforcer-plugin/](https://maven.apache.org/enforcer/maven-enforcer-plugin/)


## Feature groovy-maven-plus-plugin documentation

- [https://github.com/groovy/GMavenPlus/wiki/Usage](https://github.com/groovy/GMavenPlus/wiki/Usage)


## Feature security documentation

- [Micronaut Security documentation](https://micronaut-projects.github.io/micronaut-security/latest/guide/index.html)


## Feature annotation-api documentation

- [https://jakarta.ee/specifications/annotations/](https://jakarta.ee/specifications/annotations/)


## Feature test-resources documentation

- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)


## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)


