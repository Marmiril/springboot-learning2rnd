# Spring Boot Learning - Exercises

## Current status

Current block: **Block 2 - Validation and HTTP responses**

Completed exercises: **01 to 10**

Current exercise: **Exercise 11 - Reusable validation with private methods**

---

## Exercise index

| Exercise | Topic | Method | URL | Status |
|---|---|---|---|---|
| 01 | Basic endpoint | GET | http://localhost:8080/exercise01/hello | Done |
| 02 | Path variable | GET | http://localhost:8080/exercise02/hello/{name} | Done |
| 03 | Request param | GET | http://localhost:8080/exercise03/hello?name=Angel | Done |
| 04 | JSON response | GET | http://localhost:8080/exercise04/student | Done |
| 05 | JSON + PathVariable | GET | http://localhost:8080/exercise05/student/{name} | Done |
| 06 | JSON list | GET | http://localhost:8080/exercise06/students | Done |
| 07 | Filter by query param | GET | http://localhost:8080/exercise07/students?role=developer | Done |
| 08 | POST + RequestBody | POST | http://localhost:8080/exercise08/students | Done |
| 09 | ResponseEntity | POST | http://localhost:8080/exercise09/students | Done |
| 10 | Basic validation with POST | POST | http://localhost:8080/exercise10/students | Done |
| 11 | Reusable validation with private methods | POST | http://localhost:8080/exercise11/students | Done |
---

## Exercise 01 - Basic endpoint

**Purpose:**  
...

**URL:**  
http://localhost:8080/exercise01/hello

**HTTP method:**  
GET

**Main concepts:**

- `@RestController`
- `@GetMapping`
- Basic endpoint

**File:**  
`src/main/java/.../HelloController.java`

**Status:**  
Done



## Exercise 02 - Path variable

**Purpose:**  
This exercise introduces the use of path variables to capture dynamic values directly from the URL.

**URL:**  
http://localhost:8080/exercise02/hello/{name}

**HTTP method:**  
GET

**Main concepts:**

- `@GetMapping`
- `@PathVariable`
- Dynamic URL values
- Basic REST parameter handling

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise02/PathVariableController.java`

**Status:**  
Done

---

---

## Exercise 03 - Request param

**Purpose:**  
This exercise introduces the use of query parameters to receive optional or specific values from the URL.

**URL:**  
http://localhost:8080/exercise03/hello?name=Angel

**HTTP method:**  
GET

**Main concepts:**

- `@GetMapping`
- `@RequestParam`
- Query parameters
- Basic parameter handling in HTTP requests

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise03/RequestParamController.java`

**Status:**  
Done

---

## Exercise 04 - JSON response

**Purpose:**  
This exercise introduces how Spring Boot automatically converts a Java object into a JSON response.

**URL:**  
http://localhost:8080/exercise04/student

**HTTP method:**  
GET

**Main concepts:**

- `@RestController`
- `@GetMapping`
- JSON response
- Java object serialization

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise04/JsonController.java`

**Status:**  
Done

---

## Exercise 05 - JSON with PathVariable

**Purpose:**  
This exercise combines a JSON response with a path variable to return student data based on a value received from the URL.

**URL:**  
http://localhost:8080/exercise05/students/{id}

**HTTP method:**  
GET

**Main concepts:**

- `@GetMapping`
- `@PathVariable`
- JSON response
- Dynamic resource lookup by ID

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise05/StudentByIdController.java`

**Status:**  
Done

---

## Exercise 06 - JSON list response

**Purpose:**  
This exercise introduces how Spring Boot can return a list of Java objects as a JSON array.

**URL:**  
http://localhost:8080/exercise06/students

**HTTP method:**  
GET

**Main concepts:**

- `@RestController`
- `@GetMapping`
- `List.of()`
- JSON array response
- Java record as response model

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise06/StudentListController.java`

**Status:**  
Done

---

## Exercise 07 - Optional query parameter filter

**Purpose:**  
This exercise introduces how to use an optional query parameter to filter a JSON list response.

**URLs:**  
http://localhost:8080/exercise07/students  
http://localhost:8080/exercise07/students?role=backend

**HTTP method:**  
GET

**Main concepts:**

- `@RestController`
- `@GetMapping`
- `@RequestParam(required = false)`
- Optional query parameters
- Filtering with Stream API
- `equalsIgnoreCase()`

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise07/StudentFilterController.java`

**Status:**  
Done



---

## Exercise 08 - Receive JSON with POST

**Purpose:**  
This exercise introduces how Spring Boot can receive JSON data in the request body and convert it into a Java object.

**URL:**  
http://localhost:8080/exercise08/students

**HTTP method:**  
POST

**Main concepts:**

- `@RestController`
- `@PostMapping`
- `@RequestBody`
- JSON request body
- Java record as request model
- Java record as response model

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise08/StudentPostController.java`

**Status:**  
Done

---

## Exercise 09 - ResponseEntity with POST

**Purpose:**  
This exercise introduces how to return an HTTP status code together with a JSON response using `ResponseEntity`.

**URL:**  
http://localhost:8080/exercise09/students

**HTTP method:**  
POST

**Main concepts:**

- `@RestController`
- `@PostMapping`
- `@RequestBody`
- `ResponseEntity`
- `HttpStatus.CREATED`
- HTTP 201 Created

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise09/StudentResponseEntityController.java`

