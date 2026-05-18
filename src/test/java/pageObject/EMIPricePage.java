package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EMIPricePage extends BasePage {

    @FindBy(id = "car-loan")
    private WebElement carLoanTab;

    public EMIPricePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Step 1: Click Car Loan Tab
    public void clickCarLoanTab() {
        wait.until(ExpectedConditions.elementToBeClickable(carLoanTab));
        carLoanTab.click();
    }

    // Step 2: Select Year 2026 schedule and take screenshot of amortization table
    public void selectYear2026() throws InterruptedException, IOException {
        By year2026Locator = By.id("year2026");
        WebElement year2026Btn = wait.until(ExpectedConditions.presenceOfElementLocated(year2026Locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", year2026Btn);

        wait.until(ExpectedConditions.elementToBeClickable(year2026Btn));
        year2026Btn.click();

        Thread.sleep(2000);

        // Locate amortization table (the schedule table)
        WebElement tableArea = driver.findElement(By.xpath("//div[@id='emipaymenttable']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", tableArea);

        Thread.sleep(2000);

        // Screenshot of the amortization table
        takeElementScreenshot(tableArea, "Year2026_Table.png");
    }

    // Step 3: Scroll to bottom and take screenshot
    public void scrollToBottom() throws InterruptedException, IOException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);
        takeScreenshot("BottomPage.png");
    }

    // Utility method to capture full-page screenshot
    private void takeScreenshot(String fileName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + "/screenshots/" + timestamp + "_" + fileName);
        Files.createDirectories(destFile.getParentFile().toPath());
        Files.copy(srcFile.toPath(), destFile.toPath());
        System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
    }

    // Utility method to capture specific element screenshot
    private void takeElementScreenshot(WebElement element, String fileName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File srcFile = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + "/screenshots/" + timestamp + "_" + fileName);
        Files.createDirectories(destFile.getParentFile().toPath());
        Files.copy(srcFile.toPath(), destFile.toPath());
        System.out.println("Element screenshot saved: " + destFile.getAbsolutePath());
    }
}
