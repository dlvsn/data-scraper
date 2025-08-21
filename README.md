# data-scraper
# How to run the project
### Prerequisites:
- **Java 17 must be installed on your system.**
- **MySQL should be installed and running. Ensure you have the credentials (username and password) to access your MySQL database.**
### Clone the Repository:
```
git clone https://github.com/dlvsn/data-scraper
cd <project_directory>
```
### Set Up the Database:
- **Create a MySQL database.**
- **Create your own .env file and fill in the required data.**
   ```
   MYSQL_DATABASE=
  MYSQLDB_USER=
  MYSQLDB_ROOT_PASSWORD=
  MYSQLDB_URL=

  MYSQLDB_LOCAL_PORT=
  MYSQLDB_DOCKER_PORT=
  SPRING_LOCAL_PORT=
  SPRING_DOCKER_PORT=
  DEBUG_PORT=
   ```
### Run the Application:
- **Open a terminal in the project directory and execute:**
  ``` 
  ./mvnw spring-boot:run
  ```
- **Alternatively, if you are using an IDE like IntelliJ IDEA, open the project, locate the Application class (usually in the src/main/java directory), and run it.**
- **Run with Docker. You can also run the project using Docker by executing the following command:**
  ```
  docker-compose up --build
  ```

  ### SQL DUMP
  ``` 
  https://drive.google.com/file/d/1cOtSa4NLADh2n3c1zaBFxlCO9s8XLjjL/view?usp=sharing
  
  ```
