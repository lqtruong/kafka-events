FROM openjdk:11.0.7-jdk

# ARG ACTIVE_PROFILE
# ENV SPRING_PROFILE_ACTIVE=$ACTIVE_PROFILE

ADD target/*.jar /opt/app.jar
# ADD shahid-cms-core-api/rds-keystore.jks /opt/rds-keystore.jks
# RUN keytool -importkeystore -srckeystore /usr/local/openjdk-11/lib/security/cacerts -destkeystore /opt/rds-keystore.jks -srcstorepass changeit -deststorepass uf5ErNTb7pYRTQk

EXPOSE 8086

# ENTRYPOINT exec java -Xms512m -Xmx1024m -jar -Dspring.profiles.active=$SPRING_PROFILE_ACTIVE -DrdsKeystore=/opt/rds-keystore.jks /opt/app.jar
ENTRYPOINT exec java -Xms256m -Xmx512m -jar /opt/app.jar
