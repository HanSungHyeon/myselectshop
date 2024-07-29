FROM openjdk:17
WORKDIR /app
COPY ./build/libs/_assignment_flow_assignment_main-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
