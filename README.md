<center>
<h1> Hospital Management System </h1>
</center>
<center>
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
</center>
This is a Spring Boot project for managing appointments from patient and docter.

---
<br>

## Framework Used
* Spring Boot

---
<br>

## Language Used
* Java

---
<br>

## Data Model

* Docter model
```
private Integer doctorId;
private String doctorName;
private Specilization specilization;
```
* Patient model
```
private Long patientId;
private String patientFirstName;
private String patientLastName;
private String patientEmail;
private String patientPassword;
private String patientContact;

```
* Appointement model
```
private AppointmentKey id;
private Doctor doctor;
```

### Validation

To validate the input we get from client, we've used validation annotations that are used to enforce specific constraints on the values of the variables. These constraints ensure that the data input by the user is of the correct format and meets certain criteria.

* @Valid - In Spring Framework, the @Valid annotation is used in conjunction with @PostMapping and @PutMapping annotations, which are used to handle POST and PUT requests, respectively. When used with @PostMapping or @PutMapping, the @Valid annotation is typically applied to a method parameter annotated with @RequestBody.

* @NotEmpty: This annotation is used to validate that the annotated field is not null or empty. 

* @NotNull: This annotation is used to validate that the annotated field is not null.

* @Pattern: It is used to validate that the code field matches the regular expression pattern
---
<br>

## Data Flow

1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.

4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.


## DataBase Used
* SQL database
```
We have used Persistent database to implement CRUD Operations.
```
---
<br>

## Project Summary
The Docter App is a Spring Boot project written in Java that enables basic functionality for managing Appointments and placing online Appointments. This project is built using the Spring Boot framework. 
