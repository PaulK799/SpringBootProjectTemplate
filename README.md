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
    "value": "John Lennon"
  },
  "error": null
}
```

#### Error (HTTP Response: 404 Not Found)

An `Error` is returned to the user if the `Entry` was not added successfully.

**Error Codes:**

| Error Code    | Error Description           
| ------------- |-------------| 
| 1             | "Unable to add Entry. Entry already exists." |

Sample Error Response:
```json
{
  "entry": null,
  "error": {
    "code": "1",
    "description": "Unable to add Entry. Entry already exists."
  }
}
```

---