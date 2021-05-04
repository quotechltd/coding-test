# Users service
This is a service which exposes some REST API to manage users. It is built using Spring Boot and Gradle as dependency manager.
It uses an embedded version of MongoDB to write/read data and implements the standard CRUD functionalities.

The application is structured using the following layers, which also reflect on the package name.

### Resource
The classes in this package are mainly responsible for mapping API endpoints.
This layer uses `model` classes as API object representation. These object will be different from the ones actually written in the database.
All the business logic is demanded to the Service layer.

### Service
The classes in this package are responsible for all the business logic, input validation, and mapping between API (model) objects and data (domain) objects.

### Repository
The data access layer. The classes in this package are responsible for reading from/writing to the database, using the data (domain) object representation.

## Build and run
### Within your IDE
If you use an IDE like IntelliJ or Eclipse, you will get everything you need to build and run a Gradle application. 
### Outside your IDE
You will need to install Gradle on your machine. Once you do that, you can simply build with 
```
gradle build
```
and run the app with 
```
gradle bootRun
```