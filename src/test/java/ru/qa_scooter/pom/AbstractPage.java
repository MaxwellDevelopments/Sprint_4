package ru.qa_scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa_scooter.business.Order;

public abstract class AbstractPage {
    protected final WebDriver driver;

    // Класс шапки. используется для идентификации при открытии страницы
    private final By pageLocator = By.className("Header_Header__214zg");
    // Кнопка "Да" на панели принятия кук
    private final By cookieButton = By.xpath("//button[contains(@class, 'Cookie')]");

    // Кнопка "Cтатус заказа" в хедере (на данный момент есть у всех страниц)
    private final By orderStatusButton = By.xpath("//*[contains(@class, 'Header')]//button[text()='Статус заказа']");
    // Инпут для ввода номера заказа в хедере ((на данный момент есть у всех страниц)
    private final By orderSearchInput = By.xpath("//input[@placeholder='Введите номер заказа']");
    // Кнопка "Go!" в хедере. Выполняет роль старта поиска заказа по номеру
    private final By goButton = By.xpath("//button[text()='Go!']");
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilPageOpened(long seconds) {
        new WebDriverWait(driver, seconds).
                until(ExpectedConditions.visibilityOfElementLocated(pageLocator));
    }

    public boolean isPageOpened() {
        return isPageOpened(pageLocator);
    }

    protected boolean isPageOpened(By pageLocator) {
        boolean result;
        try {
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(pageLocator));
            result = true;
        }
        catch (TimeoutException e) {
            result = false;
        }
        return result;
    }

    protected void clickOnButton(By locator) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public void clickOnOrderStatusButton() {
        clickOnButton(orderStatusButton);
    }

    public void writeInSearchInput(Order order) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderSearchInput));
        driver.findElement(orderSearchInput).sendKeys(order.getOrderNumber());
    }

    public void clickOnGoButton() {
        clickOnButton(goButton);
    }
}
