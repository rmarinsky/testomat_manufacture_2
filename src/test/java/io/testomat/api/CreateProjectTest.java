package io.testomat.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateProjectTest {

    @Test
    @DisplayName("Login user and create project")
    void loginUserAndCreateProject() {
        String projectname = "manufacture-testomatio";
        var testomatSpecification = RestAssured.given()
                .baseUri("https://app.testomat.io")
                .log().all()
                .basePath("/api");

        String token = testomatSpecification
                .formParams(
                        "email", "newromka@gmail.com",
                        "password", "p8qfCZ7Jv7pT!hh"
                )
                .post("/login")
                .jsonPath()
                .getString("jwt");


       /*testomatSpecification
                .contentType(ContentType.URLENC)
                .header("Authorization", token)
                .formParams(
                        "project[lang]", "js",
                        "project[title]", new Faker().book().title(),
                        "commit", "Create"
                )
                .post("/projects")
                .prettyPeek();
*/

        //&project%5Blang%5D=js&project%5Btitle%5D=Popopo&project%5Burl%5D=&commit=Create
        testomatSpecification
                .header("Authorization", token)
                .get("/{projectName}/suites", projectname)
                .prettyPeek();
    }

}
