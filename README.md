# weather-app-gui
<img src="https://drive.google.com/uc?id=1XR8vf3PdxoMupMQcZbao7SfEljcBmaws" alt="ScreenShot" width="600" />

## Table of Contents
- [Features](#features)
- [Dependencies](#dependencies)
- [Run Locally](#run-locally)
- [Contact](#contact)
- [Project Status](#project-status)
## Features
* real-time weather data
* displayed info (metric):
  * temperature
  * windspeed
  * humidity
* visual weather representation(icons)
* dynamic date and time
* simplicity and compact design
## Dependencies
* [JavaFX](https://openjfx.io/openjfx-docs/) : It appears that your project uses JavaFX for the graphical user interface (GUI). 
  Make sure you have the necessary JavaFX libraries or dependencies configured to run the application.
* [JSON.simple](https://code.google.com/archive/p/json-simple/) : Your code imports org.json.simple.JSONObject and uses it to parse JSON data.
  You need to include the JSON.simple library in your project to handle JSON data.
* [Weather Forecast API](https://open-meteo.com/en/docs#latitude=33.767&longitude=-118.1892) : Specify the API used to fetch latitude and longitude data.
* [Geolocation API](https://open-meteo.com/en/docs/geocoding-api) : Specify the weather API used for retrieving weather data.
  Provide any necessary links or references to these APIs.
## Run Locally
* Clone the project
```bash
  git clone https://github.com/raimberdiyevb/weather-app-gui.git
```
* Navigate to Project Directory
```bash
  cd path_to_your_project_directory
```
* Build Maven Project:
  
for windows
```bash
  mvnw clean install
```
for linux/macOS
```bash
  mvn clean install
```
* Execute the Application
  ```bash
  mvn exec:java -Dexec.mainClass="com.raim.weatherapp.WeatherApplication"
  ```
* Ensure that Maven is installed on your system and configured correctly.
     Additionally, make sure the necessary dependencies, including JavaFX, are correctly specified
     in your Maven project's pom.xml file for the project to build and execute successfully.
     Adjust the commands and parameters based on your project's structure and requirements.
## Contact
 Feel free to reach out to me!
 Email : bekbolsun.raim@gmail.com
 WhatsApp : +48780765435
 LinkedIn : www.linkedin.com/in/raimberdiyev
## Project Status
it is a **Kickoff Version**
This project is a labor of love, and I am committed to its continuous development and improvement.
Future updates and enhancements are in the pipeline. I'm committed to expanding the project's features and functionalities.
I value feedback and input. I'm open to suggestions and eager to engage with the community for the project's growth.
