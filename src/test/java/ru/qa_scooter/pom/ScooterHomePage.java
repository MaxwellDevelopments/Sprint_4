package ru.qa_scooter.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa_scooter.business.Order;

public class ScooterHomePage extends AbstractPage {

    // Кнопка "Яндекс" в лого
    private final By logoYandexButton = By.className("Header_LogoYandex__3TSOI");
    // Кнопка "Самокат в лого
    private final By logoScooterButton = By.className("Header_LogoScooter__3lsAR");
    // Заголовок на главной странице (используется для определения того, что страница открыта)
    private final By homeHeader = By.className("Home_Header__iJKdX");
    // Список кнопок "Заказать"
    private final By orderButtons = By.xpath("//button[text()='Заказать']");
    // Список часто задаваемых вопросов внизу страницы
    private final By faq = By.className("accordion");
    // Список кнопок в разделе часто задаваемых вопросов
    private final String faqHeaderId = "accordion__heading-";
    // Список с выпадающими ответами в разделе часто задаваемых вопросов
    private final String faqTextId = "accordion__panel-";



    private By getFaqHeaderLocator(int buttonNumber) {
        return By.id(faqHeaderId + buttonNumber);
    }

    private By getFaqTextLocator(int buttonNumber) {
        return By.id(faqTextId + buttonNumber);
    }

    public ScooterHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogoOnYandex() {
        clickOnButton(logoYandexButton);
    }

    public void clickLogoOnScooter() {
        clickOnButton(logoScooterButton);
    }


    @Override
    public boolean isPageOpened() {
        return super.isPageOpened() && driver.findElement(homeHeader).isDisplayed();
    }

    public void clickOnOrderButton(int whichButton) {
        WebElement button = driver.findElements(orderButtons).get(whichButton);
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.elementToBeClickable(button));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    public void searchForOrder(Order order) {
        clickOnOrderStatusButton();
        writeInSearchInput(order);
        clickOnGoButton();
    }

    public void goToFaq() {
        WebElement faqWebElement = driver.findElement(faq);
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfElementLocated(faq));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqWebElement);
    }

    public void clickOnFaqButtonByNumber(int buttonNumber) {
        clickOnButton(getFaqHeaderLocator(buttonNumber));
    }

    public String getFaqHeaderTextByNumber(int buttonNumber) {
        return driver.findElement(getFaqHeaderLocator(buttonNumber)).getText();
    }

    public String getFaqTextByNumber(int buttonNumber) {
        return driver.findElement(getFaqTextLocator(buttonNumber)).getText();
    }

    public boolean isFaqTextVisibleByNumber(int buttonNumber) {
        return driver.findElement(getFaqTextLocator(buttonNumber)).isDisplayed();
    }



}
