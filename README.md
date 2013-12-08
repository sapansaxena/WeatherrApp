Spring based Weather Widget

Demonstrates a simple Weather Widget built upon Spring MVC, which takes Zipcode as an input and produces City Name, State Name and Temperature in Fahrenheight.

This also showcases a suite of 7 unit tests where various components of the entire widget are tested.
In this showcase you'll see the following in action:

* The simplest possible @Controller
* Mapping Requests
* Obtaining Request Data
* Generating Responses
* Rendering Views
* Validation
* Rest Template Calls


To get the code:

Clone the repository:

$ git clone git:// github.com/sapansaxena/WeatherrApp.git

To run the application:

* Install and setup Maven
* Start a tomcat Server
* From the command line with Maven:


 cd WeatherrApp
 mvn tomcat7:deploy

or

In your preferred IDE such as SpringSource Tool Suite (STS):

* Import WeatherApp as a Maven Project
* Add the project into a server to run, such as Tomcat. Run the server

Access the deployed web application at: http://localhost:8080/WeatherApp/welcome
Other info:

Framework version illustrated: Spring MVC 3.1

