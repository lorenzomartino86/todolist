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
The architecture of this project 

- **api:** Maven module used to collect Rest API, business logic and persistence layer. This module 


#### Technologies


#### Instructions for build


#### Build and deploy with docker
docker build -t local-tomcat .

docker run -it -p 8080:8080 local-tomcat

All in one: 

mvn clean install && docker build -t local-tomcat . && docker run -it -p 8080:8080 local-tomcat


#### Improvements


#### References
