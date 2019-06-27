# Install Notes

This document shows the detail instructions about how to run this bet service in local environment.

## Design

Design document is available [here](https://github.com/eziceice/bet/blob/master/Bet%20service%20design.docx). 


## Database install

**Note:** *MySQL is the mandatory database used by this service as it only has the MySQL dependency in pom.xml.*

 1. Run this [SQL](https://github.com/eziceice/bet/blob/master/src/main/resources/createSQL) in your MySQLWorkBench or CLI to create table. 

## Service install

***Minimum Java version 1.8 is required to run this project.*** 

 1. Checkout this project to your local IDE (**IntelliJ** preferred).
 2. You might have some lombok issue (**IDE related**), this can be fixed by **enable annotation setting** and **add lombok plugin**. Follow this [link](https://stackoverflow.com/questions/24006937/lombok-annotations-do-not-compile-under-intellij-idea) to resolve this issue. 
 3. Find application.properties in the project and change those three configs based on your local environment. 
**spring.datasource.url**=jdbc:mysql://localhost:3306/springdb?allowPublicKeyRetrieval=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC
**spring.datasource.username**=root
**spring.datasource.password**=12345

Things you might need to change:

		 Schema name - springdb
		 Port - 3306
		 username - root
		 password - 12345
 4. Find BetApplication.java and run it. 


## Service Trigger

 1. Install [Postman](https://www.getpostman.com)
 2. Select **POST** as the HTTP method and add `http://localhost:8080/api/bet` as the URL.
 3. Click **Body** tab and add this JSON in it.
`{
	"betDate" : "2019-07-01 14:56:00",
	"betType": "win",
	"propNumber" : "104567",
	"customerId": "1087",
	"investmentAmount": "10000"
}`
4. Click **Authorization** tab and select type **Basic Auth**. 
5. Add **admin** for Username and **12345** for Password in **Basic Auth**.
6. Click **Send**. 
7. A response is received with a HTTP status 200.

## Report Generator

Currently a report geneartion schedular is exectued daily. A report is generated at 12:00 am every morning contains the bets records for previous day. Cron expression can be changed to test the report functionality. 

 1. Find **application.properties**.
 2. **Uncomment** this property.
  `#bet.report.execution.time=* * * * * ?`
 3. **Comment out** this property.
 `bet.report.execution.time=0 0 0 ? * *`
 4. Re-run this service, you should see a bet report in folder `bet/report/`

## Log

Logs are located in folder `bet/logs`

## Other issue
If you have any other issues, please contact me through liyutian6@gmail.com
