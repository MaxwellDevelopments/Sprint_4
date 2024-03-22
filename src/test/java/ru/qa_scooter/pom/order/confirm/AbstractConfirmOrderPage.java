package ru.qa_scooter.pom.order.confirm;

import org.openqa.selenium.WebDriver;
import ru.qa_scooter.pom.AbstractPage;

public abstract class AbstractConfirmOrderPage extends AbstractPage {
    // По сути это интерфейс, а не класс.
    // но интерфейсы мы не проходили, поэтому я не очень понимаю как правильно с ними работать
    // Хотя в плане абстракций может это и правильно, что является классом. Не знаю.
    public AbstractConfirmOrderPage(WebDriver driver) {
        super(driver);
    }

    public abstract void nextPage();
}
