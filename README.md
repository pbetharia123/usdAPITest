# usdAPITest
# Rest Assured BDD Test Framework

This project contains a BDD-style test framework for API testing using Rest Assured with Java. The framework allows writing test scenarios in a behavior-driven style and ensures the quality and reliability of API endpoints.

## Project Structure

The project structure is organized as follows:
project-root/
│
├── src/
│ ├── main/
│ │ └── java/
│ │
│ └── test/
│ ├── java/
│ │ ├── com.rest.test/
│ │ │ └── getresponse.java
| | | └── expectedSchema.txt
│ │ │
│── test-output
│ │── index.html 
│── testng.xml
├── pom.xml
└── README.md

## Dependencies

The project uses the following dependencies:
- Rest Assured for API testing
- TestNG for test execution
- json schema validator to validate the schema
- Other dependencies specified in the `pom.xml` file

## Classes
getResponse - contains methods to validate status code, fetch value of AED and validate json schema of API response

# Methods
- checkStatusCode - it checks the status code of the get API response.
- fetch_AED_price - It fetchs the AED key value from the body. It checks if the value is within the range of 3.6 to 3.7
- verify_currency_pair_count - It verify the count of currency pair for rates.
- validate_response_json_schema - It validates the json schema of the API response

## Execution
The methods are annotated by @Test which can be executed as TestNg tests. 

## Result
The result file can be viewed from test-output - index.html
