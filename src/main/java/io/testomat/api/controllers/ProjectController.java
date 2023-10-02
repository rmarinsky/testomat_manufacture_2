package io.testomat.api.controllers;

import io.testomat.api.common.ResponseDecorator;
import io.testomat.api.dtos.ProjectsDTO;

public class ProjectController extends BaseController<ProjectController> {

    public ResponseDecorator getProjects() {
        return new ResponseDecorator(
                baseClient().get("/projects"),
                200,
                ProjectsDTO.class
        );
    }

}
