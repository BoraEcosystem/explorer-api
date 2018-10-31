# BORA Explorer
The BORA Explorer is a tool which helps you to collect and monitor information on transaction details through the Smart Contract for the BORA Shell.


### Components of BORA Explorer
The BORA Explorer consists of the following modules.
1. Front-End module providing UI, [explorer-front](https://github.com/BoraEcosystem/explorer-front) 
2. The Back-End module providing API for the Front-End, [explorer-api](https://github.com/BoraEcosystem/explorer-api)
3. The Crawler to fetch data from the Blockchain and configure index database, [explorer-crawler](https://github.com/BoraEcosystem/explorer-crawler)

This project is a Back-End module which is reponsible for the APIs. If you want to configure this API module, first install the Crawler to configure the index database. If you want to know about Front-end and Crawler, please see the link above.
Please note, this API component does not include APIs for the dashboard. You can decorate the dashboard as your needs using index database and real-time data from blockchain. 

### Prerequisite
#### Required development environmen
##### Java 1.8
Java 1.8 BORA Explorer is a tool written in Java. Therefore, you need to install the JDK on the machine you want to build. This project has been tested in the Java 1.8 environment.
See this link for install JDK
- OpenJDK: https://openjdk.java.net/install/
- OracleJDK: https://www.oracle.com/technetwork/java/javase/downloads/index.html

##### MySQL6
To construct a list for Block and Transaction, you need to build an index database. 

To understand MySQL and how to install, see [MySQL Official Site](https://www.mysql.com/products/community/).

You can check out the scripts regards the schema used in this application at [here](https://github.com/BoraEcosystem/explorer-crawler/blob/master/src/main/resources/schema.sql).

##### Other databases
This project is available in other databases. For example, if you want to use H2 for a simple test, add H2 database dependancy in build.gradle and add H2 database related settings in application.yml.

##### Gradle
A tool for building this project, requires gradle 4.7 or later.
See this link for install: https://gradle.org/install/

#### Understanding Spring Framework, Ethereum and Web3J
The BORA Explorer is an application using the Java based Spring Boot Framework. In this regard, you need a basic knowledge of Java and Spring Boot. The BORA Explorer runs on an Ethereum-based node and its related API leverages Web3J. If you want to understand the code, you need to have prior knowledge of Ethereum and Web3J.
- [Ethereum Official Site](https://ethereum.org/)
- [Web3J Official Site](https://web3j.readthedocs.io/en/latest/)
 
### Build
#### Configuration
Configure the application.yml to suit your environment. The following sections should be adapted depending on your environment. The BORA platform provides a separate side chain environment for each application. Therefore, if you have several applications you want to view through the BORA Explorer, you can add the configurations in an array.
If you would like to know the details of BORA platform, please check out [BORA Lagoon](https://developer.bora-lagoon.com/).

```
# You can change your default server port
# Check out default example at application.yml
server:
  port: 8080

spring:
  profiles:
    active: local

application:
  channels:
  # "app-id" should be unique for each application. Also, this value need to be consistent with "app-id" value in Crawler and API module.
  - app-id: 10 
    app-name: 'Your Application Name'
    node-url: http://your-node-url
    display-on-explorer: true
    # Set the contract address which is assigned your application.
    contract-address: '0x....'
    # Set configuration for your database 
    datasource:
      driver-class-name: com.mysql.jdbc.Driver
      jdbc-url: jdbc:mysql://127.0.0.1:3306/yourDatabaseName1
      username: username
      password: secret
      initialization-mode: never
      pool-name: point
    # For H2
    # datasource:
    #  driver-class-name: org.h2.Driver
    #  url: jdbc:h2:file:/PathForDatabase/yourDatabaseName;AUTO_SERVER=true
    #  username: sa
    #  password: 
    #  initialization-mode: never
    #  pool-name: point
    
# You can add more configurations for another application using BORA Point as follows.
# 
#  - app-id: 20
#    app-name: 'Application #1'
#    display-on-explorer: true
#    node-url: http://your-node-url
#    contract-address: '0x....'
#    datasource:
#      driver-class-name: com.mysql.jdbc.Driver
#      jdbc-url: jdbc:mysql://127.0.0.1:3306/yourDatabaseName2
#      username: username
#      password: secret
#      initialization-mode: never
#      pool-name: point

```

#### Build 

```sh
./gradlew clean build -x test
```

### Run BORA Explorer
```sh
cd bora_explorer_home_directory

java \
  # -server.port=8080 \
  # -Dlogging.level.com.boraecosystem=debug \
  -jar ./build/libs/bora-explorer-api-0.0.1-SNAPSHOT.jar
```
