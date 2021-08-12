import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageobjects.StoreMainPO;

import java.util.Random;

public class StoreChallengeTest extends BaseTest {

    final String COMMENT = "Comment" + String.valueOf((new Random()).nextInt());
    final String ERROR = "ERROR";
    final String NAME = "Teddy";
    final String WRONG_EMAIL = "Teddy123";
    final String CORRECT_EMAIL = "Teddy@123.com";

    @Test
    public void commentAddingTest(){
        PageFactory.initElements(driver, StoreMainPO.class)
                .clickSimplePageButton()
                .enterComment(COMMENT)
                .enterName(NAME)
                .enterEmail(WRONG_EMAIL)
                .clickSubmit()
                .verifyErrorAndClickBack(ERROR, timeout)
                .enterComment(COMMENT)
                .enterName(NAME)
                .enterEmail(CORRECT_EMAIL)
                .clickSubmit()
                .verifyCommentPresence(COMMENT,timeout);
    }
}
