# DB Integration Automation Challenge

## Overview

This project demonstrates automated database testing using:

- Java 17
- Maven
- JUnit 5
- H2 (file-based embedded database)

It validates full CRUD operations, constraints, and negative scenarios against a persistent database.

---

## Database

The project uses an embedded **H2 file-based database**:
jdbc:h2:./dbintegration

Schema includes:

- Customers
- Employees
- Products
- Orders

---

## How to Run

### Option 1 – One Command (Recommended)
./run-tests.sh

This will:
1. Remove the Database if exist
2. Initialize the database
3. Execute all tests
4. Generate the HTML report
5. Open the report automatically

---


### Option 2 – Manual Execution

Initialize database:
mvn compile exec:java -Dexec.mainClass="db.DatabaseInitializer"

Run tests:
mvn clean test

Generate report:
mvn surefire-report:report-only

Report location:
target/site/surefire-report.html


## Reporting

The project generates:

- Console output
- JUnit XML reports
- HTML report (Surefire)

Compatible with CI tools such as Jenkins.

---

## Author

Jefferson Corbera
