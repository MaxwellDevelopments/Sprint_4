package ru.qa_scooter.tests.configs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTestAfterAndBeforeClass extends BaseTestSetup {

    @BeforeClass
    public static void startDriver() {
        switch (Global.browserToTest) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
        }
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
