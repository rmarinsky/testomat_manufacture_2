package io.testomat.api

import io.restassured.RestAssured.given
import io.testomat.SuiteDto
import io.testomat.api.HTTPStatusCodes.OK

class SuiteController {

    fun postSuite(targetSuite: SuiteDto): SuiteDto {
        return given()
            .baseUri("https://uat.testomat.io")
            .body(targetSuite)
            .post("/suites")
            .expectedStatusCodeIs(OK)
            .toReified()
    }


}
