# Data Analysis System

## Abstract about system:

This project's a data analysis that has a purpose of monitoring a directory and when there' s a file update an action
will be triggered to generate a report again according to the processed information.

In Every valid file inside the `%HOMEPATH%/data/in` has a three types of data:

* Salesman:
  * 001çCPFçNameçSalary

* Customer:
    * 002çCNPJçNameçBusinessArea
    
* Sale:
    * 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesmaName
    
After the process will be generate a report with the actual data processed, this report will have:

* Amount of clients in the input files processed;
* Amount of salesman in the input files processed;
* ID of the most expensive sale;
* Name of the worst salesman ever

## Changing the parameters of application:

When a new change about the parameters of splitters, indexes, id of row type or directories changes you can change this 
parameters inside the `application.yml`.

## Requirements:

For run this application you must have java 8 and mongodb running as service, below has a links how install this 
requirements:

[How install java 8](https://docs.datastax.com/en/jdk-install/doc/jdk-install/installOpenJdkDeb.html)

[How install mongodb and running as a service](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)

## Running the application:

Run this application is very easy:

In the root of the project run these commands:

First generate your jar file with gradle: `./gradlew clean build`
And then run your jar: `java -jar build/libs/dataanalysissystem-0.0.1-SNAPSHOT.jar`

After this you can monitor you report file generated and put new files to be processed.

## Solution:

Understand this solution is very simple, has two scenarios in the fist time as you will run, in the first scenario hasn't
a folders in the homepath, so the application will create and will write a report with information that doesn't have any 
processed file, in the second scenario you have the folders created and input files inside the folder and the report will
be generated with this information.

Imagining the second scenario the lines of the files will be processed, and these information will be saved in mongodb, and
the name of the file also be saved in mongodb for don't read and save the same information.

