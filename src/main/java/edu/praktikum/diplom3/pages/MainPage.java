package edu.praktikum.diplom3.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

import java.time.Duration;


public class MainPage extends BasePage{
    //Кнопки входа
    public static final By ENTER_BUTTON_TO_ACCOUNT = By.xpath("//button[text()='Войти в аккаунт']");
    public static final By PERSONAL_PROFILE_BUTTON = By.xpath("//a[@href='/account']");
    //Кнопки в header
    public static final By CONSTRUCTOR_BUTTON = By.xpath("//p[text()='Конструктор']");
    public static final By LOGO_BUTTON = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");

    public static final By SOUSE_BUTTON = By.xpath("//span[text()='Соусы']");
    public static final By BUN_BUTTON = By.xpath("//span[text()='Булки']");
    public static final By FILLINGS_BUTTON = By.xpath("//span[text()='Начинки']");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверка на странице по тексту")
    public void verifyFieldTextMainPage(By fieldLocator, String expectedText) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        String actualText = webDriver.findElement(fieldLocator).getText();
        assertEquals("Текст в поле не соответствует ожидаемому", expectedText, actualText);
    }

    @Step("Нажатие на категорию Соусов")
    public boolean souseCheck() {
        return doesElementClassContain(SOUSE_BUTTON, "tab_tab_type_current");
    }

    @Step("Нажатие на категорию Начинок")
    public boolean fillingsCheck() {
        return doesElementClassContain(FILLINGS_BUTTON, "tab_tab_type_current");
    }

    @Step("Нажатие на категорию Булки")
    public boolean bunCheck() {
        return doesElementClassContain(BUN_BUTTON, "tab_tab_type_current");
    }

    @Step("Нажатие на кнопку Личный кабинет")
    public void personalProfileButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_PROFILE_BUTTON)).click();
    }

}
