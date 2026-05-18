package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomeLoanPage;
import testBase.BaseClass;

public class HomeLoanTest extends BaseClass {

    @Test
    public void verifyHomeLoanEMICalculator() {

        try {

            HomeLoanPage home = new HomeLoanPage(driver);

            // Step 1: Navigate
            home.navigateToHomeLoanCalculator();

            //  Step 2: Enter Loan Details
            home.setHomeValue();
            home.setDownPayment();
            home.setInsurance();
            home.setInterest();
            home.setTenure();
            home.setFees();

            //  Step 3: Validate Table Generated
            boolean result = home.isTableGenerated();

            Assert.assertTrue(result, " EMI table not generated");

            System.out.println(" Home Loan EMI table generated successfully");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed");
        }
    }
}