**Status:**  
Done

---

## Exercise 10 - Basic validation with POST

**Purpose:**  
This exercise introduces basic manual validation before creating a response. If the received JSON contains invalid data, the controller returns an HTTP 400 Bad Request response.

**URL:**  
http://localhost:8080/exercise10/students

**HTTP method:**  
POST

**Main concepts:**

- `@RestController`
- `@PostMapping`
- `@RequestBody`
- `ResponseEntity`
- `HttpStatus.CREATED`
- `HttpStatus.BAD_REQUEST`
- Manual validation
- Error response model

**File:**  
`src/main/java/com/angel/springbootlearning/exercises/exercise10/StudentValidationController.java`

**Status:**  
Done

## Exercise 11 - Reusable validation with private methods

**Purpose:**
This exercise improves the previous manual validation by moving repeated validation logic into private helper methods.

**URL:**
http://localhost:8080/exercise11/students

**HTTP method:**
POST

**Main concepts:**

* Manual validation
* Private helper methods
* `ResponseEntity<?>`
* `HttpStatus.CREATED`
* `HttpStatus.BAD_REQUEST`
* Custom error response
* Cleaner controller logic
* Reading `400 Bad Request` responses in PowerShell

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise11/StudentReusableValidationController.java`

**PowerShell valid request:**

```powershell
$body = @{
    name = "Angel"
    role = "backend"
} | ConvertTo-Json

Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise11/students" `
  -Method Post `
  -ContentType "application/json" `
  -Body $body
