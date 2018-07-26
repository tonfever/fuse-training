# Rest DSL Example

A Camel Spring Boot Application that exposes three Rest endpoints. The mortgage application service combines data from another two backend Rest Services using an Aggregation Strategy.

# Run the Example

mvn spring-boot:run

# Endpoints

- http://localhost:8081/applicants/getApplicant
- http://localhost:8081/properties/getProperty
- http://localhost:8081/mortgages/getmortgageApplication

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1674b4fcb7254e76ce5d)
