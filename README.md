# Users service
This is a multi-tenant (client) service which exposes a REST API to manage users. It is built using Spring Boot and Gradle as dependency manager.
It uses an embedded version of MongoDB to write/read data and implements the standard CRUD functionalities.

The application is structured using the following layers, which also reflect on the package name.

### Controllers
The classes in this package are mainly responsible for mapping API endpoints.
This layer uses `model` classes as API object representation. These object will be different from the ones actually written in the database.
All the business logic is delegated to the Service layer.

### Service
The classes in this package are responsible for all the business logic, input validation, and mapping between API (model) objects and data (domain) objects.

### Repository
The data access layer. The classes in this package are responsible for reading from/writing to the database, using the data (entity) object representation.

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

## Task

The `UserController` has been implemented to give three endpoints:

* `api/v1/users` Post - creates a user with given data
* `api/v1/users/{userId}` Get - returns user data for the user with given Id
* `api/v1/users` Get - returns all user data

The User data consists of an id, metadata(name, email, companyId and officeId) and role.

This is tested in UserControllerTest.

### TASK 1: 
The getAll endpoint has a failing test. 
The list of users returned includes all users for all clients.

### Task 2:
We now need a new endpoint which can return user address labels.

Office addresses are supplied by the `OfficeClient`

Company names are supplied by the `CompanyClient`

The new endpoint should be:
`api/v1/users/address`

The json returned should be:

```json
[
 {
  "name": String,
  "company": String,
  "firstline": String,
   
  "secondline": String,
  "city": String,
  "postcode": String
 },...
]
```
