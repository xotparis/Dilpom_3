package edu.praktikum.diplom3.helpers;

import edu.praktikum.diplom3.pages.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static edu.praktikum.diplom3.CONSTANTS.CONSTANTS.*;
import static edu.praktikum.diplom3.CONSTANTS.CONSTANTS.LOGIN_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserClient {
    protected String accessToken;

    public Response createUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(CREATE_ENDPOINT);
    }

    public static void deleteUser(String accessToken) {
        if (accessToken != null) {
            given()
                    .header("Content-Type", "json")
                    .header("Authorization", accessToken)
                    .when()
                    .delete(DELETE_ENDPOINT)
                    .then()
                    .assertThat()
                    .statusCode(SC_ACCEPTED)
                    .body("success", equalTo(true))
                    .body("message", equalTo("User successfully removed"));
        }
    }

    public Response login(User user) {
        RestAssured.baseURI = URL;
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();

    }
}
