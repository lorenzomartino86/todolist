FROM tomcat:8.0.52-jre8-alpine
COPY ["/ui/target/todolist.war","/usr/local/tomcat/webapps/"]
COPY ["tomcat-users.xml","/usr/local/tomcat/conf/tomcat-users.xml"]
CMD ["catalina.sh","run"]