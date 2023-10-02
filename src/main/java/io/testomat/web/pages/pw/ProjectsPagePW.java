package io.testomat.web.pages.pw;


import io.testomat.web.common.pw.conditions.Condition;

import static io.testomat.web.common.pw.PlaywrightWrapper.$;


public class ProjectsPagePW extends BasePage{

    public ProjectsPagePW isLoaded() {
        f("h2").shouldHave(Condition.text("Projects"));
        return this;
    }

    public ProjectsPagePW clickOnNewProjectButton() {
        f("[href='/projects/new']").click();

        return this;
    }

    public ProjectsPagePW fillProjectTitle(String projectTitle) {
        $("#project-form #project_title").setValue(projectTitle);

        return this;
    }

    public ProjectsPagePW submitProjectCreation() {
        $("[name='commit']").click();
        return this;
    }

    public ProjectsPagePW checkProjectExist(String projectTitle) {
        return this;
    }

}
