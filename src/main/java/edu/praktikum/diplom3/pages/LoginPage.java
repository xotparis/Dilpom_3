package edu.praktikum.diplom3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BasePage{

    public static final By ENTER_BUTTON = By.xpath("//button[text()='Войти']");
    public static final By REGISTRATION_BUTTON_LOGIN_PAGE = By.xpath("//a[text()='Зарегистрироваться']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void setFieldValue(By fieldLocator, String text) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        webDriver.findElement(fieldLocator).sendKeys(text);
    }

    //Ввод данных пользователя для входа
    public void setDataForEnter(String email, String password) {
        setFieldValue(RegistrationPage.EMAIL_FOR_REGISTRATION_INPUT, email);
        setFieldValue(RegistrationPage.PASSWORD_FOR_REGISTRATION_INPUT, password);
    }

    //Вход пользователя
    public void enterUser(String email, String password) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ENTER_BUTTON));
        setDataForEnter(email, password);
        clickButton(ENTER_BUTTON);
    }

}
