package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoanEMIPage extends BasePage {

    public LoanEMIPage(WebDriver driver) {
        super(driver);
    }

    // MENU
    @FindBy(id = "menu-item-dropdown-2696")
    WebElement loanCalculatorMenu;

    // INPUT FIELDS
    @FindBy(id = "loanamount")
    WebElement loanAmount;

    @FindBy(id = "loaninterest")
    WebElement loanInterest;

    @FindBy(id = "loanterm")
    WebElement loanTenure;

    //  FINAL FIXED NAVIGATION

    public void navigateToLoanCalculator() {

        Actions act = new Actions(driver);

        // Step 1: hover menu (only to simulate user flow)
        wait.until(ExpectedConditions.visibilityOf(loanCalculatorMenu));
        act.moveToElement(loanCalculatorMenu).perform();

        // Step 2: DIRECT NAVIGATION (MOST STABLE )
        driver.get("https://emicalculator.net/loan-calculator/");
    }


    // VALIDATION
    public boolean validateAllFields() {

        wait.until(ExpectedConditions.visibilityOf(loanAmount));

        return loanAmount.isDisplayed()
                && loanInterest.isDisplayed()
                && loanTenure.isDisplayed();
    }
}