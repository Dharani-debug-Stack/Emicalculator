package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.LoanEMIPage;
import testBase.BaseClass;

public class LoanEMITest extends BaseClass {

    @Test(priority = 1)
    public void validateNavigationAndUIFields() {

        LoanEMIPage page = new LoanEMIPage(driver);

        // Navigate
        page.navigateToEMICalculator();

        // Validate UI Fields
        Assert.assertTrue(page.areInputFieldsDisplayed(), "Input fields NOT visible");
        Assert.assertTrue(page.areInputFieldsEnabled(), "Input fields NOT enabled");
    }

    @Test(priority = 2)
    public void validateSliderSyncFunctionality() {

        LoanEMIPage page = new LoanEMIPage(driver);

        page.navigateToEMICalculator();

        // Loan Amount Sync
        Assert.assertTrue(page.validateLoanAmountSync(),
                "Loan Amount slider sync FAILED");

        // Interest Sync
        Assert.assertTrue(page.validateInterestSync(),
                "Interest slider sync FAILED");

        // Tenure Sync
        Assert.assertTrue(page.validateTenureSync(),
                "Tenure slider sync FAILED");
    }
}