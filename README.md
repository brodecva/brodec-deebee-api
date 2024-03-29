# Brodec DeeBee API Demo

DeeBee API is a simplified database browser RESTful API based on Spring Boot and Jersey. As of now, it support latests Postgres dialects, but it can be (thanks to the usage of almost completely standardized means) easily extended to other SQL vendors. 

## Key features

- JooQ inside
  - DeeBee metadata model is a wrapper over the metadata model provided by the Jooq library, which itself is a wrapper over JDBC methods.
  - The key advantage of Jooq over JDBC methods is that it completely abstracts away querying information_schema tables (which serve as the metamodel for many standards-adhering SQL databases), providing clean and easy-to-use interface.
- Usage of MapStruct to effortlessly adapt the JooQ metamodel to the actual data model of the API. Most attributes of the JooQ model are automatically mapped getters. And those that are not can still be easily mapped without much boileplate code. All that during compilation and with type checking.
  - This allows to dramatically extend the range of supported metamodel objects in the future.
- REST API documentation can be generated by Enunciate from Javadoc, JAX-RS and other annotations associated with the resources and endpoints.
- Usage of embedded Map-based persistent key store ChronicleMap as convenient and performant solution for keeping of the connection details.

## Usage

### Build and run

Use

```bash
mvn spring-boot:run
```

to run the service. By default, a temporary file is used to persist the connection database. In order to use the same DB in the next run, pass (through .properties or as Spring Boot runtime property) the path to the desired file location as property named 

```bash
net.brodec.sandbox.deebee.chronicle.persistenceFile
```
.

### Generate documentation

Use

```bash
mvn com.webcohesion.enunciate:enunciate-maven-plugin:docs
```

to build the API documentation (in the target/rest directory). The documentation generation is suspended for normal Maven compile goal, as it takes too long, unless turned on by specific Enunciate properties.

### Queries

Then you can interact with the API for example like this (for the complete overview, please consult the generated HTML documentation):

```bash
curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/api/connections/ --data '{ "name" : "test", "hostUrl" : "jdbc:postgresql://localhost:5435/postgres", "database" : "postgres", "schema" : "test", "username" : "postgres", "password" : "secret", "vendorDialect" : "POSTGRES" }'
```
.

## TODOs and shortfalls

- There are no automatic tests. While the code is generally designed to be testable, there was just not enough time to implement them, the more so to provide relevant coverage. And most of them would require a lot of scafolding, while the actual logic is pretty straightforward. Same for comments.
- JooQ is unable to access the tables in the information_schema and (Postgre-specific) pg_catalog through its metamodel object. This is usually not necessarry. When needed, maybe a proper object model generation from the database schema might help to generate the model of these system schemas without too much work.
- Every REST API call uses a separate connection, which takes time to initialize. This is something which might be hard to fix without bringing statefulness to the API.
- There is no security whatsoever.
- The preview implementation is intentionally reduced to limit and offset, but thanks to Jooq it would be trivial to implement conditions, aggregate function, sorting and other. Only array of arrays representation of the result set is implemented.
- The resources are intentionally kept to required minimum (but they faithfully map all the available underlying Jooq attributes and thanks to the chosen soultion could be easily extended). And they are aimed to always require a separate call when a collection of objects is needed.
- Enunciate is a bit obsolete library and the generation of demo pages within the documentation really does not work that well. Also, the documentation could be served by the Boot as well, ideally.

## Contributing
This is just a throway project.
