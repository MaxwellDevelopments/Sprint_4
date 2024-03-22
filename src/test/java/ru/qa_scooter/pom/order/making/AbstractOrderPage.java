package ru.qa_scooter.pom.order.making;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa_scooter.business.Order;
import ru.qa_scooter.pom.AbstractPage;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractOrderPage extends AbstractPage {
    // все инпуты на страницах заказа
    private final By orderContentInputs = By.xpath("//div[@class='Order_Content__bmtHS']//input");

    public AbstractOrderPage(WebDriver driver) {
        super(driver);
    }

    protected void waitForAllPageInputsLoad() {
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderContentInputs));
    }

    public void clearAllInputs() {
        waitForAllPageInputsLoad();
        List<WebElement> allInputs = driver.findElements(orderContentInputs);
        for (WebElement input : allInputs) {
            String inputType = input.getAttribute("type");
            if (inputType.equals("text")) {
                input.clear();
            }

            if (inputType.equals("checkbox") && input.isSelected()) {
                input.click();
            }
        }
    }


    protected String getFieldErrorText(By fieldLocator) {
        String[] splitArray = fieldLocator.toString().split(" ");
        String xpath;
        splitArray = Arrays.copyOfRange(splitArray, 1, splitArray.length);
        xpath = String.join("", splitArray) + "/parent::div/div";

        // тут у скрытых элементов была беда. нашёл способ вытаскивать текст из скрытых элементов
        // с помощью JavascriptExecutor
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].textContent", element);
    }

    public boolean getWebElemVisibleByText(String elemText) {
        return driver.findElement(By.xpath("//*[text()='"+elemText+"']")).isDisplayed();
    }

    public abstract void fillPage(Order order);

    public abstract void clickOnNextButton();
}
