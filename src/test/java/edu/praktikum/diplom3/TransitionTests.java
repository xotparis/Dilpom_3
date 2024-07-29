package edu.praktikum.diplom3;

import edu.praktikum.diplom3.pages.*;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static edu.praktikum.diplom3.CONSTANTS.CONSTANTS.URL;
import static edu.praktikum.diplom3.pages.MainPage.*;
import static edu.praktikum.diplom3.pages.RegistrationPage.EMAIL_FOR_REGISTRATION_INPUT;
import static edu.praktikum.diplom3.pages.RegistrationPage.PASSWORD_FOR_REGISTRATION_INPUT;

public class TransitionTests{

    WebDriver webDriver;
    MainPage mainPage;
    ProfilePage profilePage;
    @Before
    public void create() {
        BasePage basePage = new BasePage(webDriver);
        webDriver = basePage.setUpDriver();

        webDriver.manage().window().maximize();
        webDriver.get(URL);

        profilePage = new ProfilePage(webDriver);
        mainPage = new MainPage(webDriver);
    }

    @Test
    @DisplayName("Checking the 'Личный кбинет' button")
    @Description("Positive case: Checking the personal account button")
    public void transitionToPersonalAccount() {
        mainPage.clickButton(PERSONAL_PROFILE_BUTTON);
        profilePage.verifyFieldText(EMAIL_FOR_REGISTRATION_INPUT, "");
        profilePage.verifyFieldText(PASSWORD_FOR_REGISTRATION_INPUT, "");
    }

    @Test
    @DisplayName("Checking the 'Конструктор' button")
    @Description("Positive case: Checking the constructor button")
    public void transitionToConstructor() {
        mainPage.clickButton(PERSONAL_PROFILE_BUTTON);
        mainPage.clickButton(CONSTRUCTOR_BUTTON);
        mainPage.verifyFieldTextMainPage(ENTER_BUTTON_TO_ACCOUNT, "Войти в аккаунт");
        mainPage.verifyFieldTextMainPage(PERSONAL_PROFILE_BUTTON, "Личный Кабинет");
    }

    @Test
    @DisplayName("Checking the 'Логотип' button")
    @Description("Positive case: Checking the logo button")
    public void transitionToLogo() {
        mainPage.clickButton(PERSONAL_PROFILE_BUTTON);
        mainPage.clickButton(LOGO_BUTTON);
        mainPage.verifyFieldTextMainPage(ENTER_BUTTON_TO_ACCOUNT, "Войти в аккаунт");
        mainPage.verifyFieldTextMainPage(PERSONAL_PROFILE_BUTTON, "Личный Кабинет");
    }

    @Test
    @DisplayName("Checking the 'Соусы>' button")
    @Description("Positive case: Checking the souse button")
    public void transitionToSouse() {
        mainPage.clickButton(SOUSE_BUTTON);
        mainPage.souseCheck();
    }

    @Test
    @DisplayName("Checking the 'Начинки' button")
    @Description("Positive case: Checking the fillings button")
    public void transitionToFillings() {
        mainPage.clickButton(FILLINGS_BUTTON);
        mainPage.fillingsCheck();
    }

    @Test
    @DisplayName("Checking the 'Булки' button")
    @Description("Positive case: Checking the bun button")
    public void transitionToBun() {
        mainPage.clickButton(FILLINGS_BUTTON);
        mainPage.clickButton(BUN_BUTTON);
        mainPage.bunCheck();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

}