```

**PowerShell invalid request:**

```powershell
$body = @{
    name = ""
    role = "backend"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise11/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
```

**Expected error response:**

```json
{
  "message": "The student name is required!"
}
```

**Status:**
Done

## Exercise 12 - Validation of several fields

**Purpose:**
This exercise extends manual validation by checking several fields before creating a response: name, role, age and email.

**URL:**
http://localhost:8080/exercise12/students

**HTTP method:**
POST

**Main concepts:**

* Manual validation
* Validation of several fields
* Private helper methods
* `ResponseEntity<?>`
* `HttpStatus.CREATED`
* `HttpStatus.BAD_REQUEST`
* Custom error response
* Basic email validation
* Numeric range validation
* Cleaner controller logic

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise12/StudentSeveralFieldsValidationController.java`

**Tested cases:**

* Valid request returns HTTP 201 Created.
* Empty name returns HTTP 400 Bad Request.
* Empty role returns HTTP 400 Bad Request.
* Invalid age returns HTTP 400 Bad Request.
* Invalid email returns HTTP 400 Bad Request.

**Status:**
Done

## Exercise 13 - Structured error responses

**Purpose:**
This exercise improves error handling by returning a more structured error response. Instead of returning only a message, the API returns the HTTP status code, the HTTP error name and a custom validation message.

**URL:**
http://localhost:8080/exercise13/students

**HTTP method:**
POST

**Main concepts:**

* Manual validation
* Structured error response
* `ResponseEntity<?>`
* `HttpStatus.BAD_REQUEST`
* `status.value()`
* `status.getReasonPhrase()`
* Custom `ErrorResponse`
* Basic regex validation for email

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise13/StudentStructuredErrorController.java`

**Status:**
Done

---

## Exercise 14 - HTTP 404 Not Found

**Purpose:**
This exercise introduces how to return an HTTP 404 Not Found response when a requested resource does not exist.

**URL:**
http://localhost:8080/exercise14/students/{id}

**HTTP method:**
GET

**Main concepts:**

* `@GetMapping`
* `@PathVariable`
* `ResponseEntity<?>`
* HTTP 200 OK
* HTTP 404 Not Found
* Simulated resource lookup
* Structured error response

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise14/StudentNotFoundController.java`

**Status:**
Done

---

## Exercise 15 - HTTP 204 No Content

**Purpose:**
This exercise introduces how to return an HTTP 204 No Content response when an operation is completed successfully but does not need to return a response body.

**URL:**
http://localhost:8080/exercise15/students/{id}

**HTTP method:**
DELETE

**Main concepts:**

* `@DeleteMapping`
* `@PathVariable`
* `ResponseEntity<?>`
* HTTP 204 No Content
* HTTP 404 Not Found
* Simulated delete operation
* Response without body

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise15/StudentNoContentController.java`

**Status:**
Done

---

## Exercise 16 - HTTP 409 Conflict

**Purpose:**
This exercise introduces how to return an HTTP 409 Conflict response when the request is valid but conflicts with existing data.

**URL:**
http://localhost:8080/exercise16/students

**HTTP method:**
POST

**Main concepts:**

* `@PostMapping`
* `@RequestBody`
* `ResponseEntity<?>`
* HTTP 201 Created
* HTTP 400 Bad Request
* HTTP 409 Conflict
* Duplicate value validation
* Simulated existing data

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise16/StudentConflictController.java`

**Status:**
Done

---

## Exercise 17 - HTTP 422 Unprocessable Content

**Purpose:**
This exercise introduces how to return an HTTP 422 response when the received JSON is valid but does not follow a business rule.

**URL:**
http://localhost:8080/exercise17/students

**HTTP method:**
POST

**Main concepts:**

* `@PostMapping`
* `@RequestBody`
* Manual validation
* Business rule validation
* `HttpStatus.UNPROCESSABLE_CONTENT`
* HTTP 422 Unprocessable Content
* Allowed values validation
* Structured error response

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise17/StudentUnprocessableEntityController.java`

**Status:**
Done

---

## Exercise 18 - Common HTTP status codes

**Purpose:**
This exercise consolidates several common HTTP status codes used in REST APIs in a single controller.

**URL:**
http://localhost:8080/exercise18/students

**HTTP methods:**
GET, POST, DELETE

**Main concepts:**

* `ResponseEntity`
* HTTP 200 OK
* HTTP 201 Created
* HTTP 204 No Content
* HTTP 400 Bad Request
* HTTP 404 Not Found
* HTTP 409 Conflict
* `@GetMapping`
* `@PostMapping`
* `@DeleteMapping`
* `@PathVariable`
* `@RequestBody`
* Structured error responses
* Simulated student data

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise18/StudentHttpStatusController.java`

**Tested cases:**

* GET all students returns HTTP 200 OK.
* GET existing student by id returns HTTP 200 OK.
* GET missing student by id returns HTTP 404 Not Found.
* POST valid student returns HTTP 201 Created.
* POST empty required field returns HTTP 400 Bad Request.
* POST duplicated student name returns HTTP 409 Conflict.
* DELETE existing student returns HTTP 204 No Content.
* DELETE missing student returns HTTP 404 Not Found.

**Status:**
Done

## Exercise 19 - Conditional validation

**Purpose:**
This exercise introduces conditional validation. Some fields are required only when another field has a specific value.

**URL:**
http://localhost:8080/exercise19/students

**HTTP method:**
POST

**Main concepts:**

* Manual validation
* Conditional validation
* Business rule validation
* `ResponseEntity<?>`
* `HttpStatus.BAD_REQUEST`
* `HttpStatus.UNPROCESSABLE_CONTENT`
* HTTP 422 Unprocessable Content
* Private helper methods
* Structured error response

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise19/StudentConditionalValidationController.java`

**Tested cases:**

* Backend student without portfolio URL returns HTTP 201 Created.
* Frontend student without portfolio URL returns HTTP 422 Unprocessable Content.
* Fullstack student without portfolio URL returns HTTP 422 Unprocessable Content.
* Frontend student with portfolio URL returns HTTP 201 Created.
* Empty name returns HTTP 400 Bad Request.
* Empty role returns HTTP 400 Bad Request.
* Invalid role returns HTTP 422 Unprocessable Content.

**Status:**
Done

---

## Exercise 20 - Refactor responses

**Purpose:**
This exercise refactors response creation by using private helper methods for both successful and error responses.

**URL:**
http://localhost:8080/exercise20/students

**HTTP method:**
POST

**Main concepts:**

* Response refactoring
* Private helper methods
* `ResponseEntity<?>`
* `ResponseEntity<StudentResponse>`
* `ResponseEntity<ErrorResponse>`
* `HttpStatus.CREATED`
* `HttpStatus.BAD_REQUEST`
* `HttpStatus.UNPROCESSABLE_CONTENT`
* Reusable success response
* Reusable error response
* Cleaner controller logic

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise20/StudentResponseRefactorController.java`

**Tested cases:**

* Valid request returns HTTP 201 Created.
* Empty name returns HTTP 400 Bad Request.
* Empty role returns HTTP 400 Bad Request.
* Invalid role returns HTTP 422 Unprocessable Content.

**Status:**
Done

## Exercise 21 - Mutable in-memory list

**Purpose:**
This exercise introduces an `ArrayList` as temporary mutable storage. The stored students remain available only while the application is running.

**URL:**
http://localhost:8080/exercise21/students

**HTTP method:**
GET

**Main concepts:**

* `ArrayList`
* `List`
* Mutable in-memory storage
* `@RequestMapping`
* `@GetMapping`
* JSON array response
* Temporary data storage

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise21/StudentListController.java`

**PowerShell request:**

```powershell
curl.exe -s "http://localhost:8080/exercise21/students"
```

**Expected response:**

```json
[
  "Ángel",
  "Kratos"
]
```

**Tested cases:**

* GET request returns HTTP 200 OK.
* The endpoint returns every student stored in the `ArrayList`.
* The stored data disappears when the application is restarted.

**Status:**
Done

---

## Exercise 22 - Create a student

**Purpose:**
This exercise receives a student as JSON through a POST request and stores the received object temporarily in an `ArrayList`.

**URL:**
http://localhost:8080/exercise22/students

**HTTP method:**
POST

**Main concepts:**

* `ArrayList`
* `@PostMapping`
* `@RequestBody`
* JSON request body
* Java record as data model
* `ResponseEntity`
* `HttpStatus.CREATED`
* HTTP 201 Created
* Temporary in-memory storage

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise22/StudentCreateController.java`

**PowerShell request:**

```powershell
$body = @{
    id   = 1
    name = "Angel"
    role = "Backend developer"
} | ConvertTo-Json

Invoke-RestMethod `
    -Uri "http://localhost:8080/exercise22/students" `
    -Method Post `
    -ContentType "application/json" `
    -Body $body
```

**Expected response:**

```text
id name  role
-- ----  ----
 1 Angel Backend developer
```

**Tested cases:**

* A valid JSON request returns HTTP 201 Created.
* The received student is added to the in-memory list.
* The created student is returned in the response body.

**Status:**
Done

---

## Exercise 23 - List stored students

**Purpose:**
This exercise stores students temporarily in an `ArrayList` and returns all stored students through a GET request.

**URL:**
http://localhost:8080/exercise23/students

**HTTP methods:**
GET, POST

**Main concepts:**

* `ArrayList`
* Mutable in-memory storage
* `@PostMapping`
* `@GetMapping`
* `@RequestBody`
* `ResponseEntity`
* HTTP 201 Created
* JSON list response
* Creating and listing resources
* Multiple PowerShell requests with `ForEach-Object`

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise23/Exercise23StudentListController.java`

**PowerShell create requests:**

```powershell
$students = @(
    @{
        id   = 1
        name = "Angel"
        role = "Backend developer"
    },
    @{
        id   = 2
        name = "Kratos"
        role = "God of War"
    }
)

$students | ForEach-Object {
    Invoke-RestMethod `
        -Uri "http://localhost:8080/exercise23/students" `
        -Method Post `
        -ContentType "application/json" `
        -Body ($_ | ConvertTo-Json)
}
```

**PowerShell list request:**

```powershell
curl.exe -s "http://localhost:8080/exercise23/students"
```

**Expected response:**

```json
[
  {
    "id": 1,
    "name": "Angel",
    "role": "Backend developer"
  },
  {
    "id": 2,
    "name": "Kratos",
    "role": "God of War"
  }
]
```

**Tested cases:**

* POST request stores a student and returns HTTP 201 Created.
* Several students can be created using several POST requests.
* GET request returns HTTP 200 OK.
* GET request returns every student currently stored in memory.
* A Spring bean name conflict was resolved by giving the controller a unique class name.

**Status:**
Done

---

## Exercise 24 - Find a student by id

**Purpose:**
This exercise searches for a specific student inside an in-memory `ArrayList` using the id received as a path variable.

**URL:**
http://localhost:8080/exercise24/students/{id}

**HTTP method:**
GET

**Main concepts:**

* `ArrayList`
* `@GetMapping`
* `@PathVariable`
* Dynamic resource lookup
* Iterating through a list
* Comparing resource identifiers
* `ResponseEntity`
* HTTP 200 OK
* HTTP 404 Not Found
* Response without body

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise24/StudentSearchController.java`

**PowerShell successful request:**

```powershell
Invoke-RestMethod `
    -Uri "http://localhost:8080/exercise24/students/2" `
    -Method Get
```

**Expected response:**

```text
id name   role
-- ----   ----
 2 Kratos God of War
```

**PowerShell missing student request:**

```powershell
try {
    Invoke-WebRequest `
        -Uri "http://localhost:8080/exercise24/students/99" `
        -Method Get `
        -UseBasicParsing
}
catch {
    $_.Exception.Response.StatusCode.value__
}
```

**Expected error status:**

```text
404
```

**Tested cases:**

* Existing student id returns HTTP 200 OK.
* Existing student id returns the matching student as JSON.
* Missing student id returns HTTP 404 Not Found.
* The search iterates through the in-memory list until a matching id is found.

**Status:**
Done

## Exercise 25 - Delete a student by id

**Purpose:**
This exercise introduces how to delete a student from an in-memory `ArrayList` using an id received through the URL.

**URL:**
http://localhost:8080/exercise25/students/{id}

**HTTP method:**
DELETE

**Main concepts:**

* `@DeleteMapping`
* `@PathVariable`
* `ArrayList`
* `removeIf()`
* `ResponseEntity<Void>`
* HTTP 204 No Content
* HTTP 404 Not Found
* Resource deletion
* In-memory data management

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise25/StudentDeleteController.java`

