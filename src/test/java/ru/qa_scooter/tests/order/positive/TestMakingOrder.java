package ru.qa_scooter.tests.order.positive;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.qa_scooter.business.Order;
import ru.qa_scooter.pom.AbstractPage;
import ru.qa_scooter.pom.ScooterHomePage;
import ru.qa_scooter.constants.Color;
import ru.qa_scooter.constants.Page;
import ru.qa_scooter.constants.RentTime;
import ru.qa_scooter.pom.order.info.ExistedOrderPage;
import ru.qa_scooter.pom.order.confirm.AbstractConfirmOrderPage;
import ru.qa_scooter.pom.order.confirm.ConfirmOrderFirstPage;
import ru.qa_scooter.pom.order.confirm.ConfirmOrderSecondPage;
import ru.qa_scooter.pom.order.making.AbstractOrderPage;
import ru.qa_scooter.pom.order.making.FirstOrderPage;
import ru.qa_scooter.pom.order.making.SecondOrderPage;
import ru.qa_scooter.tests.configs.BaseTest;
import ru.qa_scooter.tests.configs.Global;

import static org.hamcrest.CoreMatchers.is;


@RunWith(Parameterized.class)
public class TestMakingOrder extends BaseTest {

    private final Order order;

    public TestMakingOrder(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[] getOrderData() {
        return new Object[] {
                new Order("Сергей", "Красильников",
                        "Комсомольская, 10", "Маяковская",
                        "+79527546666",
                        Global.today.plusMonths(1),
                        RentTime.ONE,
                        Color.BLACK,
                        ""
                ),
                new Order("Саша", "Красильникова",
                        "Малая грузинская, 15", "Красносельская",
                        "+79527666666",
                        Global.today.plusDays(7),
                        RentTime.SEVEN,
                        Color.GREY,
                        "С цветами"
                ),
                new Order("Антон", "Чёрный",
                        "Аркадия иванова", "Коломенская",
                        "+79258889389",
                        Global.today.plusMonths(2),
                        RentTime.TWO,
                        null,
                        null
                )
        };
    }

    @Test
    public void shouldMakeOrder() {
        String message = "The order hasn't been processed";
        boolean actual;
        boolean expected = true;

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.waitUntilPageOpened(3);

        objScooterHomePage.clickOnOrderButton(0);

        AbstractOrderPage objFirstOrderPage = new FirstOrderPage(driver);
        MatcherAssert.assertThat("The first order page hasn't been opened",
                objFirstOrderPage.isPageOpened(),
                is(expected));

        objFirstOrderPage.fillPage(this.order);
        objFirstOrderPage.clickOnNextButton();

        AbstractOrderPage objSecondOrderPage = new SecondOrderPage(driver);
        MatcherAssert.assertThat("The second order page hasn't been opened",
                objSecondOrderPage.isPageOpened(),
                is(expected));

        objSecondOrderPage.fillPage(this.order);
        objSecondOrderPage.clickOnNextButton();

        AbstractConfirmOrderPage objConfirmOrderFirstPage = new ConfirmOrderFirstPage(driver);
        MatcherAssert.assertThat("The first confirm order page hasn't been opened",
                objConfirmOrderFirstPage.isPageOpened(),
                is(expected));
        objConfirmOrderFirstPage.nextPage();

        AbstractConfirmOrderPage objConfirmOrderSecondPage = new ConfirmOrderSecondPage(driver);
        MatcherAssert.assertThat("The second confirm order page hasn't been opened",
                objConfirmOrderSecondPage.isPageOpened(),
                is(expected));
        objConfirmOrderSecondPage.nextPage();

        AbstractPage successOrderPage = new ExistedOrderPage(driver);
        MatcherAssert.assertThat(message,
                successOrderPage.isPageOpened(),
                is(expected));
    }
}


