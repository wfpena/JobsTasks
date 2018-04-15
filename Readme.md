# UNISYS-TEST API documentation
---
This application provides an API to save Jobs and Tasks. Providing basic features like adding, updating, deleting, restrieving and sorting.
## Getting Started
In order to simply run the app you will need to:

1. Go with the command line to the root directory (where the file pom.xml is).
2. Run the `mvn spring-boot:run` command.
3. Done.

After doing this you will see something along the lines of
```console
unisys.test.MainApp: Started MainApp in 9.734 seconds (JVM running for 21.849)
```
This means the app is already running.

#### Running App with Maven Wrapper
In case you don't have Maven installed in your machine, you can use the maven wrapper available in the sorce code.

In this case, instead of using the  `mvn spring-boot:run` use`./mvnw spring-boot:run`

## Testing

In order to run the tests you will need to:

1. Go with the command line to the root directory (where the file pom.xml is).
2. Run the `mvn test` command.
3. Done.

After doing this you will see the console logs and eventually the tests will run, something like this:

```bash
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running unisys.test...
```
After that the tests results will show up on the console.
#### Running Tests with Maven Wrapper
In case you don't have Maven installed in your machine, you can use the maven wrapper available in the sorce code.

In this case, instead of using the  `mvn test` use`./mvnw test`