**PowerShell successful request:**

```powershell
$response = Invoke-WebRequest `
    -Uri "http://localhost:8080/exercise25/students/1" `
    -Method Delete `
    -UseBasicParsing

$response.StatusCode
```

**Expected status:**

```text
204
```

**PowerShell missing student request:**

```powershell
try {
    Invoke-WebRequest `
        -Uri "http://localhost:8080/exercise25/students/99" `
        -Method Delete `
        -UseBasicParsing
}
catch {
    $_.Exception.Response.StatusCode.value__
}
```

**Expected error status:**

```text
404
```

**Tested cases:**

* Existing student id returns HTTP 204 No Content.
* The matching student is removed from the in-memory list.
* Missing student id returns HTTP 404 Not Found.
* Repeating the deletion of the same student returns HTTP 404 Not Found.
* The deleted student is restored when the application is restarted because the sample data is created in the controller constructor.

**Status:**
Done

## Exercise 26 - Complete student update

**Purpose:**
This exercise introduces how to completely replace the data of an existing student using a PUT request and an id received through the URL.

**URL:**
http://localhost:8080/exercise26/students/{id}

**HTTP method:**
PUT

**Main concepts:**

* `@PutMapping`
* `@PathVariable`
* `@RequestBody`
* `ArrayList`
* Iterating through a list by index
* `List.get()`
* `List.set()`
* Complete resource replacement
* `ResponseEntity<Student>`
* HTTP 200 OK
* HTTP 404 Not Found
* HTTP 405 Method Not Allowed

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise26/StudentFullUpdateController.java`

**Interactive PowerShell request:**

```powershell
$id = Read-Host "Introduce el ID del estudiante"
$name = Read-Host "Introduce el nuevo nombre"
$role = Read-Host "Introduce el nuevo rol"

$body = @{
    id   = [int]$id
    name = $name
    role = $role
} | ConvertTo-Json

try {
    $student = Invoke-RestMethod `
        -Uri "http://localhost:8080/exercise26/students/$id" `
        -Method Put `
        -ContentType "application/json" `
        -Body $body

    Write-Host "Student updated successfully:"
    $student
}
catch {
    $statusCode = $_.Exception.Response.StatusCode.value__

    if ($statusCode -eq 404) {
        Write-Host "Student not found."
    }
    else {
        Write-Host "Request failed with status code $statusCode."
    }
}
```

**Expected successful response:**

```json
{
  "id": 1,
  "name": "Angel Plata",
  "role": "Java developer"
}
```

**Expected missing student response:**

```text
Student not found.
```

**Tested cases:**

* Existing student id returns HTTP 200 OK.
* All student fields are replaced with the received data.
* The id from the URL is preserved in the updated student.
* Missing student id returns HTTP 404 Not Found.
* Sending a POST request instead of PUT returns HTTP 405 Method Not Allowed.
* `List.set(index, updatedStudent)` replaces the student stored at a specific list position.

**Status:**
Done

---

## Exercise 27 - Partial student update

**Purpose:**
This exercise introduces how to update only the fields received in a PATCH request while preserving the existing values of the other student fields.

**URL:**
http://localhost:8080/exercise27/students/{id}

**HTTP method:**
PATCH

**Main concepts:**

* `@PatchMapping`
* `@PathVariable`
* `@RequestBody`
* Partial resource update
* Optional request fields
* `ArrayList`
* `List.get()`
* `List.set()`
* Ternary operator
* Null validation
* `isBlank()`
* HTTP 200 OK
* HTTP 404 Not Found
* REST Client `.http` files

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise27/StudentPartialUpdateController.java`

**Request file:**
`requests/exercise27.http`

**Update only the name:**

```http
### Update only the student's name
PATCH http://localhost:8080/exercise27/students/1
Content-Type: application/json

{
  "name": "Angel Plata"
}
```

**Update only the role:**

```http
### Update only the student's role
PATCH http://localhost:8080/exercise27/students/1
Content-Type: application/json

{
  "role": "Java backend developer"
}
```

**Update both fields:**

```http
### Update the student's name and role
PATCH http://localhost:8080/exercise27/students/2
Content-Type: application/json

{
  "name": "Kratos of Sparta",
  "role": "Backend destroyer"
}
```

**Send an empty body:**

```http
### Preserve every current value
PATCH http://localhost:8080/exercise27/students/1
Content-Type: application/json

{
}
```

**Try to update a missing student:**

```http
### Try to update a missing student
PATCH http://localhost:8080/exercise27/students/99
Content-Type: application/json

{
  "role": "Developer"
}
```

**Expected successful response:**

```json
{
  "id": 1,
  "name": "Angel",
  "role": "Java backend developer"
}
```

**Expected missing student status:**

```text
404 Not Found
```

**Java language tip:**

The ternary operator provides a compact alternative to an `if/else` assignment:

```java
String updatedRole =
    request.role() != null && !request.role().isBlank()
        ? request.role()
        : currentStudent.role();
```

It is equivalent to:

```java
String updatedRole;

if (request.role() != null && !request.role().isBlank()) {
    updatedRole = request.role();
} else {
    updatedRole = currentStudent.role();
}
```

**Tested cases:**

* Updating only the name preserves the current role.
* Updating only the role preserves the current name.
* Sending both fields updates both values.
* Sending an empty JSON object preserves every current value.
* Missing student id returns HTTP 404 Not Found.
* The role condition must use `!request.role().isBlank()` so that non-empty roles are accepted.
* The controller does not include a GET endpoint for listing all students.

**Status:**
Done

## Exercise 28 - Automatic student id

**Purpose:**
This exercise introduces automatic id generation when creating students. The client sends only the name and role, while the server assigns the next available id.

**URL:**
http://localhost:8080/exercise28/students

**HTTP methods:**
GET, POST

**Main concepts:**

* Automatic id generation
* Simulated autoincrement
* `ArrayList`
* `List.add()`
* `@PostMapping`
* `@GetMapping`
* `@RequestBody`
* `ResponseEntity<Student>`
* `HttpStatus.CREATED`
* Postincrement operator `nextId++`
* Separate request and response models

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise28/StudentAutoIdController.java`

**Request file:**
`requests/exercise28.http`

**Tested cases:**

* POST creates a student and returns HTTP 201 Created.
* The client does not send the student id.
* The first student receives id `1`.
* Each new student receives the next available id.
* The created student is stored in the `ArrayList`.
* GET returns all stored students.
* Restarting the application clears the list and resets the id counter.

**Status:**
Done

## Exercise 29 - Prevent duplicate students

**Purpose:**
This exercise prevents creating students with duplicate names by checking the existing in-memory list before saving.

**URL:**
http://localhost:8080/exercise29/students

**HTTP methods:**
GET, POST

**Main concepts:**

* Duplicate detection
* `Stream`
* `anyMatch()`
* `equalsIgnoreCase()`
* `@PostMapping`
* `@GetMapping`
* `ResponseEntity<?>`
* `HttpStatus.CREATED`
* `HttpStatus.CONFLICT`
* HTTP 409 Conflict
* Automatic id generation

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise29/StudentDuplicateController.java`

**Request file:**
`requests/exercise29.http`

**Tested cases:**

* A new student returns HTTP 201 Created.
* A duplicated name returns HTTP 409 Conflict.
* Duplicate detection ignores uppercase and lowercase differences.
* A different student name is created successfully.
* GET returns all stored students.
* A rejected duplicate is not added to the list.

**Status:**
Done

## Exercise 30 - Complete student CRUD

**Purpose:**
This exercise integrates all CRUD operations into a single in-memory student controller.

**URL:**
http://localhost:8080/exercise30/students

**HTTP methods:**
GET, POST, PUT, PATCH, DELETE

**Main concepts:**

* Complete CRUD
* Automatic id generation
* Duplicate prevention
* Manual validation
* `@GetMapping`
* `@PostMapping`
* `@PutMapping`
* `@PatchMapping`
* `@DeleteMapping`
* `List.get()`
* `List.add()`
* `List.set()`
* `List.remove()`
* Private helper methods
* HTTP 200, 201, 204, 400, 404 and 409

**File:**
`src/main/java/com/angel/springbootlearning/exercises/exercise30/StudentCrudController.java`

**Request file:**
`requests/exercise30.http`

**Tested cases:**

* POST creates students with automatic ids.
* Duplicate names return HTTP 409 Conflict.
* GET returns all students.
* GET by id returns one student or HTTP 404.
* PUT replaces all student data.
* PATCH updates only the received fields.
* DELETE removes a student and returns HTTP 204.
* Invalid POST or PUT data returns HTTP 400.
* Missing resources return HTTP 404.

**Status:**
Done


## Exercise 31 - Separate Controller and Service

**Purpose:**
Separate the HTTP layer from the application logic by introducing a service class.

**URL:**
http://localhost:8080/exercise31/students

**HTTP methods:**
GET

**Main concepts:**

* Separation of responsibilities.
* Controller and service layers.
* Delegation from controller to service.

**Files:**
`src/main/java/com/angel/springbootlearning/exercises/exercise31/StudentController.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise31/StudentService.java`

**Request file:**
`requests/exercise31.http`

**Tested cases:**

* Getting all students returns HTTP 200 OK.
* The response contains the complete student list.

**Status:**
Done

## Exercise 32 - Create StudentService

**Purpose:**
Centralize student listing and creation operations inside a service class.

**URL:**
http://localhost:8080/exercise32/students

**HTTP methods:**
GET, POST

**Main concepts:**

* Service layer.
* Separation of responsibilities.
* Delegation from controller to service.
* Automatic ID generation with `nextId++`.
* Defensive copy of a collection.

**Files:**
`src/main/java/com/angel/springbootlearning/exercises/exercise32/Student.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise32/StudentService2.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise32/StudentController2.java`

**Request file:**
`requests/exercise32.http`

**Tested cases:**

* Getting all students returns HTTP 200 OK.
* Creating a student returns HTTP 201 Created.
* The service assigns the next available ID.
* The created student appears in subsequent GET requests.

**Status:**
Done

## Exercise 33 - Constructor Injection

**Purpose:**
Use constructor injection to provide a Spring-managed service to a controller.

**URL:**
http://localhost:8080/exercise33/students

**HTTP methods:**
GET, POST

**Main concepts:**

* `@Service`.
* Spring-managed components.
* Constructor injection.
* Dependency inversion.
* Automatic ID generation.

**Files:**
`src/main/java/com/angel/springbootlearning/exercises/exercise33/Student.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise33/StudentRequest.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise33/StudentService3.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise33/StudentController3.java`

**Request file:**
`requests/exercise33.http`

**Tested cases:**

* Getting all students returns HTTP 200 OK.
* Creating a student returns HTTP 201 Created.
* The service automatically assigns ID 3.
* The created student appears in subsequent GET requests.

**Status:**
Done




## Exercise 34 - Simulated repository layer

**Purpose:**
Separate in-memory data storage from business logic by introducing a repository layer.

**URL:**
http://localhost:8080/exercise34/students

**HTTP methods:**
GET, POST

**Main concepts:**

* `@Repository`.
* Repository layer.
* Constructor injection.
* Separation of responsibilities.
* In-memory persistence.
* Automatic ID generation.

**Files:**
`src/main/java/com/angel/springbootlearning/exercises/exercise34/StudentController4.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise34/StudentService4.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise34/StudentRepository.java`

**Request file:**
`requests/exercise34.http`

**Tested cases:**

* Getting all students returns HTTP 200 OK.
* Creating a student returns HTTP 201 Created.
* The client sends only the name and role.
* The service assigns the ID automatically.
* The repository stores the created student.
* The created student appears in the following GET request.

**Status:**
Done

## Exercise 35 - Shared repository

**Purpose:**
Reuse a shared student repository across different exercises instead of creating a new repository for each one.

**URL:**
http://localhost:8080/exercise35/students

**HTTP methods:**
GET, POST

**Main concepts:**

* Shared repository.
* Reusable project components.
* Constructor injection.
* Separation between controller, service and repository.
* Automatic ID generation.

**Files:**

`src/main/java/com/angel/springbootlearning/exercises/exercise35/StudentController5.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise35/StudentService5.java`

`src/main/java/com/angel/springbootlearning/student/repository/StudentRepository.java`

**Request file:**
`requests/exercise35.http`

**Tested cases:**

* Getting all students returns HTTP 200 OK.
* Creating a student returns HTTP 201 Created.
* The client sends only the name and role.
* The service assigns the ID automatically.
* The shared repository stores the created student.
* The created student appears in the following GET request.

**Status:**
Done

## Exercise 36 - Service validation

**Purpose:**
Validate student data in the service layer before storing it.

**URL:**
http://localhost:8080/exercise36/students

**HTTP methods:**
GET, POST

**Main concepts:**

* Service-layer validation.
* `ResponseStatusException`.
* Private validation methods.
* `Stream` and `anyMatch()`.
* `equalsIgnoreCase()`.
* HTTP 400 Bad Request.
* HTTP 409 Conflict.

**Files:**
`src/main/java/com/angel/springbootlearning/exercises/exercise36/StudentController6.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise36/StudentService6.java`

`src/main/java/com/angel/springbootlearning/student/repository/StudentRepository.java`

**Request file:**
`requests/exercise36.http`

**Tested cases:**

* Getting all students returns HTTP 200 OK.
* Creating a valid student returns HTTP 201 Created.
* Duplicate names return HTTP 409 Conflict.
* Duplicate names with different capitalization return HTTP 409 Conflict.
* Empty, blank or missing names return HTTP 400 Bad Request.
* Empty or missing roles return HTTP 400 Bad Request.

**Status:**
DONE

## Exercise 37 - In-memory repository

**Purpose:**
Manage students through an independent in-memory repository and retrieve them by ID.

**URL:**
http://localhost:8080/exercise37/students

**HTTP methods:**
GET, POST

**Main concepts:**

* Controller, service and repository layers.
* In-memory data storage.
* Constructor injection.
* Automatic ID generation.
* `Optional<Student37>`.
* `filter()` and `findFirst()`.
* `orElseThrow()`.
* HTTP 404 Not Found.

**Files:**

`src/main/java/com/angel/springbootlearning/exercises/exercise37/Student37.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise37/StudentRequest37.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise37/StudentRepository37.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise37/StudentService37.java`

`src/main/java/com/angel/springbootlearning/exercises/exercise37/StudentController37.java`

**Request file:**
`requests/exercise37.http`

**Tested cases:**

* Getting an empty student list returns HTTP 200 OK.
* Creating students returns HTTP 201 Created.
* The service assigns IDs automatically.
* Created students are stored in memory.
* Getting an existing student by ID returns HTTP 200 OK.
* Getting a nonexistent student returns HTTP 404 Not Found.

**Status:**
DONE

## Exercise 38 - Complete layered flow

**Purpose:**
Manage students through controller, service and repository layers, including filtering by role and customized error responses.

**URL:**
http://localhost:8080/exercise38/students

**HTTP methods:**
GET, POST

**Main concepts:**

* Complete flow: Controller → Service → Repository.
* Filtering students by role with `@RequestParam`.
* Returning multiple matches with `filter()` and `toList()`.
* Checking empty lists with `isEmpty()`.
* Custom list responses with `StudentListResponse38`.
* Centralized exception handling with `@RestControllerAdvice`.
* Custom JSON errors with `ErrorResponse38`.
* Handling exceptions with `@ExceptionHandler`.

**Files:**

* `Student38.java`
* `StudentRequest38.java`
* `StudentRepository38.java`
* `StudentService38.java`
* `StudentController38.java`
* `StudentListResponse38.java`
* `ErrorResponse38.java`
* `GlobalExceptionHandler38.java`

**Request file:**
`src/main/java/com/angel/springbootlearning/exercises/exercise38/exercise38.http`

**Tested cases:**

* Getting an empty list returns HTTP 200 OK with an explanatory message.
* Creating students returns HTTP 201 Created.
* Getting all students returns HTTP 200 OK.
* Filtering students by an existing role returns HTTP 200 OK.
* Filtering by a nonexistent role returns HTTP 404 Not Found.
* Getting an existing student by ID returns HTTP 200 OK.
* Getting a nonexistent student returns HTTP 404 Not Found.
* Errors use the customized `ErrorResponse38` JSON structure.

**Status:**
DONE

## Exercise 39 - Layered student CRUD

**Purpose:**
Refactor the complete in-memory student CRUD using Controller, Service and Repository layers.

**URL:**
http://localhost:8080/exercise39/students

**HTTP methods:**
GET, POST, PUT, PATCH, DELETE

**Main concepts:**

* Layered architecture: Controller, Service and Repository.
* Dependency injection through constructors.
* In-memory persistence with `ArrayList`.
* Searches by ID and name using `Optional`.
* Filtering by role using `Stream`.
* Full updates with PUT and partial updates with PATCH.
* Personalized success and error responses.
* Centralized exception handling.

**Files:**
`src/main/java/com/angel/springbootlearning/exercises/exercise39/`

**Request file:**
`requests/exercise39.http`

**Tested cases:**

* List all students and return a personalized message for an empty list.
* Create students successfully with HTTP 201 Created.
* Search by ID or name and filter by role.
* Reject simultaneous `name` and `role` parameters with HTTP 400 Bad Request.
* Reject missing or empty required data with HTTP 400 Bad Request.
* Reject an empty PATCH request with HTTP 400 Bad Request.
* Reject duplicate names with HTTP 409 Conflict.
* Return HTTP 404 Not Found for missing IDs, names and roles.
* Update students successfully with PUT and PATCH.
* Delete a student by ID with HTTP 200 OK and return the deleted student's data.
* Return HTTP 404 Not Found when deleting a missing student.

**Status:**
DONE

## Exercise 39 Bis - Advanced Layered CRUD

**Purpose:**
Implement a complete in-memory student CRUD using controller, service and repository layers, uniform responses, validation and centralized error handling.

**URL:**
http://localhost:8080/exercise39bis/students

**HTTP methods:**
GET, POST, PUT, PATCH, DELETE

**Main concepts:**

* Layered architecture
* Constructor dependency injection
* Immutable records and collections
* Search by ID, name and role
* Full and partial updates
* Normalized duplicate validation
* Structured success and error responses
* Global exception handling
* Automatic ID and registration date
* Multiple deletion by role

**Files:**

* `Student39Bis.java`
* `StudentRequest39Bis.java`
* `StudentClassResponse39Bis.java`
* `StudentRepository39Bis.java`
* `StudentService39Bis.java`
* `StudentController39Bis.java`
* `GlobalExceptionHandler39Bis.java`

**Request file:**
`exercise39bis.http`

**Tested cases:**

* Empty collection returns HTTP 200 OK with an informative message.
* Students are created with an automatic ID and registration date.
* Students can be retrieved by ID, name and role.
* A duplicate normalized name returns HTTP 409 Conflict.
* Empty required fields return HTTP 400 Bad Request.
* Missing students and roles return HTTP 404 Not Found.
* PUT updates every editable field.
* PATCH updates one or both editable fields.
* An empty PATCH request returns HTTP 400 Bad Request.
* Students can be deleted by ID and name.
* Multiple students can be deleted by role.
* Multiple deletion returns the number and list of deleted students.
* The collection returns to an empty state after deletion.

**Status:**
DONE





## Exercise 40 - Architecture Review

**Purpose:**
Review and consolidate a complete in-memory student CRUD through a canonical layered architecture, using custom exceptions and centralized HTTP error handling.

**URLs:**
http://localhost:8080/exercise40/students  
http://localhost:8080/exercise40/students/{id}  
http://localhost:8080/exercise40/students?name={name}  
http://localhost:8080/exercise40/students?role={role}

**HTTP methods:**
GET, POST, PUT, PATCH, DELETE

**Main concepts:**

* Canonical architecture: Controller → Service → Repository.
* Constructor dependency injection.
* Separation between HTTP handling, business rules and in-memory persistence.
* Custom exceptions extending `RuntimeException`.
* Centralized exception handling with `@RestControllerAdvice`.
* Exception mapping with `@ExceptionHandler`.
* Structured error responses for HTTP 400, 404 and 409.
* Request and response DTOs.
* Required-field and duplicate-name validation in the service.
* Search and deletion by ID, name and role.
* Full updates with PUT and partial updates with PATCH.
* Automatic ID and registration date.

**Files:**
`src/main/java/com/angel/springbootlearning/exercises/exercise40/`

**Pending verification:**

* Correct the path mapping used to retrieve a student by ID.
* Correct the mappings that reject simultaneous `name` and `role` filters.
* Add and run the complete REST Client request file.
* Verify successful CRUD operations and HTTP 400, 404 and 409 responses.

**Status:**
DONE



## Exercise 41 - Domain Model

**Purpose:**
Define the internal student model as the foundation for separating domain data from request and response DTOs.

**URLs:**
None.

**HTTP methods:**
None.

**Main concepts:**

* Internal domain model.
* Immutable Java record.
* Student identity, name, role and registration date.
* Compilation with Maven.

**File:**
`src/main/java/com/angel/springbootlearning/exs2/ex41/Student41.java`

**Tested cases:**

* The project compiles successfully with `.\mvnw.cmd compile`.

**Status:**
DONE

**Purpose:**
Review and consolidate a complete in-memory student CRUD through a canonical layered architecture, using custom exceptions and centralized HTTP error handling.

**URLs:**
http://localhost:8080/exercise40/students  
http://localhost:8080/exercise40/students/{id}  
http://localhost:8080/exercise40/students?name={name}  
http://localhost:8080/exercise40/students?role={role}

**HTTP methods:**
GET, POST, PUT, PATCH, DELETE

**Main concepts:**

* Canonical architecture: Controller → Service → Repository.
* Constructor dependency injection.
* Separation between HTTP handling, business rules and in-memory persistence.
* Custom exceptions extending `RuntimeException`.
* Centralized exception handling with `@RestControllerAdvice`.
* Exception mapping with `@ExceptionHandler`.
* Structured error responses for HTTP 400, 404 and 409.
* Request and response DTOs.
* Required-field and duplicate-name validation in the service.
* Search and deletion by ID, name and role.
* Full updates with PUT and partial updates with PATCH.
* Automatic ID and registration date.

**Files:**
`src/main/java/com/angel/springbootlearning/exercises/exercise40/`

**Pending verification:**

* Correct the path mapping used to retrieve a student by ID.
* Correct the mappings that reject simultaneous `name` and `role` filters.
* Add and run the complete REST Client request file.
* Verify successful CRUD operations and HTTP 400, 404 and 409 responses.

**Status:**
IN PROGRESS