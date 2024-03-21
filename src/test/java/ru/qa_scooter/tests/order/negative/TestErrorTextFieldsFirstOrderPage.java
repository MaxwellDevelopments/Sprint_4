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
import ru.qa_scooter.pom.order.making.FirstOrderPage;
import ru.qa_scooter.tests.configs.BaseTestAfterAndBeforeClass;

import static org.hamcrest.CoreMatchers.is;


@RunWith(Parameterized.class)
public class TestErrorTextFieldsFirstOrderPage extends BaseTestAfterAndBeforeClass {

    // поле ошибки
    private final String firstNameError = "Введите корректное имя";
    private final String lastNameError = "Введите корректную фамилию";
    private final String addressError = "Введите корректный адрес";
    private final String subwayStationError = "Выберите станцию";
    private final String phoneNumberError = "Введите корректный номер";
    private final Order order;

    private static FirstOrderPage objFirstOrderPage;


    public TestErrorTextFieldsFirstOrderPage(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[] getOrder() {
        return new Object[] {
                Order.createFirstPageOrder("", "", "",
                        null, ""),
                Order.createFirstPageOrder("asd", "asd", "asd",
                        null, "asd")
        };
    }


    @BeforeClass
    public static void openFirstOrderPage() {

        driver.get(Page.HOME_PAGE_SCOOTER.getLink());
        MatcherAssert.assertThat(
                "Main page hasn't been opened",
                new ScooterHomePage(driver).isPageOpened(),
                is(true));

        new ScooterHomePage(driver).clickOnOrderButton(0);
        objFirstOrderPage = new FirstOrderPage(driver);
        MatcherAssert.assertThat(
                "First order page hasn't been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

    }

    @Before
    public void clearInputs() {
        objFirstOrderPage.clearAllInputs();
    }

    @Test
    public void shouldErrorFieldFirstNameBeVisible() {
        boolean expected = true;
        boolean actual;
        String message = "Не появился текст с ошибкой у поля имя при заказе самоката\n" +
                "Расположение: первая страница заказа";


        objFirstOrderPage.setFirstName(order.getFirstName());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getWebElemVisibleByText(objFirstOrderPage.getFirstNameError());

        MatcherAssert.assertThat(
                message,
                actual,
                is(expected));
    }

    @Test
    public void shouldErrorTextFirstNameBeLikeParameter() {
        String actual;
        String message = "Неправильный текст ошибки у поля имя при заказе самоката\n" +
                "Расположение: первая страница заказа";

        objFirstOrderPage.setFirstName(order.getFirstName());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getFirstNameError();

        MatcherAssert.assertThat(
                message,
                actual,
                is(firstNameError));
    }

    @Test
    public void shouldErrorFieldLastNameBeVisible() {
        boolean expected = true;
        boolean actual;
        String message = "Не появился текст с ошибкой у поля фамилия при заказе самоката\n" +
                "Расположение: первая страница заказа";


        objFirstOrderPage.setLastName(order.getLastName());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getWebElemVisibleByText(objFirstOrderPage.getLastNameError());

        MatcherAssert.assertThat(
                message,
                actual,
                is(expected));
    }

    @Test
    public void shouldErrorTextLastNameBeLikeParameter() {
        String actual;
        String message = "Неправильный текст ошибки у поля фамилия при заказе самоката\n" +
                "Расположение: первая страница заказа";

        objFirstOrderPage.setLastName(order.getLastName());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getLastNameError();

        MatcherAssert.assertThat(
                message,
                actual,
                is(lastNameError));
    }


    @Test
    public void shouldErrorFieldAddressBeVisible() {
        boolean expected = true;
        boolean actual;
        String message = "Не появился текст с ошибкой у поля адрес при заказе самоката\n" +
                "Расположение: первая страница заказа";


        objFirstOrderPage.setAddress(order.getAddress());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getWebElemVisibleByText(objFirstOrderPage.getAddressError());

        MatcherAssert.assertThat(
                message,
                actual,
                is(expected));
    }

    @Test
    public void shouldErrorTextAddressBeLikeParameter() {
        String actual;
        String message = "Неправильный текст ошибки у поля адрес при заказе самоката\n" +
                "Расположение: первая страница заказа";

        objFirstOrderPage.setAddress(order.getAddress());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getAddressError();

        MatcherAssert.assertThat(
                message,
                actual,
                is(addressError));
    }

    @Test
    public void shouldErrorFieldSubwayStationBeVisible() {
        boolean expected = true;
        boolean actual;
        String message = "Не появился текст с ошибкой у поля метро при заказе самоката\n" +
                "Расположение: первая страница заказа";

        objFirstOrderPage.setSubwayStation(order.getSubwayStation());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getWebElemVisibleByText(objFirstOrderPage.getSubwayStationError());

        MatcherAssert.assertThat(
                message,
                actual,
                is(expected));
    }

    @Test
    public void shouldErrorTextSubwayStationBeLikeParameter() {
        String actual;
        String message = "Неправильный текст ошибки у поля адрес при заказе самоката\n" +
                "Расположение: первая страница заказа";

        objFirstOrderPage.setSubwayStation(order.getSubwayStation());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getSubwayStationError();

        MatcherAssert.assertThat(
                message,
                actual,
                is(subwayStationError));
    }

    @Test
    public void shouldErrorFieldPhoneNumberBeVisible() {
        boolean expected = true;
        boolean actual;
        String message = "Не появился текст с ошибкой у поля номер телефона при заказе самоката\n" +
                "Расположение: первая страница заказа";


        objFirstOrderPage.setPhoneNumber(order.getPhoneNumber());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getWebElemVisibleByText(objFirstOrderPage.getPhoneNumberError());

        MatcherAssert.assertThat(
                message,
                actual,
                is(expected));
    }

    @Test
    public void shouldErrorTextPhoneNumberBeLikeParameter() {
        String actual;
        String message = "Неправильный текст ошибки у поля номер телефона при заказе самоката\n" +
                "Расположение: первая страница заказа";

        objFirstOrderPage.setPhoneNumber(order.getAddress());
        objFirstOrderPage.clickOnNextButton();
        MatcherAssert.assertThat(
                "Second order page has been opened",
                objFirstOrderPage.isPageOpened(),
                is(true));

        actual = objFirstOrderPage.getPhoneNumberError();

        MatcherAssert.assertThat(
                message,
                actual,
                is(phoneNumberError));
    }

}
