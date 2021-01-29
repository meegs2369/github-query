# GithubQuery
An application designed to query the events of a public github repository and return specific event details associated with that repository.
To run the application you will need [Java 11](https://adoptopenjdk.net) and [Maven](https://maven.apache.org/download.cgi) on the machine running it.  
To build the application, navigate to the directory containing the source code and run `mvn clean package` this will create a jar in the `/target` directory
within this directory. 
At this point there are two options to run the application :

* Run the command `mvn spring-boot:run`

* Navigate to the target directory and type the command `java -jar github-query.jar`.

Once this is done, you will see the application initialize. After the message `completed initialization in {value} ms`shows, the application 
UI is accessible at `localhost:8093/query`. You can also make an api call to `localhost:8093/api/{user}/{repository}/{type}` if you are so inclined.
Should you need it, a shutdown endpoint is available; POST to `localhost:8080/manage/shutdown` using an api tool like
[postman](https://www.postman.com) or via command line `curl "localhost:8080/manage/shutdown" -X POST`.

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=meegs2369_service-point&metric=alert_status)](https://sonarcloud.io/dashboard?id=meegs2369_github-query)
