REST Store API
==============
RESTful webservice for a simple store app.

**Version:** 1.0

**Terms of service:**  
urn:tos

**Contact information:**  
Zoltan Orban  
zoltan.orban@gmail.com  

**License:** [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)

### /error
---
##### ***GET***
**Summary:** error

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

##### ***POST***
**Summary:** error

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

##### ***PUT***
**Summary:** error

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

##### ***DELETE***
**Summary:** error

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 204 | No Content |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |

##### ***OPTIONS***
**Summary:** error

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 204 | No Content |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |

##### ***PATCH***
**Summary:** error

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 204 | No Content |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |

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
**Summary:** placeOrder

**Parameters**

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| webOrder | body | webOrder | Yes | [WebOrder](#weborder) |

**Responses**

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | object |
| 201 | Created |  |
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

### ModelAndView  

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| empty | boolean |  | No |
| model | object |  | No |
| modelMap | object |  | No |
| reference | boolean |  | No |
| status | string |  | No |
| view | [View](#view) |  | No |
| viewName | string |  | No |

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

### View  

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| contentType | string |  | No |

### WebOrder  

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| buyerEmail | string |  | No |
| orderPlaced | dateTime |  | No |
| products | [ long ] |  | No |