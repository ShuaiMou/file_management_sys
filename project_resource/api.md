
**swagger-bootstrap-ui-前后端api接口文档**


**简介**：<p>FMS 系统接口文档</p>


**HOST**:localhost:8080

**联系人**:Saul

**Version**:1.0.0


# 文件管理

## 查询下载历史

**接口描述**:用户查询自己的下载过的文件

**接口地址**:`/file/downloadHistory`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|downloaderEmail| 用户唯一标识符-邮箱  | query | true |ref  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |返回数据对象|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 上传文件

**接口描述**:上传文件到服务器

**接口地址**:`/file/upload`


**请求方式**：`POST`


**consumes**:`["multipart/form-data"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|domain| 文件所属领域  | query | true |ref  |    |
|file| 上传的文件  | formData | true |ref  |    |
|uploaderEmail| 上传者账号-邮箱  | query | true |ref  |    |
|uploaderName| 上传者用户名  | query | true |ref  |    |

**200响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**200响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |







**201响应示例**:

```json
{
	"createTime": "",
	"fileID": 0,
	"fileName": "",
	"size": 0,
	"uploaderEmail": "",
	"uploaderName": "",
	"visitedTimes": 0
}
```

**201响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|createTime|   |string  |    |
|fileID|   |integer(int64)  | integer(int64)   |
|fileName|   |string  |    |
|size|   |integer(int32)  | integer(int32)   |
|uploaderEmail|   |string  |    |
|uploaderName|   |string  |    |
|visitedTimes|   |integer(int64)  | integer(int64)   |

**500响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**500响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |








**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |返回数据对象|
| 201 | new resource is created  |FMSFile|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
| 500 | 服务器内部异常  |返回数据对象|
## 查询上传历史

**接口描述**:用户查询自己的上传文件

**接口地址**:`/file/uploadHistory`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|uploaderEmail| 用户唯一标识符-邮箱  | query | true |ref  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |返回数据对象|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 用户管理
## 登录验证

**接口描述**:传入用户名和密码登录系统

**接口地址**:`/user/login.do`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|email| 用户唯一ID  | query | true |ref  |    |
|password| 用户登录密码  | query | true |ref  |    |

**200响应示例**:

```json
{
	"account": "",
	"createTime": "",
	"domain": "",
	"downloadCounter": 0,
	"gender": "",
	"level": "",
	"password": "",
	"updateTime": "",
	"uploadCounter": 0,
	"username": ""
}
```

**200响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|account|   |string  |    |
|createTime|   |string  |    |
|domain|   |string  |    |
|downloadCounter|   |integer(int32)  | integer(int32)   |
|gender|   |string  |    |
|level| 可用值:Basic,PREMIUM,VIP  |string  |    |
|password|   |string  |    |
|updateTime|   |string  |    |
|uploadCounter|   |integer(int32)  | integer(int32)   |
|username|   |string  |    |









**401响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**401响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |

**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | ok  |用户对象|
| 201 | Created  ||
| 401 | 用户名与密码不一致或用户不存在  |返回数据对象|
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 退出登录

**接口描述**:退出用户登录，清除session

**接口地址**:`/user/logout.do`


**请求方式**：`DELETE`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|creationTime|   | query | false |integer  |    |
|id|   | query | false |string  |    |
|lastAccessedTime|   | query | false |integer  |    |
|maxInactiveInterval|   | query | false |integer  |    |
|new|   | query | false |boolean  |    |
|servletContext.classLoader|   | query | false |ref  |    |
|servletContext.contextPath|   | query | false |string  |    |
|servletContext.defaultSessionTrackingModes| 枚举类型,可用值:COOKIE,URL,SSL  | query | false |array  | string   |
|servletContext.effectiveMajorVersion|   | query | false |integer  |    |
|servletContext.effectiveMinorVersion|   | query | false |integer  |    |
|servletContext.effectiveSessionTrackingModes| 枚举类型,可用值:COOKIE,URL,SSL  | query | false |array  | string   |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].buffer|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].defaultContentType|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].deferredSyntaxAllowedAsLiteral|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].elIgnored|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].errorOnUndeclaredNamespace|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].includeCodas|   | query | false |array  | string   |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].includePreludes|   | query | false |array  | string   |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].isXml|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].pageEncoding|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].scriptingInvalid|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].trimDirectiveWhitespaces|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.jspPropertyGroups[0].urlPatterns|   | query | false |array  | string   |
|servletContext.jspConfigDescriptor.taglibs[0].taglibLocation|   | query | false |string  |    |
|servletContext.jspConfigDescriptor.taglibs[0].taglibURI|   | query | false |string  |    |
|servletContext.majorVersion|   | query | false |integer  |    |
|servletContext.minorVersion|   | query | false |integer  |    |
|servletContext.requestCharacterEncoding|   | query | false |string  |    |
|servletContext.responseCharacterEncoding|   | query | false |string  |    |
|servletContext.serverInfo|   | query | false |string  |    |
|servletContext.servletContextName|   | query | false |string  |    |
|servletContext.sessionCookieConfig.comment|   | query | false |string  |    |
|servletContext.sessionCookieConfig.domain|   | query | false |string  |    |
|servletContext.sessionCookieConfig.httpOnly|   | query | false |boolean  |    |
|servletContext.sessionCookieConfig.maxAge|   | query | false |integer  |    |
|servletContext.sessionCookieConfig.name|   | query | false |string  |    |
|servletContext.sessionCookieConfig.path|   | query | false |string  |    |
|servletContext.sessionCookieConfig.secure|   | query | false |boolean  |    |
|servletContext.sessionTimeout|   | query | false |integer  |    |
|servletContext.virtualServerName|   | query | false |string  |    |
|valueNames|   | query | false |array  | string   |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |返回数据对象|
| 204 | No Content  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
## 用户注册

**接口描述**:用户校验邮箱验证码和注册

**接口地址**:`/user/register.do`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|user| user对象  | body | true |User对象  | User对象   |
|verificationCode| 邮箱发送的验证码  | query | true |ref  |    |

**200响应示例**:

```json
{
	"account": "",
	"createTime": "",
	"domain": "",
	"downloadCounter": 0,
	"gender": "",
	"level": "",
	"password": "",
	"updateTime": "",
	"uploadCounter": 0,
	"username": ""
}
```

**200响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|account|   |string  |    |
|createTime|   |string  |    |
|domain|   |string  |    |
|downloadCounter|   |integer(int32)  | integer(int32)   |
|gender|   |string  |    |
|level| 可用值:Basic,PREMIUM,VIP  |string  |    |
|password|   |string  |    |
|updateTime|   |string  |    |
|uploadCounter|   |integer(int32)  | integer(int32)   |
|username|   |string  |    |

**500响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**500响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |








**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | ok  |用户对象|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
| 500 | 服务器内部错误  |返回数据对象|
## 发送注册验证码

**接口描述**:异步邮件任务发送验证码

**接口地址**:`/user/send`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|email| 用户传入的合法邮箱地址  | query | true |ref  |    |

**响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |





**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  |返回数据对象|
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 更新用户信息

**接口描述**:更新用户个人信息

**接口地址**:`/user/update`


**请求方式**：`PUT`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`


