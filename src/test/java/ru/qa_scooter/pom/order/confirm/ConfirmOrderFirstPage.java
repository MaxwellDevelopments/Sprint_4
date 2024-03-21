package ru.qa_scooter.pom.order.confirm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmOrderFirstPage extends AbstractConfirmOrderPage {

    // Заголовок. Используется для определения открытия страницы
    private final By pageLocator = By.xpath("//*[text()='Хотите оформить заказ?']");
    // Кнопка подтверждения заказа
    private final By confirmButton = By.xpath("//*[contains(@class, 'Order_Modal')]//button[text()='Да']");

    public ConfirmOrderFirstPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return super.isPageOpened(pageLocator);
    }

    public void clickOnConfirmOrderButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(confirmButton));
        driver.findElement(confirmButton).click();
    }
    @Override
    public void nextPage() {
        clickOnConfirmOrderButton();
    }


}
