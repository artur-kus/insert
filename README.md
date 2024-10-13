# Insert Recruitment
## Running the Application

1. Clone the repo:
```bash
git clone https://github.com/artur-kus/insert.git
```

2. For start the application you need to prepare your own properties.
```text
-DMYSQL_PORT=3307 -DMYSQL_DB=insert_app -DMYSQL_USERNAME=root -DMYSQL_PASSWORD=root
```
- MYSQL_PORT - port on which the database works
- MYSQL_DB - name of schema for that application
- MYSQL_USERNAME - db username
- MYSQL_PASSWORD - db password user
<br>
<br>
3. Choose project folder:
```bash
cd insert
```

4. Build package (with my default arguments or you own, all in step 2):
```bash
mvn clean package -DMYSQL_PORT=3307 -DMYSQL_DB=insert_app -DMYSQL_USERNAME=root -DMYSQL_PASSWORD=root
 ```

4. Start spring boot application:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--MYSQL_PORT=3307,--MYSQL_DB=insert_app,--MYSQL_USERNAME=root,--MYSQL_PASSWORD=root
```

When all steps are completed, application should run on port ***8900*** 
