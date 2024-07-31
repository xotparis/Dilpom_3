package edu.praktikum.diplom3.tests;

import edu.praktikum.diplom3.helpers.UserClient;
import edu.praktikum.diplom3.pages.*;
import edu.praktikum.diplom3.utils.TestDataGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static edu.praktikum.diplom3.constants.CONSTANTS.URL;
import static edu.praktikum.diplom3.pages.LoginPage.REGISTRATION_BUTTON_LOGIN_PAGE;
import static edu.praktikum.diplom3.pages.MainPage.ENTER_BUTTON_TO_ACCOUNT;
import static edu.praktikum.diplom3.pages.MainPage.PERSONAL_PROFILE_BUTTON;
import static edu.praktikum.diplom3.pages.ProfilePage.EXIT_BUTTON;
import static edu.praktikum.diplom3.pages.ProfilePage.INPUT_WITH_LOGIN_FOR_CHECK;
import static edu.praktikum.diplom3.pages.RegistrationPage.EMAIL_FOR_REGISTRATION_INPUT;
import static edu.praktikum.diplom3.pages.RegistrationPage.ENTER_BUTTON_REGISTRATION_PAGE;

public class EnterAndExitTests {
    WebDriver webDriver;
    protected String accessToken;
    private User user;
    private UserClient userClient;
    private static String name;
    private static String email;
    private static String password;

    LoginPage loginPage;
    ProfilePage profilePage;
    MainPage mainPage;
    RegistrationPage registrationPage;
    ForgotPasswordPage forgotPasswordPage;

    @Before
    public void create() {
        BasePage basePage = new BasePage(webDriver);
        webDriver = basePage.setUpDriver();

        webDriver.manage().window().maximize();
        webDriver.get(URL);

        name = TestDataGenerator.getRandomFirstName();
        email = TestDataGenerator.getRandomEmail();
        password = TestDataGenerator.getRandomPassword();

        registrationPage = new RegistrationPage(webDriver);
        loginPage = new LoginPage(webDriver);
        profilePage = new ProfilePage(webDriver);
        mainPage = new MainPage(webDriver);
        forgotPasswordPage = new ForgotPasswordPage(webDriver);

        user = new User(email, password, name);

        RestAssured.baseURI = URL;
        userClient = new UserClient();
        Response response = userClient.createUser(user);
        accessToken = response.path("accessToken");
    }

    @Test
    @DisplayName("Enter user with button 'Войти в аккаунт'")
    @Description("Positive case: Enter user with 'Войти в аккаунт' and check")
    public void enterButtonLoginToAccount() {
        mainPage.clickButton(ENTER_BUTTON_TO_ACCOUNT);
        loginPage.enterUser(email, password);
        mainPage.personalProfileButton();
        profilePage.verifyFieldText(INPUT_WITH_LOGIN_FOR_CHECK, email);
    }

    @Test
    @DisplayName("Enter user with button 'Личный кабинет'")
    @Description("Positive case: Enter user with 'Личный кабинет' and check")
    public void enterButtonPersonalProfile() {
        mainPage.clickButton(PERSONAL_PROFILE_BUTTON);
        loginPage.enterUser(email, password);
        mainPage.personalProfileButton();
        profilePage.verifyFieldText(INPUT_WITH_LOGIN_FOR_CHECK, email);
    }

    @Test
    @DisplayName("Enter user with button 'Войти'")
    @Description("Positive case: Enter user with 'Войти' on registration page and check")
    public void enterButtonRegistrationPage() {
        mainPage.clickButton(PERSONAL_PROFILE_BUTTON);
        loginPage.clickButton(REGISTRATION_BUTTON_LOGIN_PAGE);
        registrationPage.clickButton(ENTER_BUTTON_REGISTRATION_PAGE);
        loginPage.enterUser(email, password);
        mainPage.personalProfileButton();
        profilePage.verifyFieldText(INPUT_WITH_LOGIN_FOR_CHECK, email);
    }

    @Test
    @DisplayName("Enter user with button 'Войти'")
    @Description("Positive case: Enter user with 'Войти' on password recovery page and check")
    public void enterButtonPasswordRecoveryPage(){

        mainPage.clickButton(ENTER_BUTTON_TO_ACCOUNT);
        forgotPasswordPage.clickButtonEnterForgotPassword();
        forgotPasswordPage.clickButtonEnterButtonForgotPasswordPage();
        loginPage.enterUser(email, password);
        mainPage.personalProfileButton();
        profilePage.verifyFieldText(INPUT_WITH_LOGIN_FOR_CHECK, email);
    }

    @Test
    @DisplayName("Exit user with button 'Выйти'")
    @Description("Positive case: Exit user with 'Выйти' on personal account page and check")
    public void exitButtonPersonalProfilePage(){
        mainPage.clickButton(ENTER_BUTTON_TO_ACCOUNT);
        loginPage.enterUser(email, password);
        mainPage.personalProfileButton();
        profilePage.verifyFieldText(INPUT_WITH_LOGIN_FOR_CHECK, email);
        profilePage.clickButton(EXIT_BUTTON);
        profilePage.verifyFieldText(EMAIL_FOR_REGISTRATION_INPUT, "");
    }

    @After
    public void tearDown() {
        webDriver.quit();
        UserClient.deleteUser(accessToken);
    }

}
