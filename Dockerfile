FROM openjdk:8u151-jre-slim-stretch

COPY build/libs/app.jar app.jar

EXPOSE 8080
CMD ["java","-jar","app.jar"]
