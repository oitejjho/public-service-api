<h1 align="center"> Public Service API </h1> <br>

<p align="center">
  This service will communicate with internal microservices
</p>


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [API](#api)
- [Improvements](#improvements)




## Introduction

This service is responsible for the communication between UI and subscription-service.

## Features
* Microservice architecture
* BackEnd for the frontend design
* An aggregator service
* * Validate request from user
* * Forward request to subscription service 
* * Forward response to user from subscription service or different downstreams




## Requirements
The application can be run locally. Containerization is in progress

### Local
* [Java 16 SDK](https://www.oracle.com/java/technologies/downloads/#java16)
* [Maven](https://downloads.apache.org/maven/maven-3/3.8.1/binaries/)


## Quick Start
Make sure your maven is pointing to JAVA_HOME and JAVA_HOME is set to Java16 JDK

The subscription service host  value in the __application.yml__ file is set to `http://localhost:9090`.

### Run Local
If your JAVA_HOME is set to Java16 JDK
```bash
$ mvn clean install
$ java -jar target/public-service-api-0.0.1-SNAPSHOT.jar
```

For multiple JDK issue
```bash
$ JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-16.0.2.jdk/Contents/Home
$ export JAVA_HOME
$ mvn clean install
$ java -jar target/public-service-api-0.0.1-SNAPSHOT.jar
```

Application will run by default on port `8080`

Configure the port by changing `server.port` in __application.yml__

## API
After running the application you'll get the documentation in swagger-ui and open-api docs

* [Swagger-UI](http://localhost:8080/swagger-ui/index.html)
* [Open-Api docs](http://localhost:8080/v3/api-docs/)


## Improvements
* Reactive way of coding
* Containerization
* Integration with CICD i.e. jenkins / rancher
* Metrics Expose
* Integration with jaeger / slueth / opentelemetry for better traceability
* Integration with metrics collector i.e. prometheus
* Integration with ELK stack
* Integration with grafana for better visibility, observability and alerts
* 100% Unit test code coverage
* Integration test
* Automation testing or behavioral testing i.e. RobotFramework, Selenium 
