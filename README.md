# JOINTECH
Create JOINTECH by your self

- Run this application with apache maven
- Run test with command : 
mvn -DTest:SampleClass test, example : mvn -DTest:InitDataTest test

- Run application : 
mvn spring-boot:run

- Compile and run application with skip test :
mvn clean package spring-boot:run -DskipTests

- Run application with skip test and remote debug :
mvn spring-boot:run -DskipTests -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

- Or Run application with server.sh in directory server :
./server.sh start

---- currently ----
- build spring-boot to war :
mvn package -DskipTests

- run spring-boot .war:
java -jar filename.war


- Access link url :
http://localhost:8181/login

- Git ignore file mode :
git config core.fileMode false

- Git ignore file remove cache :
git rm --cached FILENAME. Example git rm --cached server.sh


- Root folder
	* webapp
		* admin
		* pages
			* v1
				* pages
				* spa
					* templates
					* js e.g -> "/admin/v1/spa/js/sammy.template.js"
		* login
		* resources ( public )
			* boostrap
			* dist
			* plugis

- Request API
	1. Request token
		* URL : http://localhost:8181/admin/v1/security/request-token
		* HTTP Method = POST
		* Header Content-Type : "application/json" / "application/xml"
		* Request body json format : {"user":"ariariari","token":"98253668-476e-47c9-bf7a-05aaad45bf18"}
		* Response format : JSON / XML
		* Response body in json message format : {"sessionToken": "46C9FA43D36905106BD03BC89C93EF33", "csrfToken": "63b71d2a-b695-49f9-bf32-9fdd784b6fa3"}
	2. Auth token 
		* URL : http://localhost:8181/login
		* HTTP Method = POST
		* Header cookie : e.g JSESSIONID=46C9FA43D36905106BD03BC89C93EF33; XSRF-TOKEN=3b71d2a-b695-49f9-bf32-9fdd784b6fa3
		* Header X-XSRF-TOKEN : e.g 3b71d2a-b695-49f9-bf32-9fdd784b6fa3
		* Request body form-data e.g: 
			* username:ari
			* password:1234 
		* Response :
			* cookies JSESSIONID: e.g 1B2898B70D99DC13F54DAE4A9CF078D6
			* XSRF-TOKEN : e.g a7055b90-a035-420b-8674-275fa8b55eda
	3. API
		* E.g 1 :
			* URL e.g : http://localhost:8181/admin/v1/api/user/editUser
			* HTTP Method = POST
			* Header Content-Type : application/json / "application/xml"
			* Header cookie : e.g JSESSIONID=1B2898B70D99DC13F54DAE4A9CF078D6; XSRF-TOKEN=a7055b90-a035-420b-8674-275fa8b55eda
			* Header X-XSRF-TOKEN : e.g a7055b90-a035-420b-8674-275fa8b55eda
			* Request body json format  : { "request":{"username":"ari","name":"notblank","email":"prasetiyooo@gmail.com","noHp":"085645480401","roles":[{"roleName":"admin","id":"1"}],"isActive":true,"password":"dummay","id":"1"} }
			* Response body json format :
				```json
				{
				    "messageStatus": "UPDATE_SUCCEED",
				    "message": "UPDATE SUCCESS",
				    "response": {
				        "id": 1,
				        "modifiedTime": "09-12-2017 11:15:58",
				        "disabled": false,
				        "username": "ari",
				        "name": "notblank",
				        "password": "dummay",
				        "email": "prasetiyooo@gmail.com",
				        "noHp": "085645480401",
				        "isActive": true,
				        "roles": [
				            {
				                "id": 1,
				                "createdTime": "09-12-2017 11:15:58",
				                "disabled": false,
				                "roleName": "admin"
				            }
				        ]
				    }
				}
				``` 
		* E.g 2 :
			* URL e.g : http://localhost:8181/admin/v1/api/user/list
			* HTTP Method = POST
			* Header cookie : e.g JSESSIONID=1B2898B70D99DC13F54DAE4A9CF078D6; XSRF-TOKEN=a7055b90-a035-420b-8674-275fa8b55eda
			* Header X-XSRF-TOKEN : e.g a7055b90-a035-420b-8674-275fa8b55eda
			* Request body json format e.g : {"request":{"limit":10,"offset":0,"search":""}}
			* Response body in json message format :
				```json
				{
				    "messageStatus": "LIST_SUCCEED",
				    "message": "LIST SUCEESS",
				    "response": {
				        "disabled": false,
				        "totalRecord": 2,
				        "listUser": [
				            {
				                "id": 1,
				                "createdTime": "16-04-2017 01:14:39",
				                "modifiedTime": "16-04-2017 01:14:39",
				                "version": "1.0",
				                "disabled": false,
				                "username": "ari",
				                "name": "",
				                "email": "prasetiyooo@gmail.com",
				                "noHp": "085645480401",
				                "isActive": true,
				                "roles": [
				                    {
				                        "id": 1,
				                        "createdTime": "15-04-2017 02:02:17",
				                        "modifiedTime": "15-04-2017 02:02:17",
				                        "version": "1.0",
				                        "disabled": false,
				                        "roleName": "admin"
				                    }
				                ]
				            },
				            {
				                "id": 2,
				                "createdTime": "04-04-2017 03:15:20",
				                "modifiedTime": "03-12-2017 04:09:42",
				                "disabled": false,
				                "username": "coba",
				                "name": "coba",
				                "email": "coba@coba",
				                "noHp": "1111111111",
				                "isActive": true,
				                "roles": [
				                    {
				                        "id": 4,
				                        "createdTime": "03-12-2017 04:09:42",
				                        "modifiedTime": "03-12-2017 04:09:42",
				                        "disabled": false,
				                        "roleName": "public"
				                    }
				                ]
				            }
				        ]
				    }
				}
				```

```		
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.7.RELEASE)
```
