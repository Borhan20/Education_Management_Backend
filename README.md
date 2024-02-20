# Education_Management_Backend
This is the backend of Education Management System<br>

Postman request:<br>
Admin:(AdminController)<br>
Admin can create Student : http://localhost:8080/admin/create-students <br>
Admin can create Teacher : http://localhost:8080/admin/create-teachers<br>
Admin can add Role : http://localhost:8080/admin/add-role/{userId}<br>
Admin can Deactive user : http://localhost:8080/admin/deactivate-user/{userId}<br>
Admin can see studentlist: http://localhost:8080/admin/students<br>
Admin can see Teacherlist: http://localhost:8080/admin/teachers<br>

Teacher:(TeacherController)<br>
Teacher can see his profile: http://localhost:8080/teachers/{userId}/profile<br>
Teacher can edit profile: http://localhost:8080/teachers/{userId}/edit-profile<br>
Teacher can see reset password: http://localhost:8080/teachers/{userId}/reset-password<br>
Teacher can see student request: http://localhost:8080/teachers/{userId}/student-requests<br>
Teacher can accept student request: http://localhost:8080/teachers/{userId}/accept-request<br>
Teacher can dismiss student request: http://localhost:8080/teachers/{userId}/dismiss-request<br>
Teacher can see advisee student : http://localhost:8080/teachers/{userId}/advisor-students<br>
Teacher can remove anyone from advisee student: http://localhost:8080/teachers/{userId}/remove-student<br>

Student:(StudentController)<br>
Student can see his profile: http://localhost:8080/students/{userId}/profile<br>
Student can edit profile : http://localhost:8080/students/{userId}/edit-profile<br>
Student can see teacherlist : http://localhost:8080/students/active-teachers<br>
Student can see can request to be advisor: http://localhost:8080/students/send-advisor-request<br>
Student can reset password: http://localhost:8080/students/{userId}/reset-password<br>

Authentication:(AuthenticationController)<br>
Anyone can register : http://localhost:8080/auth/register<br>
Anyone can authenticate: http://localhost:8080/auth/authenticate<br>

For Authentication used JWT token based authentication.<br>

Sure, to run your Spring Boot project, you need to follow these steps:<br>

### Requirements:

1. **Java Development Kit (JDK):**
   - Install JDK 21. You can download it from [AdoptOpenJDK](https://adoptopenjdk.net/).

2. **Integrated Development Environment (IDE):**
   - Use an IDE for Java development. Popular choices include:
     - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
     - [Eclipse](https://www.eclipse.org/)
     - [Visual Studio Code](https://code.visualstudio.com/)

3. **PostgreSQL Database:**
   - Install PostgreSQL as your relational database. You can download it from [PostgreSQL Downloads](https://www.postgresql.org/download/).

### Steps:

1. **Clone or Download the Project:**
   - Clone the project from your version control system (e.g., Git) or download the ZIP file and extract it.

2. **Open Project in IDE:**
   - Open the project in your chosen IDE.

3. **Configure Database:**
   - Set up your PostgreSQL database. Create a database and update the `application.properties` or `application.yml` file in your project with the correct database connection details.

4. **Build and Run the Project:**
   - Build and run your Spring Boot application using your IDE or by running the following command in the project directory:
     ```
     ./mvnw spring-boot:run
     ```
     or for Windows:
     ```
     mvnw.cmd spring-boot:run
     ```

5. **Access the Application:**
   - Once the application is running, you can access it in your web browser at `http://localhost:8080` (assuming the default Spring Boot port).

6. **Test Endpoints:**
   - Use tools like Postman to test your API endpoints.

Make sure to install any dependencies or plugins required by your IDE for Lombok and Spring Boot development.

Note: If you encounter any issues during the build or run process



