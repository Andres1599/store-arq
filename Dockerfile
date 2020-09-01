FROM openjdk:8
EXPOSE 9001
ADD target/stores.war stores.war
ENTRYPOINT ["java","-jar","/stores.war"]
