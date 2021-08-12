import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    Logger log = LoggerFactory.getLogger(BaseTest.class);
    Properties props;
    InputStream is;
    WebDriver driver;
    int timeout;

    public BaseTest() {
        props = new Properties();
        try {
            is = BaseTest.class.getClassLoader().getResourceAsStream("test.properties");
            props.load(is);
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ioe) {
                    log.error(ioe.getMessage());
                }
            }
        }
    }
    @BeforeTest
    void startBrowser(){
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getClassLoader().getResource("chromedriver.exe").getPath());
        timeout = Integer.parseInt(props.getProperty("wait"));

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.get(props.getProperty("base.url"));
    }
    @AfterTest
    void stopDriver(){
        driver.quit();
    }
}