**请求示例**：
```json
null
```


**请求参数**：

| 参数名称         | 参数说明     |     in |  是否必须      |  数据类型  |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|user| 用户对象  | body | true |User 对象  | User 对象   |

**200响应示例**:

```json
{
	"account": "",
	"createTime": "",
	"domain": "",
	"downloadCounter": 0,
	"gender": "",
	"level": "",
	"password": "",
	"updateTime": "",
	"uploadCounter": 0,
	"username": ""
}
```

**200响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|account|   |string  |    |
|createTime|   |string  |    |
|domain|   |string  |    |
|downloadCounter|   |integer(int32)  | integer(int32)   |
|gender|   |string  |    |
|level| 可用值:Basic,PREMIUM,VIP  |string  |    |
|password|   |string  |    |
|updateTime|   |string  |    |
|uploadCounter|   |integer(int32)  | integer(int32)   |
|username|   |string  |    |

**500响应示例**:

```json
{
	"code": 0,
	"data": {},
	"msg": ""
}
```

**500响应参数**:


| 参数名称         | 参数说明                             |    类型 |  schema |
| ------------ | -------------------|-------|----------- |
|code| 状态码  |integer(int32)  | integer(int32)   |
|data| 返回的数据  |object  |    |
|msg| 状态描述  |string  |    |








**响应状态**:


| 状态码         | 说明                            |    schema                         |
| ------------ | -------------------------------- |---------------------- |
| 200 | ok  |用户对象|
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
| 500 | 服务器内部错误  |返回数据对象|
