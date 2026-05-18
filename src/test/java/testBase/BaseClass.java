package testBase;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

public class BaseClass {

    public WebDriver driver;

    @BeforeClass
    public void setup() {

        // ✅ Force TestNG report generation
        Reporter.log("Execution started...", true);

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://emicalculator.net/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // ✅ Screenshot method (fixed - no overwrite issue)
    public String captureScreenshot(String testName) {

        String path = System.getProperty("user.dir") + "/reports/"
                + testName + "_" + System.currentTimeMillis() + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(path);

            Files.createDirectories(dest.getParentFile().toPath());
            Files.copy(src.toPath(), dest.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}