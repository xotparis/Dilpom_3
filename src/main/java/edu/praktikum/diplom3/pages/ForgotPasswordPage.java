package edu.praktikum.diplom3.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage{

    public static final By ENTER_FORGOT_PASSWORD = By.xpath("//a[@href='/forgot-password']");
    public static final By ENTER_BUTTON_FORGOT_PASSWORD_PAGE = By.xpath("//a[@href='/login']");

    public ForgotPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажатие на ссылку 'Восстановить пароль'")
    public void clickButtonEnterForgotPassword() {
        clickButton(ENTER_FORGOT_PASSWORD);
    }

    @Step("Нажатие на кноку 'Войти' на странице восстановление пароля")
    public void clickButtonEnterButtonForgotPasswordPage() {
        clickButton(ENTER_BUTTON_FORGOT_PASSWORD_PAGE);
    }
}
