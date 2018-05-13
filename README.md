[![CircleCI](https://circleci.com/gh/lorenzomartino86/todolist.svg?style=svg)](https://circleci.com/gh/lorenzomartino86/todolist)

## TODO List
A simple online TODO list with a web interface that can be used in all popular web browsers.

#### Use Cases
- User can sign in using unique login and password securely (this can be hard coded
  to a default user list, at list one user e.g. with username: test, password: pwd123)
- User can view her/his task list
- User can add/remove task
- All changes can be persistent to allow view them in next sign in by the same user
- Each task should display the date of last updates and description
- User can check/uncheck any task on their list
- Consider performance

#### Project Architecture
This project separates APIs from UIs component through the usage of following modules:
- **api:** Maven module used to collect Rest API, business logic and persistence layer. This module is generating a Jar library.
- **ui:** Maven module used to handle ui components (html, javascript and css). This module is generating a WAR and depends on api.

This approach get the benefit that components are decoupled and can be built and deployed separately.


#### Main Technologies Used

For the API components the main frameworks/tools used are:
- *OpenJDK 1.8* 
- *Spring Core, Beans and Context:* Used for facilitate dependency injection
- *Spring MVC:* Used to facilitate the creation of rest endpoints
- *Spring Data and Hibernate:* Used to facilitate database mapping
- *Lombok:* Used to avoid common java boilerplate code
- *Jackson:* Used for serialization/deserialization
- *JUnit:* Used for test automation
- *Mockito:* Used for stub during test automation
- *Spring Test:* Used for facilitate testing of Spring Components

Basically the build process run unit and integration tests separately and generates a Jar file.

For the UI components the main frameworks/tools used are:
- *HTML5*
- *CSS3*
- *Javascript*
- *jQuery*
 

#### Instructions for build
In order to build the final WAR file and  we need to perform following commands:

1. Build API module
```
   mvn clean install -f api/pom.xml
```

2. Build UI module
```
   mvn clean install -f ui/pom.xml
```

Now we can deploy generated WAR to a Tomcat container. The generated file is under *ui* module
```
  ui/target/todolist.war
```


#### Build and deploy with docker
Alternatively we can speed up deployment using docker. After the step 2 of the build instructions we can build a docker image:

```
    docker build -t local-tomcat .
```

And then run docker container forwarding port 8080 from tomcat service:
```
    docker run -it -p 8080:8080 local-tomcat
```

#### Web App Usage
In order to do a manual test of APIs we can import Postman collection stored under folder *postman/todolist.postman_collection.json*.

The web app can be reached at the address http://localhost:8080/todolist. We can Login 

  LOGIN IMAGE
  
If not registered we can do it in this page:

  REGISTER IMAGE
  
After registration/login we can start our task list management:

 TASK LIST INIT 
 
After inserting and checking a task we can see the following result:

 TASK LIST MANAGEMENT


#### Improvements

There are infinite improvements in this project. 

First of all we need to refactor the behavior on the Javascript files. 
In order to do that we can start writing tests to secure our next refactoring operations.

