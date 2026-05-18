package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomeLoanPage;
import testBase.BaseClass;

import java.util.List;

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


            // Step 2: Extract yearly table
            List<String[]> yearlyData = home.extractYearlyTable();

            // Step 3: Parse and validate
            double totalPrincipal = 0.0;
            for(String[] row : yearlyData) {
                String year = row[0];
                String principal = row[1];
                String interest = row[2];
                String totalPayment = row[3];
                String balance = row[4];
                String loanPaid = row[5];

                System.out.println(year + " | " + principal + " | " + interest + " | "
                        + totalPayment + " | " + balance + " | " + loanPaid);

                totalPrincipal += Double.parseDouble(principal.replace("₹","").replace(",","").trim());
            }

            double homeValue = Double.parseDouble(
                    home.getHomeValue().replace("₹","").replace(",","").trim()
            );

            double downPaymentPercent = Double.parseDouble(
                    home.getDownPayment().trim()
            );

            double insurance = Double.parseDouble(
                    home.getInsurance().replace("₹","").replace(",","").trim()
            );

// Calculate down payment in rupees
            double downPayment = homeValue * (downPaymentPercent / 100.0);

// Expected loan amount
            double expectedLoanAmount = homeValue + insurance - downPayment;

// Validate
            Assert.assertEquals(totalPrincipal, expectedLoanAmount, 1.0, "Principal mismatch!");


            System.out.println("✅ EMI table validation completed successfully. Principal matches expected loan amount!");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed");
        }
    }
}