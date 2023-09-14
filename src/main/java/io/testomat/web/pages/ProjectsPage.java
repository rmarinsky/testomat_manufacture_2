package io.testomat.web.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsPage extends BasePage {

    public ProjectsPage isLoaded() {
        f("h2").shouldHave(Condition.text("Projects"));
        return this;
    }

    public ProjectsPage clickOnNewProjectButton() {
        f("[href='/projects/new']").click();

        return this;
    }

    public ProjectsPage fillProjectTitle(String projectTitle) {
        $("#project-form #project_title").setValue(projectTitle);

        return this;
    }

    public ProjectsPage submitProjectCreation() {
        $("[name='commit']").click();
        return this;
    }

    public ProjectsPage checkProjectExist(String projectTitle) {
        return this;
    }

}
