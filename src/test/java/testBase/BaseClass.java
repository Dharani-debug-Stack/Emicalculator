package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

public class BaseClass {

    public WebDriver driver;
    public Properties config_p;

    protected static final Logger capture = LogManager.getLogger(BaseClass.class);

    @BeforeClass(groups={"Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        Reporter.log("Execution started...", true);

        FileInputStream file = new FileInputStream("./src/test/resources/config.Properties");
        config_p = new Properties();
        config_p.load(file);

        //  Remote execution
        if(config_p.getProperty("execution_env").equalsIgnoreCase("remote")) {

            DesiredCapabilities test_dc = new DesiredCapabilities();

            if(os.equalsIgnoreCase("windows")) {
                test_dc.setPlatform(Platform.WIN11);
            } else if(os.equalsIgnoreCase("mac")) {
                test_dc.setPlatform(Platform.MAC);
            } else {
                throw new RuntimeException("Invalid OS");
            }

            switch (br.toLowerCase()) {
                case "chrome": test_dc.setBrowserName("chrome"); break;
                case "edge": test_dc.setBrowserName("MicrosoftEdge"); break;
                default: throw new RuntimeException("Invalid browser");
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), test_dc);
        }

        // Local execution
        else if(config_p.getProperty("execution_env").equalsIgnoreCase("local")) {

            switch (br.toLowerCase()) {
                case "chrome":
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("headless");
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid browser");
            }
        }

        // Common setup

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(config_p.getProperty("appURL1"));
    }

    @AfterMethod(groups={"Master"})
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (WebDriverException e) {
            System.out.println("Browser already closed or unreachable.");
        }
    }

    //  Screenshot method (fixed)
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
