package ru.qa_scooter.tests.homepage;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa_scooter.pom.ScooterHomePage;
import ru.qa_scooter.constants.Page;
import ru.qa_scooter.tests.configs.BaseTest;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;


public class TestLogo extends BaseTest {

    @Test
    public void testGoToYandexPageFromScooterLogo() {
        String expected = Page.DZEN_YANDEX.getTitle();
        String message = "Yandex.Dzen main page hasn't been opened";
        String result;

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());
        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);
        objScooterHomePage.waitUntilPageOpened(3);

        objScooterHomePage.clickLogoOnYandex();

        Set<String> handles = driver.getWindowHandles();
        // Переключаемся на последнюю (новую) вкладку
        for (String handle : handles) {
            driver.switchTo().window(handle);
        }
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.titleIs(expected));
        result = driver.getTitle();

        MatcherAssert.assertThat(message, result, is(expected));
    }

    @Test
    public void testGoToScooterPageFromScooterLogo() {
        String message = "Yandex.Scooter main page hasn't been opened";
        boolean expected = true;
        boolean result;

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());

        ScooterHomePage objScooterHomePage = new ScooterHomePage(driver);

        objScooterHomePage.waitUntilPageOpened(3);
        objScooterHomePage.clickLogoOnScooter();
        objScooterHomePage.waitUntilPageOpened(3);

        result = objScooterHomePage.isPageOpened();

        MatcherAssert.assertThat(message, result, is(expected)); // здесь бы по идее тоже искать по title, но title undefined, так что хз
    }

}
