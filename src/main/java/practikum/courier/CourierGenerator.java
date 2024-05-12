package practikum.courier;

import io.qameta.allure.Step;
import net.datafaker.Faker;

public class CourierGenerator {
    static Faker faker = new Faker();

    @Step("Создание курьера со случайными данными")
    public Courier createCourierWithRandomData() {
        return new Courier(
                faker.name().name(),
                faker.internet().password(),
                faker.name().firstName()
        );
    }
}
