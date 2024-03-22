package ru.qa_scooter.pom.order.making;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa_scooter.business.Order;

public class FirstOrderPage extends AbstractOrderPage {

    // локатор страницы для определения открытия
    private final By pageLocator = By.xpath("//*[contains(text(), 'Для кого самокат')]");
    // инпут для ввода имени
    private final By firstNameInput = By.xpath("//*[contains(@placeholder, 'Имя')]");
    // инпут для ввода фамилии
    private final By lastNameInput = By.xpath("//*[contains(@placeholder, 'Фамилия')]");
    // инпут для ввода адреса
    private final By addressInput = By.xpath("//*[contains(@placeholder, 'Адрес')]");
    // инпут-кнопка для метро
    private final By subwayStationInput = By.xpath("//*[contains(@placeholder, 'метро')]");
    // у метро какая-то слишком особенная ошибка. Я пытался унифицировать поиски локаторов ошибок, но получаются
    // какие-то слишком уродские выражения
    // пришёл к выводу, что лучше описать его отдельно, раз он такой особенный
    private final By subwayStationError = By.xpath("//div[contains(@class, 'Order_MetroError')]");
    // первая кнопка из выпадающего списка
    private final By subwayStationSelectFirst = By.xpath("//li[@class='select-search__row' and position() = 1]/button");
    // инпут для ввода номера
    private final By phoneNumberInput = By.xpath("//*[contains(@placeholder, 'Телефон')]");
    // кнопка для перехода на следующая страницу заказа
    private final By nextButton = By.xpath("//button[text()='Далее']");

    public FirstOrderPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isPageOpened() {
        return super.isPageOpened() && super.isPageOpened(pageLocator);
    }


    public void setFirstName(String firstName) {
        if (firstName == null) {
            return;
        }
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public String getFirstNameError() {
        return getFieldErrorText(firstNameInput);
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            return;
        }
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public String getLastNameError() {
        return getFieldErrorText(lastNameInput);
    }

    public void setAddress(String address) {
        if (address == null) {
            return;
        }
        driver.findElement(addressInput).sendKeys(address);
    }

    public String getAddressError() {
        return getFieldErrorText(addressInput);
    }

    public void setSubwayStation(String subwayStation) {
        if (subwayStation == null) {
            return;
        }

        driver.findElement(subwayStationInput).sendKeys(subwayStation);

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(subwayStationSelectFirst));

        driver.findElement(subwayStationSelectFirst).click();

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.textToBePresentInElementValue(subwayStationInput, subwayStation));
    }

    public String getSubwayStationError() {
        return driver.findElement(subwayStationError).getText();
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return;
        }
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public String getPhoneNumberError() {
        return getFieldErrorText(phoneNumberInput);
    }

    @Override
    public void fillPage(Order order) {
        waitForAllPageInputsLoad();

        setFirstName(order.getFirstName());
        setLastName(order.getLastName());
        setAddress(order.getAddress());
        setSubwayStation(order.getSubwayStation());
        setPhoneNumber(order.getPhoneNumber());
    }

    public void clickOnNextButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
    }
}
