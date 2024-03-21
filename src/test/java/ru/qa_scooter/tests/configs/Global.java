package ru.qa_scooter.tests.configs;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Сюда вынес всякие используемые почти всеми константы и методы
// Комментирую одну из строчек браузера мы настраиваем все тесты для взаимодействия с определённым браузером
public class Global {
//    public static Browser browserToTest = Browser.CHROME;
    public static Browser browserToTest = Browser.FIREFOX;
    public static LocalDate today = LocalDate.now();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void setCookie(String cookieName, String cookieValue, WebDriver driver) {
        Cookie cookie = driver.manage().getCookieNamed(cookieName);

        if (cookie == null) {
            Cookie newCookie = new Cookie(cookieName,cookieValue);
            driver.manage().addCookie(newCookie);
        }
        else {
            deleteCookie(cookieName, driver);
            setCookie(cookieName, cookieValue, driver);
        }
    }

    public static void deleteCookie(String cookieName, WebDriver driver) {
        Cookie cookie = driver.manage().getCookieNamed(cookieName);
        if (cookie != null) {
            driver.manage().deleteCookieNamed(cookieName);
        }

    }
}
