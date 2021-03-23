# Spring Boot Project Template

The purpose of this repository is to provide a template for quickly building proof of concept applications without
having to reimplement the wheel for each and every project.

The following setup has been implemented as part of the project:

* Spring Data with a connection to `Redis`.

---

## Redis Server Setup Docker Instance Installation

The `SpringBootTemplate` is built on an implementation of `Redis` using `Spring Data`. If you do not have access to
a `Redis`server, please follow the steps as below for getting started quickly.

This can be rapidly be setup by utilising `Docker` to setup the `Redis`container.

Below you will find `Docker`commands for downloading and starting up a local `Redis` instance.

```
> docker pull redis
> docker run --name=redis-devel --publish=6379:6379 --hostname=redis --restart=on-failure --detach redis:latest
```

---

# Entry API

Below you will find the API definition for the `Entry API` which has been implemented as part of
the `Spring Boot Project Template`.

---

## Add Entry

The add `Entry` operation is supported by the following Restful CRUD operation:
> POST /entries/entry

### Request

Request Body - An `EntryActionInput` object:
```json
{
  "entry": {
    "value": "John Lennon"
  }
}
```

Sample Request:
```shell
curl --location --request POST 'http://localhost:8080/entries/entry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "entry": {
      "value":"John Lennon"
    }
}'
```

### Response

The following response structure is expected for the add `Entry` operation.

#### Success (HTTP Response: 201 Created)

The entry is returned to the user if added successfully.

Sample Successful Response:
```json
{
  "entry": {
    "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
    "value": "John Lennon"
  },
  "error": null
}
```

#### Error (HTTP Response: 404 Not Found)

An `Error` is returned to the user if the `Entry` was not added successfully.

**Error Codes:**

| Error Code    | Error Description  | HTTP Code     |          
| ------------- |-------------| -------------|
| 1             | "Entry already exists." | 404 |
| 3             |  "The request body is missing or not well formed." | 400 |

Sample Error Response (Entry Already exists)
```json
{
  "entry": null,
  "error": {
    "code": "1",
    "description": "Entry already exists."
  }
}
```

Sample Error Response (Bad Request - Not Well Formed)
```json
{
  "entry": null,
  "error": {
    "code": "3",
    "description": "The request body is missing or not well formed."
  }
}
```

---

## Delete Entry

The delete `Entry` operation is supported by the following Restful CRUD operation:
> DELETE /entries/entry

### Request

Request Body - An `EntryActionInput` object:
```json
{
  "entry": {
    "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
    "value":"John Lennon"
  }
}
```

Sample Request:
```shell
curl --location --request DELETE 'http://localhost:8080/entries/entry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "entry": {
        "value":"John Lennon"
    }
}'
```

### Response

The following response structure is expected for the add `Entry` operation.

#### Success (HTTP Response: 200 Ok)

The `Entry` which has been removed is returned to the user if deleted successfully.

Sample Successful Response:
```json
{
  "entry": {
    "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
    "value": "John Lennon"
  },
  "error": null
}
```

#### Error (HTTP Response: 404 Not Found)

An `Error` is returned to the user if the `Entry` was not added successfully.

**Error Codes:**

| Error Code    | Error Description  | HTTP Code     |          
| ------------- |-------------| -------------|
| 2             | "Entry could not be found." | 404 |
| 3             | "The request body is missing or not well formed." | 400 |

Sample Error Response (HTTP Response 404 - Entry Not Found)
```json
{
  "entry": null,
  "error": {
    "code": "2",
    "description": "Entry could not be found."
  }
}
```

Sample Error Response (Bad Request - Not Well Formed)
```json
{
  "entry": null,
  "error": {
    "code": "3",
    "description": "The request body is missing or not well formed."
  }
}
```

---
