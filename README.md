Spring Social With Google OAUTH2
============================
Example Oauth2 + Google drive integration using Spring Social Google. 



Set up a project at https://console.developers.google.com/ and add the 
OAuth2 client ID and secret to the "google.clientId" and "google.clientSecret"
environment properties when running the app.    

Make sure you replace the following line in DriveController.java  before running
Resource resource = new FileSystemResource("/resources/images/test.png");  // Line no: 59 : replace the file path with a valid file.


To build app: 
mvn clean install

To run with Maven:
mvn tomcat7:run -Dgoogle.clientId=CLIENT_ID -Dgoogle.clientSecret=CLIENT_SECRET

The application will be available at http://localhost:8080/