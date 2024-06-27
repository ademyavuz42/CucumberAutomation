package GeneralFolder.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    private Driver(){
    }
    private static WebDriver driver;
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperties("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions option = new ChromeOptions();
                    option.addArguments("--disable-blink-features=AutomationControlled");
                    option.setExperimentalOption("useAutomationExtension", false);
                    //System.setProperty("webdriver.chrome.driver", "./chromedriver");
                    driver = new ChromeDriver(option);
                    break;
                case "chrome-headless":
                    ChromeOptions option2 = new ChromeOptions();
                    option2.addArguments("--headless");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(option2);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "firefox-headless":
                    FirefoxOptions optionFirefox = new FirefoxOptions();
                    optionFirefox.addArguments("--headless");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(optionFirefox);
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
                case "remote_chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--no-sandbox");
                    //options.addArguments("--headless");
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                    break;
                case "remote_firefox":
                    FirefoxOptions options2 = new FirefoxOptions();
                    options2.addArguments("--no-sandbox");
                    // options2.addArguments("--headless");
//                    options2.addArguments("--headless");
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options2);
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
        return driver;
    }
    public static void closeDriver(){
        if (driver!=null){
            driver.quit();
            driver = null;
        }
    }
}
