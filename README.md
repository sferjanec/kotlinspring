# kotlinspring

This project was generated using Spring Boot 2.4.0.  This project demonstrates the efficiencies of building REST services using Spring Boot with Kotlin 
and Flyway for database migrations

## Development server

Run `gradle bootRun` to build and run the Tomcat development server

## MySQL configuration
Update kotlinsping/src/main/resources/application.yml with your datasource url and credentials.

Remember to start your MySQL container or docker container with MySQL image prior to starting the Tomcat server.

## Additional Docker settings for containerized MySQL

Starting docker with mysql image
docker run --rm --name test-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=<your_password> -e MYSQL_DATABASE=kotlin_crud_db -d mysql:latest

Executing queries from inside container
docker exec -it test-mysql mysql -u root -p
mysql> use ktolin_crud_db;
mysql> show tables;
mysql> select * from tasks;
