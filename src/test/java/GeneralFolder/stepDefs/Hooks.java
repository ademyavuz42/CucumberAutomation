package GeneralFolder.stepDefs;


import GeneralFolder.utilities.ConfigReader;
import GeneralFolder.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @Before
    public void setUp() {

        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperties("url"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @After
    public void tarayiciyiKapat() throws IOException, InterruptedException {
        Thread.sleep(5000);
        Driver.closeDriver();
    }
}
