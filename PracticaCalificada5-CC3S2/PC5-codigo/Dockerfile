FROM openjdk:17
WORKDIR /app
COPY src/main/java/ /app
RUN javac org/example/*.java
CMD ["java", "org.example.TowerDefenseGame"]