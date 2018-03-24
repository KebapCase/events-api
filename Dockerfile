FROM maven:alpine

WORKDIR /event-management-api/

ADD docker/settings.xml /root/.m2/settings.xml
ADD pom.xml pom.xml
ADD src src

EXPOSE 8080

ENTRYPOINT mvn clean compile swagger:generate spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
