package ru.qa_scooter.tests.order.positive;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.qa_scooter.business.Order;
import ru.qa_scooter.constants.Page;
import ru.qa_scooter.pom.AbstractPage;
import ru.qa_scooter.pom.ScooterHomePage;
import ru.qa_scooter.pom.order.info.NotExistedOrderPage;
import ru.qa_scooter.pom.order.info.ExistedOrderPage;
import ru.qa_scooter.tests.configs.BaseTest;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class TestSearchOrder extends BaseTest {

    private final Order order;
    private final boolean isOrderExist;

    public TestSearchOrder(Order order, boolean isOrderExist) {
        this.order = order;
        this.isOrderExist = isOrderExist;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {new Order("999999999"), false},
                {new Order("11111111111111"), false},
                {new Order("00000000000000"), false},
                {new Order("51920592158546546"), false},
                {new Order("896924"), true}
        };
    }

    @Test
    public void shouldSearchOrderAndOpenOrderPage() {
        boolean expected = true;
        boolean result;
        String message = "Page with order info hasn't been opened";

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        MatcherAssert.assertThat(
                "Main page hasn't been opened",
                objScooterHomePage.isPageOpened(),
                is(expected));
        objScooterHomePage.searchForOrder(order);

        // если заказ существует, то тест тоже отработает
        AbstractPage orderInfoPage = isOrderExist ? new ExistedOrderPage(driver) : new NotExistedOrderPage(driver);
        result = orderInfoPage.isPageOpened();
        MatcherAssert.assertThat(message, result, is(expected));
    }
}
