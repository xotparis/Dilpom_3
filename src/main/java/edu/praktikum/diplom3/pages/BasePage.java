package edu.praktikum.diplom3.pages;

import edu.praktikum.diplom3.CONSTANTS.CONSTANTS;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasePage {
    protected WebDriver webDriver;


    public WebDriver setUpDriver() {
        String browser = System.getProperty("browser", "yandex");

        if (browser.equalsIgnoreCase("chrome")) {
            webDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.yandex.driver", CONSTANTS.PATH_TO_YANDEX_DRIVER);
            ChromeOptions options = new ChromeOptions();
            options.setBinary(CONSTANTS.PATH_TO_YANDEX_BROWSER);
            webDriver = new ChromeDriver(options);
        }
        else {
            throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
        webDriver.get(CONSTANTS.URL);
        return webDriver;
    }

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickButton(By locator) {
        webDriver.findElement(locator).click();
    }

    public boolean doesElementClassContain(By xpath, String substring) {
        String className = getClassOfCategory(xpath);
        return className != null && className.contains(substring);
    }

    @Step("Поиск элемента по атрибуту Class")
    public String getClassOfCategory(By xpath) {
        return webDriver.findElement(xpath).getAttribute("class");
    }

}
