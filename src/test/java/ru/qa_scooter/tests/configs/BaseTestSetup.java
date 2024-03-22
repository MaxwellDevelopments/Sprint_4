package ru.qa_scooter.tests.configs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

// Самый базовый класс для тестов.
// Отвечает за настройку драйверов
public class BaseTestSetup {
    protected static WebDriver driver;

    @BeforeClass
    public static void initDriver() {
        switch (Global.browserToTest) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
        }
    }
}
