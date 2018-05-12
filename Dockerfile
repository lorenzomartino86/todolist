FROM tomcat:8.0.52-jre8-alpine
COPY ["/ui/target/ui-1.0.war","/usr/local/tomcat/webapps/"]
COPY ["tomcat-users.xml","/usr/local/tomcat/conf/tomcat-users.xml"]
CMD ["catalina.sh","run"]