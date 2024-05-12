package practikum.courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static practikum.Client.*;
import static io.restassured.RestAssured.given;

public class CourierClient {
    public static RequestSpecification requestSpec() {
        return given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL);
    }

    @Step("Регистрация нового курьера")
    public ValidatableResponse createCourier(Courier courierInfo) {
        return requestSpec()
                .body(courierInfo)
                .when()
                .post(POST_COURIER_CREATE)
                .then();
    }

    @Step("Логин курьера")
    public ValidatableResponse courierAuthorization(CourierLogin courierInfo) {
        return requestSpec()
                .body(courierInfo)
                .when()
                .post(POST_COURIER_LOGIN)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse courierDelete(int courierId) {
        return requestSpec()
                .when()
                .delete(DELETE_COURIER + courierId)
                .then();
    }
}
