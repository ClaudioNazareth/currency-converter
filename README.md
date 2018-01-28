# Currency Converter Services

Click in the badges below to see build and coverage information

[![Build Status](https://travis-ci.org/ClaudioNazareth/currency-converter.svg?branch=master)](https://travis-ci.org/ClaudioNazareth/currency-converter)
[![codecov](https://codecov.io/gh/ClaudioNazareth/currency-converter/branch/master/graph/badge.svg)](https://codecov.io/gh/ClaudioNazareth/currency-converter)


![javaversion](https://img.shields.io/badge/Java-8-yellowgreen.svg)
![springboot](https://img.shields.io/badge/spring%20boot-1.5.9.RELEASE-orange.svg)
![server](https://img.shields.io/badge/server-undertow-yellow.svg)
![swagger](https://img.shields.io/badge/swagger-2.7.0-green.svg)
![googleformater](https://img.shields.io/badge/google%20format-1.5-blue.svg)
![springsecurity](https://img.shields.io/badge/spring%20security-1.5.9-orange.svg)
![fixturefactory](https://img.shields.io/badge/fixture%20factory-3.1.10-yellowgreen.svg)


- _Technical information about the project is after the description_

Scenario for this application

Develop a protected currency converter application using a public currency converter API 
(https://openexchangerates.org/signup/free, https://currencylayer.com/documentation, etc... ). 
The application should provide a login/registration screen and a main screen to query historical 
or current exchange rates. 
After the successful login the application should show the last 10 queries and their results on 
the main screen as reminder. Some main currencies (EUR, USD, GBP, NZD, AUD, JPY, HUF, etc...) are enough. 

* Create a Java web app. Store your source code in your Github account and provide documentation on how we can build executable war/jar. 

* Make your app available via Internet (hint: Heroku, Digital Ocean, Google App Engine or CloudFoundry) and share with us the link. 

* You could use in-memory (h2, etc.), cloud database providers (http://mongolab.com, https://www.mongosoup.de/en/index.html)
 or some RDBMS provider with MySQL hosting for persistence.  

* A reliable, working code is a must! We should see a login screen, a registration screen and the main 
screen with logout feature. Any simple web page with a basic GUI will suffice, i.e. three fields with
 currencies and the date and a simple list of past entries. Show an application based on some Spring 
 stuff and JPA persistence to make us smile. 

* BONUS POINTS: 

  * Use Spring MVC for the registration and login forms. The registration should require a valid 
  email address (according to the RFC), a reasonable date of birth, as well as a postal address with
  street, zip code, city, and a country selectable from a list. Mandatory if you want to be 
  considered for the Shop dev. team.
  
  * Implement acceptance tests with a BDD framework like Cucumber or JBehave 
  
  * Provide an automated build and test run on a continuous integration server
   (it could be hosted on https://travis-ci.org/, https://shippable.com, https://circleci.com/ or 
   something similar, but you can also setup your own Jenkins instance) 
   
  * Implement monitoring and management interface (JMX, REST, etc..)  
  
  * Cache external request with configurable TTL
  
  * Any stuff that ensures the application can integrate very well with the external components, 
    checks and protects the quality, automates things could be a nice extra.  
 
  
If you have any doubts about the project, please feel free to contact me at chtnazareth@gmail.com


  
## Instructions
  
To compile and run this project you will need:

  * **Java 8** (JDK8)
  * **Maven 3.0.5** or grater
  
#### Why Maven and not Gradle ?  

I have some Gradle projects as you can see in my Github, but some services I've used in this project 
do not work very well yet with gradle. So for this test I chose maven.


#### How to start the application and port   

Application port : **8080**  
 
To start the application use the command bellow   

```bash
mvn spring-boot:run
```


#### Application endpoint rest API

###### I disabled the spring security basic authentication for this test.

The base path for the endpoins is: **/api/v1/currency-converter**
  - For this application we have:  **api/v1/currency-converter/currencies** and **api/v1/currency-converter/currencies-top-ten**
  - **/health** - Application's status
  - You can see more about the api at swagger(payload and return codes)
  
For MVC controllers the endpoints are:
  - **/login** ,  **/registration** and **/** to go to de the index.  
  
When the application start up go to http://localhost:8080/login to log in or click in the registration link.

You can also test te application at: https://currency-converter-nazareth.herokuapp.com/login  


#### Tests

To run all unit and integration tests use the command bellow   

```bash
mvn test
```  

You can also test the application online on swagger that was deployed on herokuapp : https://currency-converter-nazareth.herokuapp.com/swagger-ui.html
You need authenticate first

#### Data base

To save the user and roles i choose H@
- The application contains a built-in **h2** database that is initialized along with application 
  - When a user is registered the password is **encrypted** using spring security  
  - see application.yml for more details

To save the user last 10 queries and their results  a used mongo
- The application contains a built-in **MongoDB** database that is initialized along with application    
    - Port to access MongoDB: **12345**
    - For this application we have 1 collections : **currenciesHistory** 
    - see application.yml for more details
    

  
# Architecture, tools and frameworks used

### Clean Architecture and Clean Code

#### Clean Code

Clean Code is a development style that focuses on the **ease of writing, reading and maintaining code**.

**Robert C. Martin**, in his book, "**Clean Code: A Handbook of Agile Software Craftsmanship**," 
states that the reading to writing ratio of the code is 10: 1. Therefore, a well-written code that 
facilitates reading is not only desirable, **but necessary in the current scenario**.

For this project I've used some clean code principles like :

* **Names are very important** : 
  * **Be precise**: _we must pass the central idea of ​​our variable or method, without turning, being concise 
    and direct_.
  * **Do not be afraid of big names**: _a very descriptive name, even if it is large, will enable a 
    better understanding and subsequent maintenance of the code_.
    
* **Comments only the necessary**
  * _Comment what is needed and only what is necessary. Codes are constantly modified, while comments rarely. 
    Thus, it is common for a comment to cease to have meaning, or worse, to pass on a false meaning after some time_.    

#### Clean Architecture

For this application I choose to use **Clean Architecture**

The Clean Architecture leverages well-known and not so well-known concepts, rules, and patterns, 
explaining how to fit them together, to propose a standardised way of building applications.

The core objectives behind Clean Architecture are the same as for Ports & Adapters (Hexagonal)
 and Onion Architectures:

* Independence of tools;
* Independence of delivery mechanisms;
* Testability in isolation.

In the [post](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) about 
Clean Architecture was published, this was the diagram used to explain the global idea:

![cleanarchitecture](https://8thlight.com/blog/assets/posts/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

**_The best of clean architecture is its use an software design technique to understand and solve 
complexity is Domain Driven Design (DDD). Domain Driven Design advocates modeling based on the 
reality of business as relevant to our use cases._** 

## Formatter

The code was formatted using [Google Format](https://github.com/google/google-java-format)


## APIs - Swagger

To document the APIs I used Swagger.

Swagger is the world’s largest framework of API developer tools for the OpenAPI Specification(OAS),
enabling development across the entire API lifecycle, from design and documentation, 
to test and deployment.

Here you can read more about [Swagger](https://swagger.io/)

To **see and test** the APIs go to path **/swagger-ui.html** (ex: _http://localhost:8080/swagger-ui.html_)

## UNDERTOW
I chose Undertow as web ser for this test. Why undertow?

Undertow is a web server designed to be used for both blocking and non-blocking tasks. 
Some of its main features are:

  * High Performance
  * Embeddable
  * Servlet 3.1
  * Web Sockets
  * Reverse Proxy

See more at In the [undertow](http://undertow.io/)


## Fixture Factory

I chose Fixture Factory to create fixtures for integrated and unit tests.

Fixture Factory is a tool to help developers quickly build and organize fake objects for unit tests. 
The key idea is to create specification limits of the data (templates) instead of hardcoded data. 
Try using F-F, then you can focus on the behavior of your methods and we manage the data.

See more at [Fixture Factory](https://github.com/six2six/fixture-factory)


## MongoDB

I chose MongoDB as database for this test. Why MongoDB?

MongoDB is the database for today’s applications: innovative, fast time-to-market, globally scalable, 
reliable, and inexpensive to operate. With MongoDB, you can build applications that were never
possible with traditional relational databases.


See more at [MongoDB](https://www.mongodb.com/collateral/mongodb-architecture-guide)