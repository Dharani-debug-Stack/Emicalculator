package pageObject;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeLoanPage extends BasePage {

    public HomeLoanPage(WebDriver driver) {
        super(driver);
    }

    // ===== MENU ELEMENTS =====

    @FindBy(id = "menu-item-dropdown-2696")
    WebElement loanCalculatorMenu;

    @FindBy(xpath = "//a[contains(@href,'home-loan-emi-calculator')]")
    WebElement homeLoanCalculatorLink;

    // ===== HOME LOAN INPUT FIELDS =====

    @FindBy(id = "loanamount")
    WebElement txtLoanAmount;

    @FindBy(id = "loaninterest")
    WebElement txtLoanInterest;

    @FindBy(id = "loanterm")
    WebElement txtLoanTerm;

    // ===== YEAR-WISE TABLE ROWS =====

    @FindBy(xpath = "//table//tr[contains(@class,'yearlypaymentdetails')]")
    List<WebElement> yearlyRows;

    // ===== ACTIONS =====

    // Navigate to Home Loan EMI Calculator from Menu
    public void navigateToHomeLoanCalculator() {
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOf(loanCalculatorMenu));
        actions.moveToElement(loanCalculatorMenu).perform();

        wait.until(ExpectedConditions.elementToBeClickable(homeLoanCalculatorLink))
                .click();
    }

    // Enter loan details
    public void enterHomeLoanDetails(String amount, String interest, String tenure) {
        wait.until(ExpectedConditions.visibilityOf(txtLoanAmount));

        txtLoanAmount.clear();
        txtLoanAmount.sendKeys(amount);

        txtLoanInterest.clear();
        txtLoanInterest.sendKeys(interest);

        txtLoanTerm.clear();
        txtLoanTerm.sendKeys(tenure);
        txtLoanTerm.sendKeys(Keys.TAB); // triggers calculation
    }

    // Get year-wise EMI table rows
    public List<WebElement> getYearlyRows() {
        wait.until(ExpectedConditions.visibilityOfAllElements(yearlyRows));
        scrollDown();
        return yearlyRows;
    }
}