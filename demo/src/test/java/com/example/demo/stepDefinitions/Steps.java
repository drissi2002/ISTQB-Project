package com.example.demo.stepDefinitions;

import com.example.demo.entities.Student;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Objects;

public class Steps {
    private final String BASE_URL = "http://localhost/api/v1";
    private final String STUDENT_NAME = "Iheb";
    private final String STUDENT_EMAIL = "iheb@gmail.com";
    private final String STUDENT_GENDER = "MALE";

    private static Response response;
    private static String jsonString;
    private static String studentId;

    @When("I query the API for a list of students")
    public void queryListOfStudents() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/students");
    }

    @Then("Then a list of students is returned")
    public void listOfStudentsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I add a new student to the list of students")
    public void addNewStudent() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        response = request.body("{ \"name\": \"" + STUDENT_NAME + "\", " +
                        "\"email\": \"" + STUDENT_EMAIL + "\", " +
                        "\"gender\": \"" + STUDENT_GENDER + "\" }")
                .post("/students");

        jsonString = response.asString();
        studentId = JsonPath.from(jsonString).get("id");
    }

    @Then("The student is added")
    public void studentIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Given("The student exists in the list of students")
    public void studentExists() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/students");

        jsonString = response.asString();
        List<Student> students = JsonPath.from(jsonString).getList("$", Student.class);

        Assert.assertTrue(students.stream().anyMatch(student -> Objects.equals(String.valueOf(student.getId()), studentId)));
    }

    @When("I delete the student from the list of students")
    public void deleteStudent() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        response = request.delete("/students/{0}", studentId);
    }

    @Then("The student is deleted")
    public void studentIsDeleted() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/students");

        jsonString = response.asString();
        List<Student> students = JsonPath.from(jsonString).getList("$", Student.class);

        Assert.assertFalse(students.stream().anyMatch(student -> Objects.equals(String.valueOf(student.getId()), studentId)));
    }
}
