package io.testomat.web.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;


public class ProjectsPage extends BasePage {

    @Step
    public ProjectsPage isLoaded() {
        f("h2").shouldHave(Condition.text("Projects"));
        return this;
    }

    @Step
    public ProjectsPage clickOnNewProjectButton() {
        f("[href='/projects/new']").click();

        return this;
    }

    @Step
    public ProjectsPage fillProjectTitle(String projectTitle) {
        $("#project-form #project_title").setValue(projectTitle);

        return this;
    }

    @Step
    public ProjectsPage submitProjectCreation() {
        $("[name='commit']").click();
        return this;
    }

    public ProjectsPage createProjectLog() {
        $("");
        return this;
    }

    @Step
    public ProjectsPage checkProjectExist(String projectTitle) {
        return this;
    }

}
