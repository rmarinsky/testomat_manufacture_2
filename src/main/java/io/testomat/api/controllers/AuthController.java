package io.testomat.api.controllers;

import io.restassured.http.ContentType;
import io.testomat.api.dtos.LoginDto;

public class AuthController extends BaseController<AuthController> {

    public LoginDto loginUser(String email, String password) {
        return baseClient()
                .contentType(ContentType.URLENC)
                .formParams("email", email, "password", password)
                .post("/login")
                .as(LoginDto.class);
    }

}
