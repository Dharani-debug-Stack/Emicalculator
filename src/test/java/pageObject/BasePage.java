package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class BasePage
    {
        WebDriver driver;
        WebDriverWait wait;
        public BasePage(WebDriver driver)
        {
            this.driver=driver;
            PageFactory.initElements(driver, this);
            wait=new WebDriverWait(driver, Duration.ofSeconds(40));
        }

        public void scrollDown() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1500)");
        }

    }
