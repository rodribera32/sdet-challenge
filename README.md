# SDET - Challenge

## Introduction
This Test Automation challenge was created using Java + Selenium Web Driver + TestNG. It was used to automate a specific scenario on www.aliexpress.com. Please check the Objective section.

## Objective
As a Customer we want to see if the second ad from the second results page when searching for "Iphone" on www.aliexpress.com has at least 1 item to be bought.

## Technologies
* Language used: JAVA
* Framework: Selenium webDriver + TESTNG
* Report Framework: Allure

## Requirements
* JavaJDK 1.8 or greater
* Apache Maven 3.3 or greater

## Execution
 * Clone the repo
 * Open command prompt and go to the repo folder.
 * Install the dependencies and execute the tests
```
$ cd sdet-challenge
$ mvn clean install
``` 
## Report
This project is using allure to generate the report.

### Allure - setup
Install allure command line in order to be able to generate a new report

* OSx 
```
brew install allure
```

* Windows
```
scoop install allure
```

### Allure - Commands

* Open the last execution saved
```
allure open
```
* Generate a new report with the last execution
```
allure generate --clean
```

## Report
Gif file attached with the execution of the tests and the report generated (Gif file on Video folder)

![til](https://github.com/rodribera32/sdet-challenge/blob/dev/video/challenge.gif)
