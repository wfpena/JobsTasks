# UNISYS-TEST API documentation
---
This application provides an API to save Jobs and Tasks. Providing basic features like adding, updating, deleting, restrieving and sorting.
## Getting Started
In order to run the app you will need to:

1. Open command line and go to the root directory of the project (where the file pom.xml is).
2. Run the `mvn spring-boot:run` command.
3. Done.

After doing this you will see something along the lines of:
```console
unisys.test.MainApp: Started MainApp in 9.734 seconds (JVM running for 21.849)
```
This means the app is already running.

#### Running App with Maven Wrapper
In case you don't have Maven installed in your machine, you can use the maven wrapper available in the sorce code.

In this case, instead of using the  `mvn spring-boot:run` use`./mvnw spring-boot:run`

## The API
---
There are two groups of api methods to access: the Jobs and the Tasks.

### Jobs
These are the endpoints for manipulating the jobs:

* **/jobs**
    * **Allowed Methods:** `GET` | `POST` 
    * **Action**: `GET`
        * **URL Params:** None  
        *  **Query Params:**

           *  **Required:** None
    
           * **Optional:** `order=[boolean]`
            
            * **Success Response:**
                * Code: 200
                * Content: If `order=true` returns list of Jobs ordered by the sum of its tasks weight, otherwise returns the list unordered.
            * **Error Response:** 
                * Code: 500
                * Content: Error description
            
        * **Example:** `/jobs?order=true`
    * **Action**: `POST`
    *  **URL Params:**

       *  **Required:** None

       * **Optional:** None
       
       * **Data Params:** New job to be inserted. 
        
        * **Success Response:**
            * Code: 201
            * Content: Returns the inserted job.
        * **Error Response:** 
            * Code: 500
            * Content: "Transaction Error"
            
* **/jobs/{id}**
    * **Action**: `GET`
        *  **URL Params:**

           *  **Required:** `id[integer]` 
    
           * **Optional:** None
            
            * **Success Response:**
                * Code: 200
                * Content: Returns the Job with id equals `id`.
            * **Error Response:** 
                * Code: 500
                * Content: `Internal Error retrieving Job`
    * **Action**: `PUT`
        *  **URL Params:**
    
           *  **Required:** `id[integer]` 
    
           * **Optional:** None
           
           * **Data Params:** Job to be updated. 
            
            * **Success Response:**
                * Code: 200
                * Content: Returns the updated job.
            * **Error Response:** 
                * Code: 500
                * Content: `"Error:  [Error Message]`
    * **Action**: `DELETE`
        *  **URL Params:**
    
           *  **Required:** `id[integer]` 
    
           * **Optional:** None
           
           * **Data Params:** None 
            
            * **Success Response:**
                * Code: 200
                * Content: None
            * **Error Response:** 
                * Code: 500
                * Content: `Error removing Job`

### Tasks

* **/tasks**
    * **Allowed Methods:** `GET` | `POST`
    * **Action**: `GET`
        * **URL Params:** None  
        *  **Query Params:**

           *  **Required:** None
    
           * **Optional:** `creationDate=[string]`
            
            * **Success Response:**
                * Code: 200
                * Content: If `creationDate` is defined it returns list of Tasks filtered by this date, otherwise it returns the un-ordered list with all values.
            * **Error Response:** 
                * Code: 500
                * Content: `"Server Error: " + Error Message`
            
        * **Example:** `/tasks?creationDate='2015-05-23'`
    * **Action**: `POST`
        *  **URL Params:**
    
           *  **Required:** None
    
           * **Optional:** None
           
           * **Data Params:** New Task to be inserted. 
            
            * **Success Response:**
                * Code: 201
                * Content: Returns the inserted Task.
            * **Error Response:** 
                * Code: 500
                * Content: `"Server Error: [Error Message]"`            
* **/tasks/{id}**
    * **Allowed Methods:** `GET` | `DELETE` | `PUT` 
    * **Action**: `GET`
        *  **URL Params:**

           *  **Required:** `id[integer]` 
    
           * **Optional:** None
            
            * **Success Response:**
                * Code: 200
                * Content: Returns the Task with id equals `id`.
            * **Error Response:** 
                * Code: 500
                * Content: `Transaction Error`
    * **Action**: `PUT`
        *  **URL Params:**
    
           *  **Required:** None
    
           * **Optional:** None
           
           * **Data Params:** Task to be updated. 
            
            * **Success Response:**
                * Code: 200
                * Content: Returns the updated task.
            * **Error Response:** 
                * Code: 500
                * Content: `"Transaction Error" + Error Message`
    * **Action**: `DELETE`
        *  **URL Params:**
    
           *  **Required:** `id[integer]` 
    
           * **Optional:** None
           
           * **Data Params:** None 
            
            * **Success Response:**
                * Code: 200
                * Content: None
            * **Error Response:** 
                * Code: 500
                * Content: `Error removing task`

## Testing
---
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

## Author
---
* **Wilson Pena** - [GitHub](https://github.com/wfpena)

