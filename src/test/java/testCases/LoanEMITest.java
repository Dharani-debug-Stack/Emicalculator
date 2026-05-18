package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoanEMIPage;
import testBase.BaseClass;

public class LoanEMITest extends BaseClass {

    @Test
    public void validateLoanEMICalculatorInputFieldsFromMenu() {

        LoanEMIPage loanEMIPage = new LoanEMIPage(driver);

        // Navigate using Page Object
        loanEMIPage.navigateToLoanEMICalculator();

        // Validate input fields
        Assert.assertTrue(
                loanEMIPage.areInputFieldsEnabled(),
                "Loan EMI input fields are not enabled or not usable"
        );
    }
}
