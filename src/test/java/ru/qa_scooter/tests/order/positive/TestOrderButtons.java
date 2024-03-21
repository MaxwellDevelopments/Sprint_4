package ru.qa_scooter.tests.order.positive;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import ru.qa_scooter.pom.ScooterHomePage;
import ru.qa_scooter.constants.Page;
import ru.qa_scooter.pom.order.making.FirstOrderPage;
import ru.qa_scooter.tests.configs.BaseTest;

import static org.hamcrest.CoreMatchers.is;

public class TestOrderButtons extends BaseTest {

    @Test
    public void shouldGoToOrderPageFromHeaderOrderButton() {
        String message = "Order button from main page header has not opened order page";
        boolean actual;
        boolean expected = true;

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.waitUntilPageOpened(3);

        objScooterHomePage.clickOnOrderButton(0);

        FirstOrderPage objFirstOrderPage = new FirstOrderPage(driver);
        actual = objFirstOrderPage.isPageOpened();

        MatcherAssert.assertThat(message, actual, is(expected));


    }

    @Test
    public void shouldGoToOrderPageFromRoadMapOrderButton() {
        String message = "Order button from main page roadmap has not opened order page";
        boolean actual;
        boolean expected = true;

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.waitUntilPageOpened(3);

        objScooterHomePage.clickOnOrderButton(1);

        FirstOrderPage objFirstOrderPage = new FirstOrderPage(driver);
        actual = objFirstOrderPage.isPageOpened();

        MatcherAssert.assertThat(message, actual, is(expected));
    }
}
