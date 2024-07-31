package edu.praktikum.diplom3.pages;

import io.qameta.allure.Step;
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

    @Step("Ввод текста в поле ввода")
    public void setFieldValue(By fieldLocator, String text) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        webDriver.findElement(fieldLocator).sendKeys(text);
    }

    @Step("Ввод данных для логина")
    public void setDataForEnter(String email, String password) {
        setFieldValue(RegistrationPage.EMAIL_FOR_REGISTRATION_INPUT, email);
        setFieldValue(RegistrationPage.PASSWORD_FOR_REGISTRATION_INPUT, password);
    }

    @Step("Ввод данных для логина и нажатие на кнопку 'Войти'")
    public void enterUser(String email, String password) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ENTER_BUTTON));
        setDataForEnter(email, password);
        clickButton(ENTER_BUTTON);
    }

}
