package ru.qa_scooter.pom.order.confirm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmOrderSecondPage extends AbstractConfirmOrderPage {
    // Локатор заголовка для определения открытости страницы
    private final By pageLocator = By.xpath("//*[text()='Заказ оформлен']");
    // Кнопка "Посмотреть статус"
    private final By watchStatusButton = By.xpath("//*[contains(@class, 'Order_Modal')]//button[text()='Посмотреть статус']");

    public ConfirmOrderSecondPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void nextPage() {
        clickOnWatchStatusButton();
    }

    @Override
    public boolean isPageOpened() {
        return super.isPageOpened(pageLocator);
    }

    public void clickOnWatchStatusButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(watchStatusButton));
        driver.findElement(watchStatusButton).click();
    }
}
