# Spring Boot Project Template

The purpose of this repository is to provide a template for quickly building proof of concept applications without
having to reimplement the wheel for each and every project.

The following setup has been implemented as part of the project:

* Spring Data integration with a connection to `Redis`.
* Service Provider integrated with a service discovery node with `Apache Zookeeper`.
* Basic RESTFul API implemented using basic `GET`, `POST`, `PUT` and `DELETE`operations for manipulating an `Entry` in a
  data store.
* Queue consumer service for processing queued messages via `RabitMQ`.

---

## Development Redis Server Setup Docker Instance Installation

The `SpringBootTemplate` is built on an implementation of `Redis` using `Spring Data`. If you do not have access to
a `Redis`server, please follow the steps as below for getting started quickly.

This can be rapidly be setup by utilising `Docker` to setup the `Redis`container.

Below you will find `Docker`commands for downloading and starting up a local `Redis` instance.

```shell
> docker pull redis
> docker run --name=redis-devel --publish=6379:6379 --hostname=redis --restart=on-failure --detach redis:latest
```

---

## Development Zookeeper Server Setup Docker Instance Installation

The `SpringBootTemplate` has in built with `Zookeeper`as a service discovery tool. If you do not have access to
a `Zookeeper`server, please follow the steps as below for getting started quickly.

For development purposes, this can be rapidly setup by utilizing `Docker` to setup the `Zookeeper`container.

Below you will find `Docker` commands for downloading and starting up a local `Zookeeper`instance.

```shell
> docker pull zookeeper
> docker run -it --name=local-zookeeper --publish 2181:2181 --hostname=zookeeper --restart=on-failure --detach zookeeper
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

## Development RabbitMQ Server Setup Docker Instance Installation

The `SpringBootTemplate` has in built with `RabbitMQ`as the framework for implementing a a message broker protocol
system. If you do not have access to a `RabbitMQ`server, please follow the steps as below for getting started quickly.

For development purposes, this can be rapidly setup by utilizing `Docker` to setup the `RabbitMQ`container.

Below you will find `Docker` commands for downloading and starting up a local `RabbitMQ`instance.

```shell
> docker pull rabbitmq
> docker run -it --name=local-rabbitmq --publish 5672:5672,15672:15672 --hostname=rabbitmq --restart=on-failure --detach rabbitmq:3-management-alpine
```

This will startup a `RabbitMQ`instance in `Docker` on port `5672` with the management console on `15672` for your local
machine. You can hit `http://localhost:15672/` to verify the management console started up correctly.

By navigating to `src/main/resources/bootstrap.properties` you can configure the `RabbitMQ`server by updating the
properties.

```properties
# RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

---

# Dockerize the EntryAPI into a Docker Container

## Create Docker Image of EntryAPI

1. First you need to generate a jar of the latest version of the application. This can be done via maven with the
   following command:

```shell
> ./mvnw clean package
```

2. Verify the jar starts up correctly

```shell
> java -jar target/demo<insert version>.jar
```

Example: `java -jar target/demo-0.0.1-SNAPSHOT.jar`.

3. Create the `Docker` Image

```shell
> docker build --tag=entryapi .
```

4. Pull down additional docker images from the `Docker` hub.

```shell
> docker pull redis
> docker pull zookeeper 
> docker pull rabbitmq
```

5. Create `Docker` network bridge

``` shell
> docker network create -d bridge spring-template-network
```

6. Compose `Docker` container

```shell
> docker-compose up
```

7. Verify `Docker` containers are up and running

```shell
> docker ps -a
```

Expected output should be as follows:

```shell
CONTAINER ID   IMAGE              COMMAND                  CREATED          STATUS                        PORTS                                                  NAMES                                
dfb6238f2e4b   entryapi           "java -jar demo-0.0.…"   29 minutes ago   Up 9 seconds                  0.0.0.0:8080->8080/tcp                                 springbootprojecttemplate_entryapi_1
505a08a4b9c6   zookeeper          "/docker-entrypoint.…"   29 minutes ago   Up 9 seconds                  2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp, 8080/tcp   springbootprojecttemplate_zookeeper_1
db090e0b45a0   redis              "docker-entrypoint.s…"   29 minutes ago   Up 9 seconds                  0.0.0.0:6379->6379/tcp                                 springbootprojecttemplate_redis_1
```

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
    "value": "Rabbit Test"
  }
}
```

