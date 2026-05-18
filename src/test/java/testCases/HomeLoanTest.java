package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomeLoanPage;
import pageObject.HomeLoanEMIPage;
import testBase.BaseClass;

import java.util.List;

public class HomeLoanTest extends BaseClass {

    @Test
    public void validateYearlyEMIBreakup() {
        HomeLoanPage homeLoanPage = new HomeLoanPage(driver);
        HomeLoanEMIPage emiPage = new HomeLoanEMIPage(driver);

        // Step 1: Fill loan details
        homeLoanPage.fillHomeLoanDetails("5000000", "8.5", "2");

        // Step 2: Extract yearly table
        List<String[]> yearlyData = emiPage.extractYearlyTable();

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

        // Step 4: Validate total principal equals loan amount
        Assert.assertEquals(totalPrincipal, 5000000.0, 1.0, "Principal mismatch!");
    }
}
