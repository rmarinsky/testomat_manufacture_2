package io.testomat.api.common;

import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;

import static io.testomat.api.common.BindingExceptionHandler.catchResponseException;

public class ResponseDecorator<T> {

    private Response targetResponse;
    private int expectedDefaultStatusCode;
    private Class<T> targetClass;

    public ResponseDecorator(Response targetResponse, int expectedDefaultStatusCode, Class<T> targetClass) {
        this.targetResponse = targetResponse;
        this.expectedDefaultStatusCode = expectedDefaultStatusCode;
        this.targetClass = targetClass;
    }

    public ResponseDecorator assertStatusCode(int statusCode) {
        Assertions.assertThat(targetResponse.statusCode()).withFailMessage(
                String.format(
                        "Expected status code %s, but was %s \nresponse body was %s",
                        statusCode,
                        targetResponse.statusCode(),
                        targetResponse.body().asPrettyString()
                )
        ).isEqualTo(statusCode);
        return this;
    }

    @SneakyThrows
    public <T> T toObject() {
        Assertions.assertThat(targetResponse.statusCode())
                .withFailMessage(String.format(
                        "Expected status code %s, but was %s \nresponse body was %s",
                        expectedDefaultStatusCode,
                        targetResponse.statusCode(),
                        targetResponse.body().asPrettyString()
                ))
                .isEqualTo(expectedDefaultStatusCode);
        try {
            return (T) targetResponse.as(this.targetClass);
        } catch (Exception e) {
            throw /*new RuntimeException(e.getMessage())*/catchResponseException(e, targetClass);
        }
    }

    public <T> T as(Class<T> targetClass) {
        return targetResponse.as(targetClass);
    }

    public ResponseDecorator targetResponse(Response targetResponse) {
        this.targetResponse = targetResponse;
        return this;
    }

    public ResponseDecorator expectedDefaultStatusCode(int expectedDefaultStatusCode) {
        this.expectedDefaultStatusCode = expectedDefaultStatusCode;
        return this;
    }

    public ResponseDecorator targetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
        return this;
    }

}
