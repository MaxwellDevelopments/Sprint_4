package ru.qa_scooter.tests.order.negative;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.qa_scooter.business.Order;
import ru.qa_scooter.constants.Page;
import ru.qa_scooter.pom.ScooterHomePage;
import ru.qa_scooter.pom.order.confirm.ConfirmOrderFirstPage;
import ru.qa_scooter.pom.order.making.FirstOrderPage;
import ru.qa_scooter.pom.order.making.SecondOrderPage;
import ru.qa_scooter.tests.configs.BaseTestAfterAndBeforeClass;

import static org.hamcrest.CoreMatchers.is;


@RunWith(Parameterized.class)
public class TestErrorTextFieldsSecondOrderPage extends BaseTestAfterAndBeforeClass {

    // поля текста ошибок ошибки
    // это мои предположения. на странице вообще нет классов для ошибок у даты и срока аренды
    // хотя поля обязательные и по идее при нажатии на кнопку оформления заказа должны подсвечиваться
    private final String dateError = "Выберите дату";
    private final String rentError = "Выберите срок аренды";
    // только для комментария нашёл ошибку
    private final String commentError = "Введите корректный комментарий";

    private final Order order;

    private final boolean isCommentErrorVisible;

    private static SecondOrderPage objSecondOrderPage;

    // это повторяющийся код, я его вынес
    private void confirmPageOrderIsNotOpened() {
        MatcherAssert.assertThat(
                "Confirm order page has been opened",
                new ConfirmOrderFirstPage(driver).isPageOpened(),
                is(false));
    }
    
    public TestErrorTextFieldsSecondOrderPage(Order order, boolean isCommentErrorVisible) {
        this.order = order;
        this.isCommentErrorVisible = isCommentErrorVisible;
    }

    @Parameterized.Parameters
    public static Object[][] getOrder() {

        return new Object[][] {
                {new Order(), false},
                {Order.createSecondPageOrder(null, null, null, "fahfjas"), true}
        };
    }


    @BeforeClass
    public static void openSecondOrderPage() {

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());
        MatcherAssert.assertThat(
                "Main page hasn't been opened",
                new ScooterHomePage(driver).isPageOpened(),
                is(true));

        new ScooterHomePage(driver).clickOnOrderButton(0);
        FirstOrderPage objFirstOrderPage = new FirstOrderPage(driver);
        MatcherAssert.assertThat(
                "First order page hasn't been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));
        
        objFirstOrderPage.setFirstName("Оленёнок");
        objFirstOrderPage.setLastName("Бэмби");
        objFirstOrderPage.setAddress("Антарктида");
        objFirstOrderPage.setSubwayStation("Чистые пруды");
        objFirstOrderPage.setPhoneNumber("+79999999999");
        objFirstOrderPage.clickOnNextButton();


        objSecondOrderPage = new SecondOrderPage(driver);
        MatcherAssert.assertThat(
                "Second order page hasn't been opened",
                objSecondOrderPage.isPageOpened(),
                is(true));
    }

    @Before
    public void clearInputs() {
        objSecondOrderPage.clearAllInputs();
    }

    @Test
    public void shouldErrorFieldCommentBeVisible() {
        boolean expected = isCommentErrorVisible;
        boolean actual;
        String message = "Не появился текст с ошибкой у поля комментарий при заказе самоката\n" +
                "Расположение: вторая страница заказа";


        objSecondOrderPage.setComment(order.getComment());
        objSecondOrderPage.clickOnNextButton();
        confirmPageOrderIsNotOpened();

        actual = objSecondOrderPage.getWebElemVisibleByText(objSecondOrderPage.getCommentError());

        MatcherAssert.assertThat(
                message,
                actual,
                is(expected));
    }

    @Test
    public void shouldErrorTextCommentBeLikeParameter() {
        String actual;
        String message = "Неправильный текст ошибки у поля комментарий при заказе самоката\n" +
                "Расположение: вторая страница заказа";

        objSecondOrderPage.setComment(order.getComment());
        objSecondOrderPage.clickOnNextButton();
        confirmPageOrderIsNotOpened();

        actual = objSecondOrderPage.getCommentError();

        MatcherAssert.assertThat(
                message,
                actual,
                is(commentError));
    }


}
