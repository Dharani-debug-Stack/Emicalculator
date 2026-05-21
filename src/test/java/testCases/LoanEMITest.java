package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoanEMIPage;
import testBase.BaseClass;

public class LoanEMITest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void validateLoanEMIInputFields() {

        capture.info("********** Loan EMI Input Field Test Started **********");

        try {

            LoanEMIPage emiPage = new LoanEMIPage(driver);

            // Navigate
            emiPage.navigateToLoanCalculator();

            capture.info("********** Navigated to Loan EMI Calculator **********");

            // Validate fields
            boolean status = emiPage.validateAllFields();

            Assert.assertTrue(status, "Fields are not visible!");

            System.out.println("EMI input fields validated successfully");

            capture.info("********** EMI input fields validated successfully **********");

            capture.info("********** Loan EMI Test Passed **********");

        } catch (Exception e) {
            capture.error("********** Loan EMI Test Failed **********", e);
            e.printStackTrace();
            Assert.fail("Test failed");
        }
    }
}