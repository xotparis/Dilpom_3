package edu.praktikum.diplom3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
import java.time.Duration;

public class ProfilePage extends BasePage{

    //Выход
    public static final By EXIT_BUTTON = By.xpath("//button[text()='Выход']");
    //Проверка
    public static final By INPUT_WITH_LOGIN_FOR_CHECK = By.xpath("//label[text()='Логин']/following-sibling::input");

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    //Проверка
    public void verifyFieldText(By fieldLocator, String expectedText) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        String actualText = webDriver.findElement(fieldLocator).getAttribute("value");
        assertEquals("Текст в поле не соответствует ожидаемому", expectedText, actualText);
    }
}
