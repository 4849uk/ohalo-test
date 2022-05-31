# Ohalo test Dictionary application

I've opted to use Spring Boot and Java 8 for my solution.  It's the platform I'm most familiar with right now and I didn't want to get distracted.

Additional libraries used:
- Spring Data JPA
- H2 for in-memory persistance
- Springdoc open API ui for endpoint documentation - added for demonstration and testing convenience.  I wouldn't expect this to be exposed directly in a production environment

As there was no mention of securing the endpoints, I've opted to keep them open to avoid unnecessary complexity.

## How to run:
Run the project as follows:
- Navigate to project root
- perform `mvn clean package` - this will run automated tests in your environment and create an executable application packeag jar file
- perform `java -jar target/dictionary-0.0.1-SNAPSHOT.jar` - this will run the applciation locally
- Go to your web browser the naviage to [http://localhost:8000/swagger-ui.html](http://localhost:8000/swagger-ui.html)

## Suggested enhancements
Some suggestions for enhancing this solution.  Things I'd like to see there but in the spirit of MVP (and time) I've not done:
- Secure the end points
- Add validation error handling - there is none right now, the app just returns 400 BAD REQUEST without a message, not particularly helpful
- Add external persistence; the in-memory H2 database used is for convenience only as this is a test project
- Consider using Lombok to reduce boilerplate code ([Project Lombok](https://projectlombok.org/)) - it works well and cleans up POJO code substantially
- Consider using something like Mapstruct to automate mapping code generation ([Mapstruct](https://mapstruct.org/)) - again it aids in clean code and takes the drudgery out of mapping code 
- Formalise testing with it's own application yml to facilitate alternate testing properties
- Add some integration tests to the testing pack
- Externalise database schema using liquibase, force java implementation to validate schema on startup


## Resource references:
Some resources I used during the build.
- https://stackoverflow.com/questions/8938498/get-the-index-of-a-pattern-in-a-string-using-regex
- https://stackoverflow.com/questions/1751301/regex-match-entire-words-only
- https://regex101.com/
