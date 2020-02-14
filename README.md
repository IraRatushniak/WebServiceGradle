# WebServiceGradle
### Getting Started
These instructions will help you to set up and run a project for local development.
### Prerequisites
What things you need to install
* Java 1.8.0_201
* Gradle 4.10.3
* Tomcat 9.0.30
* MySql 8.0
* Intellij IDEA

### Run SOAP Web Service
* gradle run

### Generate SOAP Web CLient
* wsimport -keep -p {package for client} http://localhost:8080/booksservice?wsdl
