package GeneralFolder.pages;



import GeneralFolder.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class PageObject {

    public PageObject(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public void scrollInto(WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView();",element);
    }

    public void clickByJS(WebElement element){
        scrollInto(element);
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].click();",element);
    }

    public String getText(WebElement element){
        waitUntilVisibilityOf(element);
        scrollInto(element);
        return element.getText();
    }

    public void sendKey(WebElement element, String text){
        waitUntilVisibilityOf(element);
        scrollInto(element);
        element.sendKeys(text);
    }

    public void click(WebElement element){
//        waitUntilVisibilityOf(element);
//        scrollInto(element);
        element.click();
    }

    public void switchToSecondWindows(String mevcutWindows){
        Set<String> allWindows = Driver.getDriver().getWindowHandles();
        for (String window : allWindows) {
            if (!mevcutWindows.equals(window)){
                Driver.getDriver().switchTo().window(window);
            }
        }
    }

    public void waitUntilVisibilityOf(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilClicklable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isEnabledElement(WebElement element){
        scrollInto(element);
        return element.isEnabled();
    }

    public void waitUntilElementDisabled(WebElement element) throws InterruptedException {
        boolean flag = element.isEnabled();
        int i = 0;
        while (flag & i < 20) {
            Thread.sleep(1000);
            flag = element.isEnabled();
            i++;
        }
    }



}
