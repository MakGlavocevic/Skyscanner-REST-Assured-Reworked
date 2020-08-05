# Skyscanner-REST-Assured-Reworked
Testing API with REST Assured and TestNG

## Overview

This automation test was reworked since the original lacked a lot of stuff, the original was created as a part of a work task and at that time 
I was limited with knowledge about the REST Assured framework. It uses Skyscanner Flight Search API from rapidapi.com.

This test represents a smoke test and the main objective is to see if the API responds to our GET requests with the correct HTTP status code, we will also assert content type and some of the values from the response.

rapidapi.com link: https://rapidapi.com/skyscanner/api/skyscanner-flight-search/details

--------------------------------------

## Prerequisites
1.Java 1.8 (https://www.oracle.com/java/technologies/javase-downloads.html)

2.IntelliJ IDEA Community Edition (https://www.jetbrains.com/idea/)

3.REST Assured (will be installed via maven)

4.TestNG (will be installed via maven)

5. Hamcrest Matchers (will be installed via maven)

--------------------------------------

## How to run

1. Java 1.8

2. Install latest IntelliJ IDEA Community Edition

3. Clone my git repository (https://github.com/MakGlavocevic/Skyscanner-REST-Assured-Reworked.git)

4. Open IntelliJ IDEA Community Edition

5. Open the folder where my project is via IntelliJ

6. In the Project window on the sidebar go to pom.xml 

7. Inside pom.xml see if all of the dependecies are up to date and let IntelliJ download them if you dont have them alredy.(load meaven changes button) or install them via CLI with mvn clean install command

8. Go to src>test>java and click on TestRequest there you can right click and select Run to run the test.

--------------------------------------

