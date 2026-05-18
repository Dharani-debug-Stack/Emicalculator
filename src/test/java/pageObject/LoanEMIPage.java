package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoanEMIPage extends BasePage {

    public LoanEMIPage(WebDriver driver) {
        super(driver);
    }

    // EMI Calculator input fields
    @FindBy(id = "loanamount")
    WebElement txtLoanAmount;

    @FindBy(id = "loaninterest")
    WebElement txtLoanInterest;

    @FindBy(id = "loanterm")
    WebElement txtLoanTenure;

    //  Validate input fields are enabled
    public boolean areInputFieldsEnabled() {
        wait.until(ExpectedConditions.visibilityOf(txtLoanAmount));
        return txtLoanAmount.isEnabled()
                && txtLoanInterest.isEnabled()
                && txtLoanTenure.isEnabled();
    }
}