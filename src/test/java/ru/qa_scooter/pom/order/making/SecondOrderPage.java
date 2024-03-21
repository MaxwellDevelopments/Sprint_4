package ru.qa_scooter.pom.order.making;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qa_scooter.business.Order;
import ru.qa_scooter.constants.Color;
import ru.qa_scooter.constants.RentTime;

import java.time.LocalDate;

import static ru.qa_scooter.tests.configs.Global.formatter;
import static ru.qa_scooter.tests.configs.Global.today;

public class SecondOrderPage extends AbstractOrderPage {

    // локатор для определения открытия страницы
    private final By pageLocator = By.xpath("//*[contains(text(), 'Про аренду')]");
    // ввод даты
    private final By dateInput = By.xpath("//*[contains(@placeholder, 'Когда')]");
    // ввод времени аренды
    private final By rentTimeInput = By.xpath("//*[contains(text(), 'аренды')]");

    // инпут для ввода комментария
    private final By commentInput = By.xpath("//*[contains(@placeholder, 'Комментарий')]");
    // кнопка на объекте календаря для перемещения на предыдущий месяц
    private final By prevMonthButton = By.xpath("//button[contains(text(), 'Previous')]");
    // кнопка на объекте календаря для перемещения на следующий месяц
    private final By nextMonthButton = By.xpath("//button[contains(text(), 'Next')]");
    // кнопка для подтверждения заказа
    private final By makeOrderButton = By.xpath("//*[contains(@class, 'Order_Content')]//button[text()='Заказать']");

    public SecondOrderPage(WebDriver driver) {
        super(driver);
    }

    private void clickNextMonthButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(nextMonthButton));
        driver.findElement(nextMonthButton).click();
    }

    private void clickPrevMonthButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(prevMonthButton));
        driver.findElement(prevMonthButton).click();
    }

    public void setDateByCalendar(LocalDate rentalDate) {
        if (rentalDate == null) {
            return;
        }

        driver.findElement(dateInput).click();
        String rentalDateXpath;
        String weekClassName = "react-datepicker__week";
        int monthValue = today.getMonthValue();
        int rentMonthValue = rentalDate.getMonthValue();
        int diffMonth = rentMonthValue - monthValue + (rentalDate.getYear() - today.getYear())*12;
        int add = diffMonth > 0 ? -1 : 1;
        while (diffMonth != 0) {
            if (diffMonth > 0) {
                clickNextMonthButton();
            }
            else {
                clickPrevMonthButton();
            }
            diffMonth += add;
        }


        int weekOffset = 1 + (rentalDate.getDayOfMonth()-1 + rentalDate.withDayOfMonth(1).getDayOfWeek().getValue()) / 7;

        //div[@class='react-datepicker__week' and position()=1]//*[text()='30']

        rentalDateXpath = "//div[@class='" + weekClassName
                + "' and position()="
                + weekOffset
                + "]//*[text()='"
                + rentalDate.getDayOfMonth() + "']";

        driver.findElement(By.xpath(rentalDateXpath)).click();

    }

    public void setDateByInput(LocalDate rentalDate) {
        if (rentalDate == null) {
            return;
        }
        driver.findElement(dateInput).sendKeys(rentalDate.format(formatter));
    }

    public String getDateError() {
        return getFieldErrorText(dateInput);
    }


    public void setRentTime(RentTime rentPeriod) {
        if (rentPeriod == null) {
            return;
        }
        driver.findElement(rentTimeInput).click();
        driver.findElement(By.xpath("//*[text()='" + rentPeriod.getStringValue() + "']")).click();
    }

    public String getRentTimeError() {
        return getFieldErrorText(rentTimeInput);
    }

    public void setColor(Color color) {
        if (color == null) {
            return;
        }
        driver.findElement(By.xpath("//*[text()='" + color.getStringValue() + "']")).click();
    }

    public void setComment(String comment) {
        if (comment == null) {
            return;
        }
        driver.findElement(commentInput).sendKeys(comment);
    }

    public String getCommentError() {
        return getFieldErrorText(commentInput);
    }


    @Override
    public void fillPage(Order order) {
        waitForAllPageInputsLoad();
        setDateByCalendar(order.getDate());
        setRentTime(order.getRentTime());
        setColor(order.getColor());
        setComment(order.getComment());
    }

    @Override
    public boolean isPageOpened() {
        return super.isPageOpened() && super.isPageOpened(pageLocator);
    }

    @Override
    public void clickOnNextButton() {
        clickOnMakeOrderButton();
    }

    public void clickOnMakeOrderButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(makeOrderButton));
        driver.findElement(makeOrderButton).click();
    }




}
