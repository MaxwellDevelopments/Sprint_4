package ru.qa_scooter.tests.homepage;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Cookie;
import ru.qa_scooter.constants.Page;
import ru.qa_scooter.pom.ScooterHomePage;
import ru.qa_scooter.tests.configs.BaseTestAfterAndBeforeClass;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static ru.qa_scooter.tests.configs.Global.setCookie;

@RunWith(Parameterized.class)
public class TestFaqButtons extends BaseTestAfterAndBeforeClass {
    private final String faqHeaderText;
    private final String faqText;
    private final int faqButtonNumber;

    private static ScooterHomePage objScooterHomePage;

    @BeforeClass
    public static void openMainPage() {

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());
        setCookie("Cartoshka-legacy", "true", driver);
        driver.navigate().refresh();

        objScooterHomePage = new ScooterHomePage(driver);
        MatcherAssert.assertThat(
                "Main page hasn't been opened",
                objScooterHomePage.isPageOpened(),
                is(true));

        objScooterHomePage.goToFaq();
    }

    public TestFaqButtons(String faqHeaderText, String faqText, int faqButtonNumber) {
        this.faqHeaderText = faqHeaderText;
        this.faqText = faqText;
        this.faqButtonNumber = faqButtonNumber;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        };
    }

    @Test
    public void shouldTextInFaqBeLikeParametersAndButtonsBeClickable() {
        boolean isHidden = true;

        boolean hasErrors = false;
        String message = "";
        String reason = "";

        objScooterHomePage.clickOnFaqButtonByNumber(faqButtonNumber);

        String faqHeaderText = objScooterHomePage.getFaqHeaderTextByNumber(faqButtonNumber);
        try {
            reason = "Текст на кнопке не совпадает с переданным в параметре";
            MatcherAssert.assertThat(
                    reason,
                    faqHeaderText,
                    is(this.faqHeaderText));
        }
        catch (AssertionError e) {
            message += reason + "\n";
            hasErrors = true;
        }

        try {
            reason = "Выпадающий текст не появился по клику по кнопке в разделе FAQ";
            MatcherAssert.assertThat(
                    reason,
                    objScooterHomePage.isFaqTextVisibleByNumber(faqButtonNumber),
                    is(isHidden));
        }
        catch (AssertionError e) {
            message += reason + "\n";
            hasErrors = true;
        }

        String faqText = objScooterHomePage.getFaqTextByNumber(faqButtonNumber);

        try {
            reason = "Выпадающий текст не совпадает с переданным в параметре";
            MatcherAssert.assertThat(
                    reason,
                    faqText,
                    is(this.faqText));
        }
        catch (AssertionError e) {
            message += reason + "\n";
            hasErrors = true;
        }

        objScooterHomePage.clickOnFaqButtonByNumber(faqButtonNumber);
        try {
            reason = "Выпадающий текст не пропал по клику по кнопке в разделе FAQ";
            MatcherAssert.assertThat(
                    reason,
                    objScooterHomePage.isFaqTextVisibleByNumber(faqButtonNumber),
                    is(!isHidden));
        }
        catch (AssertionError e) {
            message += reason + "\n";
            hasErrors = true;
        }

        if (hasErrors) {
            throw new AssertionError(
                    "Тест завершён с ошибками:\n"
                            + message
                            + "Расположение: главная страница");
        }

    }



}
