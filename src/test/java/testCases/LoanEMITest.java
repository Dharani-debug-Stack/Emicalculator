package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoanEMIPage;
import testBase.BaseClass;

public class LoanEMITest extends BaseClass {

    @Test
    public void validateLoanEMIInputFields() {

        try {

            LoanEMIPage emiPage = new LoanEMIPage(driver);

            // Navigate (FIXED)
            emiPage.navigateToLoanCalculator();

            // Validate fields
            boolean status = emiPage.validateAllFields();

            Assert.assertTrue(status, "Fields are not visible!");

            System.out.println("EMI input fields validated successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed");
        }
    }
}