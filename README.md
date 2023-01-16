## Nasa Api Project

This is a simple demo application that collects photos from the [public NASA API](https://api.nasa.gov/) with two server endpoints.

Mars Rover Photos

**How to use**

**Prerequisites**

- Maven
- Java 11
- Docker

**Running the JAR directly**
- Build and run with Maven from the root directory of the project: 
>
> ./mvnw clean package && java -jar target/MarsRoverApi-0.0.1-SNAPSHOT.jar
>
### OR

**Running as a Docker Container**
- Build local docker image
>
> docker build -t marsrover .
>
- Run the image
>
> docker run -p 8080:8080 --name marsrover marsrover 
>

Type **http://localhost:8080** in your browser.

**Choose the Rover and camera and sol days and finally submit**

**It will return photos from Rovers**
