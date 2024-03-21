package ru.qa_scooter.pom.order.info;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.qa_scooter.pom.AbstractPage;

public class NotExistedOrderPage extends AbstractPage {
    // локатор для определения открытия страницы
    private final By pageLocator = By.xpath("//*[contains(@class, 'NotFound')]");
    public NotExistedOrderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return super.isPageOpened() && super.isPageOpened(pageLocator);
    }

}