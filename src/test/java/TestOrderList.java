import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import practikum.order.OrdersListAssert;
import practikum.order.OrderChecks;
public class TestOrderList {
    private OrderChecks orderCheck;

    private final OrdersListAssert orderAssertVoid = new OrdersListAssert();

    @Before
    public void setUp(){
        orderCheck = new OrderChecks();
    }

    @DisplayName("Проверка получения списка заказов")
    @Description("Тест на успешное получение списка заказов")
    @Test
    public void testGetOrdersList(){
        ValidatableResponse response = orderCheck.getOrdersList();
        orderAssertVoid.successGetOrdersList(response);
    }
}
