package ru.qa_scooter.tests.configs;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseTest extends BaseTestSetup {

    @Before
    public void startDriver() {
        switch (Global.browserToTest) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
