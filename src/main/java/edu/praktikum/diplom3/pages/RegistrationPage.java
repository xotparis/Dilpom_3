package edu.praktikum.diplom3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static edu.praktikum.diplom3.pages.LoginPage.ENTER_BUTTON;
import static edu.praktikum.diplom3.pages.LoginPage.REGISTRATION_BUTTON_LOGIN_PAGE;
import static edu.praktikum.diplom3.pages.MainPage.ENTER_BUTTON_TO_ACCOUNT;
import static org.junit.Assert.assertTrue;

public class RegistrationPage extends BasePage{

    //Регистрация и вход, поля
    public static final By NAME_FOR_REGISTRATION_INPUT = By.xpath("//label[text()='Имя']/following-sibling::input");
    public static final By EMAIL_FOR_REGISTRATION_INPUT = By.xpath("//label[text()='Email']/following-sibling::input");
    public static final By PASSWORD_FOR_REGISTRATION_INPUT = By.xpath("//label[text()='Пароль']/following-sibling::input");

    //Кнопки
    public static final By REGISTRATION_BUTTON_REG_PAGE = By.xpath("//button[text()='Зарегистрироваться']");
    public static final By ENTER_BUTTON_REGISTRATION_PAGE = By.xpath("//a[text()='Войти']");

    //Проверка
    public static final By INPUT_AFTER_WRONG_PASSWORD_FOR_CHECK = By.xpath("//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void setFieldValue(By fieldLocator, String text) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        webDriver.findElement(fieldLocator).sendKeys(text);
    }

    //Ввод данных пользователя для регистрации
    public void setDataForRegistration(String name, String email, String password) {
        setFieldValue(NAME_FOR_REGISTRATION_INPUT, name);
        setFieldValue(EMAIL_FOR_REGISTRATION_INPUT, email);
        setFieldValue(PASSWORD_FOR_REGISTRATION_INPUT, password);
    }

    //Регистрация пользователя
    public void registerUser(String name, String email, String password) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        clickButton(ENTER_BUTTON_TO_ACCOUNT);
        clickButton(REGISTRATION_BUTTON_LOGIN_PAGE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(REGISTRATION_BUTTON_REG_PAGE));
        setDataForRegistration(name, email, password);
        clickButton(REGISTRATION_BUTTON_REG_PAGE);
    }

    public void verifyElementIsVisible(By fieldLocator) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        assertTrue(webDriver.findElement(fieldLocator).isDisplayed());
    }
}
