# Spring Boot Project Template

The purpose of this repository is to provide a template for quickly building proof of concept applications without
having to reimplement the wheel for each and every project.

The following setup has been implemented as part of the project:

* Spring Data integration with a connection to `Redis`.
* Service Provider integrated with a service discovery node with `Apache Zookeeper`.
* Basic RESTFul API implemented using basic `GET`, `POST`, `PUT` and `DELETE`operations for manipulating an `Entry` in a
  data store.

---

## Redis Server Setup Docker Instance Installation

The `SpringBootTemplate` is built on an implementation of `Redis` using `Spring Data`. If you do not have access to
a `Redis`server, please follow the steps as below for getting started quickly.

This can be rapidly be setup by utilising `Docker` to setup the `Redis`container.

Below you will find `Docker`commands for downloading and starting up a local `Redis` instance.

```shell
> docker pull redis
> docker run --name=redis-devel --publish=6379:6379 --hostname=redis --restart=on-failure --detach redis:latest
```

---

## Zookeeper Server Setup Docker Instance Installation

The `SpringBootTemplate` has in built with `Zookeeper`as a service discovery tool. If you do not have access to
a `Zookeeper`server, please follow the steps as below for getting started quickly.

For development purposes, this can be rapidly setup by utilizing `Docker` to setup the `Zookeeper`container.

Below you will find `Docker` commands for downloading and starting up a local `Zookeeper`instance.

```shell
> docker run  zookeeper
> docker run -p 2181:2181 --name local-zookeeper --restart always -d zookeeper
```

This will startup a `Zookeeper`instance in `Docker` on port `2181` for your local machine.

By navigating to `src/main/resources/bootstrap.properties` you can enable and configure the `Zookeeper`server by
updating the properties.

```properties
# Zookeeper Configuration (Disabled by default)
spring.cloud.zookeeper.enabled=true
spring.cloud.zookeeper.discovery.enabled=true
# Configure to your Zookeeper instance.
spring.cloud.zookeeper.connect-string=localhost:2181
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

### Success (HTTP Response: 201 Created)

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

### Error Scenarios

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
    "value": "John Lennon"
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

The following response structure is expected for the delete `Entry` operation.

### Success (HTTP Response: 200 Ok)

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

### Error Scenarios

An `Error` is returned to the user if the `Entry` was not deleted successfully.

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

## Update Entry

The update `Entry` operation is supported by the following Restful CRUD operation:
> PUT /entries/entry

### Request

Request Body - An `EntryActionInput` object:

```json
{
  "key": "Ringo Star",
  "entry": {
    "id": "aab81683-ce86-4954-9207-6094a1d82e7f",
    "value": "Ringo Starr"
  }
}
```

Sample Request:

```shell
curl --location --request PUT 'http://localhost:8080/entries/entry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "key":"Ringo Star",
    "entry": {
        "id": "aab81683-ce86-4954-9207-6094a1d82e7f",
            "value": "Ringo Starr"
    }
}'
```

### Response

The following response structure is expected for the update `Entry` operation.

#### Success (HTTP Response: 200 Ok)

The `Entry` which has been updated is returned to the user if updated successfully.

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

### Error Scenarios

An `Error` is returned to the user if the `Entry` was not updated successfully.

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

## Get Entry

The get `Entry` operation is supported by the following Restful CRUD operation:
> GET /entries/entry/{value}/id/{id}

### Request

Sample Request:

```shell
curl --location --request GET 'http://localhost:8080/entries/entry/John Lennon/id/ddabce27-f6a7-4860-8111-5728b5f4c915'
```

### Response

The following response structure is expected for the get `Entry` operation.

### Success (HTTP Response: 200 Ok)

The `Entry` is returned to the user if found.

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

### Error Scenarios

An `Error` is returned to the user if the `Entry` was not found successfully.

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

## Get All Entries

The get all `Entry` operation is supported by the following Restful CRUD operation:
> GET /entries

### Request

Sample Request:

```shell
curl --location --request GET 'http://localhost:8080/entries/entry/John Lennon/id/ddabce27-f6a7-4860-8111-5728b5f4c915'
```

### Response

The following response structure is expected for the get all `Entry` operation.

### Success (HTTP Response: 200 Ok)

An array of `Entry` is returned if more than one `Entry`is found.

Sample Successful Response:

```json
{
  "entries": [
    {
      "id": "d8f79374-b51c-47ef-9937-c44323d54183",
      "value": "Paul McCartney"
    },
    {
      "id": "aab81683-ce86-4954-9207-6094a1d82e7f",
      "value": "Ringo Starr"
    },
    {
      "id": "f0462965-4488-45f5-a52a-5b5bffc5fade",
      "value": "George Harrison"
    },
    {
      "id": "8bbdf639-942b-41ea-936f-4ddeea6601f5",
      "value": "John Lennon"
    }
  ],
  "error": null
}
```

### Error Scenarios

An `Error` is returned to the user if not a single `Entry` was found successfully.

**Error Codes:**

| Error Code    | Error Description  | HTTP Code     |          
| ------------- |-------------| -------------|
| 2             | "Entry could not be found." | 404 |

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

---