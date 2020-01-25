# StudentRecordJB

How to start the StudentRecordJB application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/StudentRecordJB-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Exposed endpoints
---

    POST `/students/add`

    GET `/students/all`

    DELETE `/students/delete/{id}`

    PUT `/students/edit/{id}`

    GET `/students/getByID`


Data in JSON format
---

`{
    
    "id": 5,

    "name": "Aphiwe",

    "gpa": 22.0
}`
