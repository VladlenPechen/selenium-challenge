package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimplePO extends BasePO {

    private final String COMMENT_PARAM_PATH = "//div//p[.=\"%s\"]";

    public SimplePO(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "comment")
    WebElement commentInput;

    @FindBy(id = "author")
    WebElement authorInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(id = "submit")
    WebElement submitButton;

    public SimplePO enterComment(String comment) {
        commentInput.clear();
        commentInput.sendKeys(comment);
        return this;
    }

    public SimplePO enterName(String name) {
        authorInput.clear();
        authorInput.sendKeys(name);
        return this;
    }

    public SimplePO enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    public SimplePO clickSubmit() {
        submitButton.click();
        return this;
    }

    public SimplePO verifyErrorAndClickBack(String error, int waitUntilError){
        new WebDriverWait(driver, waitUntilError)
                .until(ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(String.format("//strong[.=\"%s\"]", error)))));
        driver.navigate().back();
        return PageFactory.initElements(driver, SimplePO.class);
    }
    public SimplePO verifyCommentPresence(String text, int waitUntilAppearance){
        new WebDriverWait(driver, waitUntilAppearance)
                .until(ExpectedConditions.visibilityOf(
                        driver.findElement(By.xpath(String.format(COMMENT_PARAM_PATH, text)))));
        return this;
    }
}
