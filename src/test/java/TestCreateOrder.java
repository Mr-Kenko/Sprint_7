import practikum.order.OrderInfo;
import practikum.order.OrderChecks;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class TestCreateOrder {
    private final List<String> colour;
    private OrderChecks orderSteps;
    int track;

    public TestCreateOrder(List<String> colour) {
        this.colour = colour;
    }
    @Before
    public void setUp(){
        orderSteps = new OrderChecks();
    }

    @After
    public void cleanUp(){
        orderSteps.cancellationOrder(track);
    }

    @Parameterized.Parameters
    public static Object[][] selectScooterColour(){
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("BLACK","GREY")},
                {List.of("GREY")},
                {List.of()}
        };
    }

    @DisplayName("Успешное создание нового заказа")
    @Description("Создание заказа с разными цветами")
    @Test
    public void createOrderWithDiffColours(){
        OrderInfo orderInfo = new OrderInfo(colour);
        ValidatableResponse response = orderSteps.successOrderCreate(orderInfo);
        track = response.extract().path("track");
        response.assertThat().statusCode(201).body("track", is(notNullValue()));
    }
}
