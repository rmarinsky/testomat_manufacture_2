package io.testomat.api.controllers;

import io.testomat.api.common.ResponseDecorator;
import io.testomat.api.dtos.SuiteDto;
import io.testomat.api.dtos.SuitesDto;

public class SuitesController extends BaseController<SuitesController> {

    public ResponseDecorator createTestSuite(String targetProjectId, Object suiteDTO) {
        return new ResponseDecorator(
                baseClient()
                        .body(suiteDTO)
                        .post("/{targetProject}/suites", targetProjectId),
                200,
                SuiteDto.class
        );
    }

    public SuitesDto getAllSuitesForProject(String projectName) {
        return baseClient()
                .get("/{projectName}/suites", projectName)
                .as(SuitesDto.class);
    }

    public SuiteDto getSuiteForProjectById(String projectName, String suiteId) {
        return baseClient()
                .get("/{projectName}/suites/{suiteId}", projectName, suiteId)
                .as(SuiteDto.class);
    }

}
