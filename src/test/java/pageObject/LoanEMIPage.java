package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoanEMIPage extends BasePage {

    public LoanEMIPage(WebDriver driver) {
        super(driver);
    }

    // ===== Menu elements =====

    @FindBy(id = "menu-item-dropdown-2696")
    WebElement loanCalculatorMenu;

    @FindBy(xpath = "//a[contains(@href,'loan-calculator')]")
    WebElement loanCalculatorLink;

//    @FindBy(id = "emi-calc")
//    WebElement emiCalculatorLink;

    // ===== EMI input fields =====

    @FindBy(id = "loanamount")
    WebElement txtLoanAmount;

    @FindBy(id = "loaninterest")
    WebElement txtLoanInterest;

    @FindBy(id = "loanterm")
    WebElement txtLoanTenure;

    // ===== Actions =====

    // Navigate from Menu → Loan Calculator → EMI Calculator
    public void navigateToLoanEMICalculator() {
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOf(loanCalculatorMenu));
        actions.moveToElement(loanCalculatorMenu).perform();

        wait.until(ExpectedConditions.elementToBeClickable(loanCalculatorLink)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(emiCalculatorLink)).click();
    }

    // Validate EMI input fields
    public boolean areInputFieldsEnabled() {
        wait.until(ExpectedConditions.visibilityOfAllElements(
                txtLoanAmount, txtLoanInterest, txtLoanTenure));

        return txtLoanAmount.isEnabled()
                && txtLoanInterest.isEnabled()
                && txtLoanTenure.isEnabled();
    }
}