# algorithm-visualizer
<img src="https://drive.google.com/uc?id=1XR8vf3PdxoMupMQcZbao7SfEljcBmaws" alt="ScreenShot" width="600" />

Algorithm Visualizer is a JavaFX application that provides an interactive 
and visual representation of various sorting algorithms.
This project aims to help users understand the mechanics 
and performance of different sorting algorithms by visualizing
their step-by-step execution on a given input array.

## Table of Contents
- [Features](#features)
- [Dependencies](#dependencies)
- [Run Locally](#run-locally)
- [Contact](#contact)
- [Project Status](#project-status)
## Features
* Sorting Algorithms
  * Bubble Sort: Visualize the step-by-step execution of the classic Bubble Sort algorithm.
  * Selection Sort: Understand the Selection Sort algorithm through an interactive visual representation.
  * Quick Sort: Explore the Quick Sort algorithm and its efficiency in sorting arrays.
  * Merge Sort: Visualize the Divide and Conquer strategy employed by the Merge Sort algorithm.
* User Interaction
  * Input Array Configuration: Input custom arrays or use default examples for sorting demonstrations.
  * Order Selection: Choose between ascending and descending order for sorting demonstrations.
  * Step Navigation: Move through the sorting process step by step with "Next" and "Previous" buttons.
* Real-time Visualization
  * Dynamic Canvas: See the array elements move in real-time as the sorting algorithms rearrange them.
  * Color-coded Elements: Each array element is color-coded to provide a clear visual indication of its movement.
* Playback Controls
  * Play/Pause Button: Play or pause the automatic playback of the sorting process.
  * Speed Adjustment: Control the speed of the sorting animation for better comprehension.
* Responsive Design
  * Resizable Window: Adjust the application window size to suit your preference.
  * Cross-Platform Compatibility: Run the visualizer on various platforms supporting JavaFX.
* Code Reusability
  * Modular Code Structure: Use well-organized and modular Java code for easy understanding and potential extension.
  * Object-Oriented Design: Leverage object-oriented principles for maintainability and scalability.
## Dependencies
* [JavaFX](https://openjfx.io/openjfx-docs/) : It appears that your project uses JavaFX for the graphical user interface (GUI). 
  Make sure you have the necessary JavaFX libraries or dependencies configured to run the application.
## Run Locally
* Clone the project
```bash
  git clone https://github.com/raimberdiyevb/algorithm-visualizer.git
```
* Navigate to Project Directory
```bash
  cd path_to_this_project
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
  mvn exec:java -Dexec.mainClass="com.raim.algorithmvisualizer.AlgorithmVisualizerApplication"
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
