package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoanEMIPage extends BasePage {

    public LoanEMIPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // ==============================
    // NAVIGATION ELEMENTS
    // ==============================

    @FindBy(linkText = "Loan Calculator")
    WebElement loanCalculatorMenu;

    @FindBy(linkText = "EMI Calculator")
    WebElement emiCalculatorOption;

    // ==============================
    // INPUT FIELDS
    // ==============================

    @FindBy(id = "loanamount")
    WebElement loanAmountTxt;

    @FindBy(id = "loaninterest")
    WebElement interestTxt;

    @FindBy(id = "loanterm")
    WebElement tenureTxt;

    // ==============================
    // SLIDERS
    // ==============================

    @FindBy(xpath = "//div[@id='loanamountslider']//span")
    WebElement loanAmountSlider;

    @FindBy(xpath = "//div[@id='loaninterestslider']//span")
    WebElement interestSlider;

    @FindBy(xpath = "//div[@id='loantermslider']//span")
    WebElement tenureSlider;

    // ==============================
    // NAVIGATION METHOD ✅
    // ==============================

    public void navigateToEMICalculator() {
        loanCalculatorMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(emiCalculatorOption));
        emiCalculatorOption.click();
    }

    // ==============================
    // UI VALIDATION METHODS ✅
    // ==============================

    public boolean areInputFieldsDisplayed() {
        return loanAmountTxt.isDisplayed()
                && interestTxt.isDisplayed()
                && tenureTxt.isDisplayed();
    }

    public boolean areInputFieldsEnabled() {
        return loanAmountTxt.isEnabled()
                && interestTxt.isEnabled()
                && tenureTxt.isEnabled();
    }

    // ==============================
    // TEXTBOX METHODS
    // ==============================

    public void setLoanAmount(String value) {
        loanAmountTxt.click();
        loanAmountTxt.sendKeys(Keys.CONTROL + "a");
        loanAmountTxt.sendKeys(value);
    }

    public String getLoanAmount() {
        return loanAmountTxt.getAttribute("value");
    }

    public void setInterest(String value) {
        interestTxt.click();
        interestTxt.sendKeys(Keys.CONTROL + "a");
        interestTxt.sendKeys(value);
    }

    public String getInterest() {
        return interestTxt.getAttribute("value");
    }

    public void setTenure(String value) {
        tenureTxt.click();
        tenureTxt.sendKeys(Keys.CONTROL + "a");
        tenureTxt.sendKeys(value);
    }

    public String getTenure() {
        return tenureTxt.getAttribute("value");
    }

    // ==============================
    // SLIDER METHODS
    // ==============================

    public void moveSlider(WebElement slider, int xOffset) {
        Actions action = new Actions(driver);
        action.dragAndDropBy(slider, xOffset, 0).perform();
    }

    public void waitForValueChange(WebElement element, String oldValue) {
        wait.until(ExpectedConditions.not(
                ExpectedConditions.attributeToBe(element, "value", oldValue)
        ));
    }

    // ==============================
    // USER STORY VALIDATION ✅
    // ==============================

    public boolean validateSliderTextboxSync(WebElement slider,
                                             WebElement textbox,
                                             int moveBy,
                                             String newValue) {

        // Step 1: capture old value
        String before = textbox.getAttribute("value");

        // Step 2: move slider
        moveSlider(slider, moveBy);

        // Step 3: wait for change
        waitForValueChange(textbox, before);

        String afterSlider = textbox.getAttribute("value");

        if (before.equals(afterSlider)) {
            return false;
        }

        // Step 4: change textbox
        textbox.click();
        textbox.sendKeys(Keys.CONTROL + "a");
        textbox.sendKeys(newValue);

        String afterTextbox = textbox.getAttribute("value");

        return afterTextbox.equals(newValue);
    }

    // Wrapper methods for reuse
    public boolean validateLoanAmountSync() {
        return validateSliderTextboxSync(loanAmountSlider, loanAmountTxt, 50, "1000000");
    }

    public boolean validateInterestSync() {
        return validateSliderTextboxSync(interestSlider, interestTxt, 10, "10");
    }

    public boolean validateTenureSync() {
        return validateSliderTextboxSync(tenureSlider, tenureTxt, 10, "5");
    }
}