Sample Request:

```shell
curl --location --request POST 'http://localhost:8080/entries/entry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "entry": {
        "value":"Rabbit Test"
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
    "id": "5de624fb-cb76-41d4-ba24-070d7b87f6f2",
    "value": "Rabbit Test",
    "audits": {
      "auditList": [
        {
          "timestamp": "2021-03-30T13:02:31.21",
          "auditId": 1,
          "action": "Add"
        }
      ],
      "nextAuditId": 2
    }
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
    "id": "5de624fb-cb76-41d4-ba24-070d7b87f6f2",
    "value": "Rabbit Test"
  },
  "error": null
}
```

Sample Request:

```shell
curl --location --request DELETE 'http://localhost:8080/entries/entry' \
--header 'Content-Type: application/json' \
--data-raw '{
    "entry": {
        "id": "5de624fb-cb76-41d4-ba24-070d7b87f6f2",
            "value": "Rabbit Test"
    },
    "error": null
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
    "id": "5de624fb-cb76-41d4-ba24-070d7b87f6f2",
    "value": "Rabbit Test",
    "audits": {
      "auditList": [
        {
          "timestamp": "2021-03-30T13:02:31.21",
          "auditId": 1,
          "action": "Add"
        },
        {
          "timestamp": "2021-03-30T13:03:40.582",
          "auditId": 2,
          "action": "Delete"
        }
      ],
      "nextAuditId": 3
    }
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
  "key":"Ringo Star",
  "entry": {
    "id": "47f268bd-0d95-480b-8261-bb436e6ed96c",
    "value": "Ringo Star"
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
        "id": "47f268bd-0d95-480b-8261-bb436e6ed96c",
        "value": "Ringo Star"
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
    "value": "John Lennon",
    "createdDateTime": "2021-03-29T14:48:50.256",
    "lastModifiedDateTime": "2021-03-29T14:49:50.256",
    "auditId": 2
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
curl --location --request GET 'http://localhost:8080/entries/entry/Ringo Star/id/47f268bd-0d95-480b-8261-bb436e6ed96c'
```

### Response

The following response structure is expected for the get `Entry` operation.

### Success (HTTP Response: 200 Ok)

The `Entry` is returned to the user if found.

Sample Successful Response:

```json
{
  "entry": {
    "id": "47f268bd-0d95-480b-8261-bb436e6ed96c",
    "value": "Ringo Star",
    "audits": {
      "auditList": [
        {
          "timestamp": "2021-03-30T13:02:04.119",
          "auditId": 1,
          "action": "Add"
        },
        {
          "timestamp": "2021-03-30T13:02:16.678",
          "auditId": 2,
          "action": "Update"
        }
      ],
      "nextAuditId": 3
    }
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
curl --location --request GET 'http://localhost:8080/entries'
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
      "id": "5de624fb-cb76-41d4-ba24-070d7b87f6f2",
      "value": "Rabbit Test",
      "audits": {
        "auditList": [
          {
            "timestamp": "2021-03-30T13:02:31.21",
            "auditId": 1,
            "action": "Add"
          }
        ],
        "nextAuditId": 2
      }
    },
    {
      "id": "47f268bd-0d95-480b-8261-bb436e6ed96c",
      "value": "Ringo Star",
      "audits": {
        "auditList": [
          {
            "timestamp": "2021-03-30T13:02:04.119",
            "auditId": 1,
            "action": "Add"
          },
          {
            "timestamp": "2021-03-30T13:02:16.678",
            "auditId": 2,
            "action": "Update"
          }
        ],
        "nextAuditId": 3
      }
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