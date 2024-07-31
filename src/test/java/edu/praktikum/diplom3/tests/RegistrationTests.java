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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static edu.praktikum.diplom3.CONSTANTS.CONSTANTS.LOGIN_ENDPOINT;
import static edu.praktikum.diplom3.CONSTANTS.CONSTANTS.URL;
import static edu.praktikum.diplom3.pages.MainPage.PERSONAL_PROFILE_BUTTON;
import static edu.praktikum.diplom3.pages.ProfilePage.INPUT_WITH_LOGIN_FOR_CHECK;
import static edu.praktikum.diplom3.pages.RegistrationPage.INPUT_AFTER_WRONG_PASSWORD_FOR_CHECK;
import static io.restassured.RestAssured.given;

public class RegistrationTests{

    WebDriver webDriver;
    protected String accessToken;
    private static String name;
    private static String email;
    private static String password;
    private User user;
    private UserClient userClient;

    RegistrationPage registrationPage;
    LoginPage loginPage;
    ProfilePage profilePage;
    MainPage mainPage;

    @Before
    public void setUpTestData() {
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

        userClient = new UserClient();
    }

    @Test
    @DisplayName("Registration user")
    @Description("Positive case: Registration user with correct data and check")
    public void registrationWithCorrectData(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        registrationPage.registerUser(name, email, password);
        loginPage.enterUser(email,password);
        user = new User(email, password, name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_PROFILE_BUTTON)).click();
        profilePage.verifyFieldText(INPUT_WITH_LOGIN_FOR_CHECK, email);
    }

    @Test
    @DisplayName("Registration user with incorrect password")
    @Description("Negative case: Registration user with incorrect password and check")
    public void registrationWithIncorrectData() {

        registrationPage.registerUser(name, email, "12345");
        registrationPage.verifyElementIsVisible(INPUT_AFTER_WRONG_PASSWORD_FOR_CHECK);
    }

    @After
    public void tearDown(){
        webDriver.quit();
        if (user != null) {
            Response response = userClient.login(user);
            accessToken = response.path("accessToken");
            userClient.deleteUser(accessToken);
        }
    }
}


