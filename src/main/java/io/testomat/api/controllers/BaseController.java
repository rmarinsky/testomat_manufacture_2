package io.testomat.api.controllers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.testomat.api.common.LogRequestFilter;

import static io.restassured.RestAssured.given;

public abstract class BaseController<T> {

    private String authToken;

    public T withToken(String authToken) {
        this.authToken = authToken;
        return (T) this;
    }

    public void cleanToken() {
        this.authToken = null;
    }

    public RequestSpecification baseClient() {
        var reqSpec = given()
                .baseUri("https://app.testomat.io")
                .basePath("/api")
                .filters(new LogRequestFilter(), new AllureRestAssured())
                .contentType(ContentType.JSON);
        if (authToken != null) {
            reqSpec.header("Authorization", authToken);
        }

        return reqSpec;
    }

}
