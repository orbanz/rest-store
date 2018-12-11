REST Store API
==============
RESTful webservice for a simple store app.## How to Run 

This application is Spring Boot application which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service the following way:
```
        mvn spring-boot:run
```
The application can be accessed on the following url: [http://localhost:8080/]: http://localhost:8080/
## API DOCS

**Version:** 1.0

**Terms of service:**  
urn:tos

**Contact information:**  
Zoltan Orban  
zoltan.orban@gmail.com  

**License:** [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)

### /orders
---
##### ***GET***
**Summary:** Find orders between fromDate and ToDate

**Description:** Retrieving the collection of orders

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| fromDate | query | fromDate | No | dateTime |
| toDate | query | toDate | No | dateTime |

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | Success | [ [OrderData](#orderdata) ] |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

##### ***POST***
**Summary:** Place order

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| webOrder | body | Order data | Yes | [WebOrder](#weborder) |

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

### /orders/{id}
---
##### ***GET***
**Summary:** Get Order Data

**Description:** Retreiving the data for the given id

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path | ID os the Order | Yes | long |

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [OrderData](#orderdata) |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

### /products
---
##### ***GET***
**Summary:** getProductList

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [ [Product](#product) ] |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

##### ***POST***
**Summary:** createProduct

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| product | body | product | Yes | [Product](#product) |

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

### /products/{id}
---
##### ***GET***
**Summary:** retrieveProduct

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path | id | Yes | long |

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [Product](#product) |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

##### ***PUT***
**Summary:** updateProduct

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path | id | Yes | long |
| product | body | product | Yes | [Product](#product) |

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

### Models
---

### OrderData  

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| buyerEmail | string |  | No |
| id | long |  | No |
| items | [ [OrderItem](#orderitem) ] |  | No |
| orderPlaced | dateTime |  | No |
| totalOrderAmount | double |  | No |

### OrderItem  

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| name | string |  | No |
| price | double |  | No |

### Product  

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| id | long |  | No |
| name | string |  | No |
| price | double |  | No |

### WebOrder  

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| buyerEmail | string |  | No |
| orderPlaced | dateTime |  | No |
| products | [ long ] |  | No |