Technical-Test
In order to start the spring boot application please do either of the following:

Build the necessary dependancies for the project by first running "mvn clean install" at the root level of the project. Once that has completed please import the spring boot application into an IDE of your choice (ensure that the IDE supports spring boot) and then simply run the application from the class "TechincaltestApplication".
OR

Run the batch file "START_APPLCATION.bat" which lives at the root level of the project to build the project, download the necessary dependancies needed and to start the server in one go.
Inside the src/main/resources/ folder you will find a file called "Uleska.postman_collection.json"

Take this file and import it into postman in order to load in the necessary web service calls by clicking the import button in the top left hand corner.

You should now see all the web web service calls imported.

NOTE: All web web service calls will fail unless basic auth is applied to the request using either of the following credentials: 
Username: user
Password: password

Username: admin
Password: password