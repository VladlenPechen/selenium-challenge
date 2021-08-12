package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StoreMainPO extends BasePO {
    public StoreMainPO(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@href=\"http://store.demoqa.com/sample-page/\"]")
    WebElement samplePageButton;

    public SimplePO clickSimplePageButton(){
        scrollIntoElement(samplePageButton);
        samplePageButton.click();
        return PageFactory.initElements(driver, SimplePO.class);
    }

}
