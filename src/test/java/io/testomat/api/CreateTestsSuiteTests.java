package io.testomat.api;

import com.github.javafaker.Faker;
import io.testomat.api.controllers.AuthController;
import io.testomat.api.controllers.ProjectController;
import io.testomat.api.controllers.SuitesController;
import io.testomat.api.dtos.Attributes;
import io.testomat.api.dtos.DataItem;
import io.testomat.api.dtos.ProjectsDTO;
import io.testomat.api.dtos.SuiteDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateTestsSuiteTests {

    Faker faker = new Faker();

    @Test
    @DisplayName("Test name")
    void testName() {
        var jwtToken = new AuthController().loginUser("newromka@gmail.com", "p8qfCZ7Jv7pT!hh").getJwt();

        var projectTitle = ((ProjectsDTO) new ProjectController()
                .withToken(jwtToken)
                .getProjects().toObject()).getData().get(0).getAttributes().getTitle();

        new ProjectController()
                .withToken(jwtToken)
                .getProjects();

        var suitesController = new SuitesController()
                .withToken(jwtToken);

        var suite = (SuiteDto) suitesController.createTestSuite(
                projectTitle,
                generateSuiteDto()
        ).toObject();

        suitesController.getSuiteForProjectById(projectTitle, suite.getData().getId());

        suitesController.getAllSuitesForProject(projectTitle);
    }


    private SuiteDto generateSuiteDto() {
        return SuiteDto.builder()
                .data(DataItem.builder()
                        .type("suites")
                        .attributes(Attributes.builder()
                                .title(faker.book().title())
                                .description(faker.book().publisher())
                                .build())
                        .build())
                .build();
    }

}
