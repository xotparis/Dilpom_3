package edu.praktikum.diplom3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage{

    public static final By ENTER_FORGOT_PASSWORD = By.xpath("//a[@href='/forgot-password']");
    public static final By ENTER_BUTTON_FORGOT_PASSWORD_PAGE = By.xpath("//a[@href='/login']");

    public ForgotPasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickButtonEnterForgotPassword() {
        clickButton(ENTER_FORGOT_PASSWORD);
    }

    public void clickButtonEnterButtonForgotPasswordPage() {
        clickButton(ENTER_BUTTON_FORGOT_PASSWORD_PAGE);
    }
}
