package pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePO {

    WebDriver driver;
    WebDriverWait wait;
    Logger log;

    BasePO(WebDriver driverInstc) {
        driver = driverInstc;
        log = LoggerFactory.getLogger(BasePO.class);
    }

    void scrollIntoElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
