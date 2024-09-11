package br.com.automation.configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {

    private static WebDriver driver;

    private BrowserFactory() {}

    public static WebDriver web(){
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
