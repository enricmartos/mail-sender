# mail-sender-webapp

## Description

This REST API allows you to send emails by using the _javax.mail_ built-in library.

## Core technologies

*Back-end*
- Spring Boot

*Unit Testing*
- JUnit
- Mockito

*Dependency management tool*
- Gradle

*Containerization*
- Docker-compose

*Documentation*
- Swagger

## Build setup

- Clone this repo to your local machine. This application can be executed either with docker or with maven wrapper:

### With Docker

```
$ docker-compose up
```

## Validate test scenarios

#### Validate email sending
- Set your mail credentials (mail.smtp.username and mail.smtp.password) in mailsender.properties.
- Execute the request below and verify that the email is actually sent.

POST http://localhost:8080/mail-sender/api/v1/email

**Request Params**
```json
to={TO_VALUE}
subject={SUBJECT_VALUE}
body={BODY_VALUE}
```

**Expected response code** 
```json
200
```


