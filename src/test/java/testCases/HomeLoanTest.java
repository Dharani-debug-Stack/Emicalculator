package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomeLoanPage;
import testBase.BaseClass;

import java.util.List;
import org.openqa.selenium.WebElement;

public class HomeLoanTest extends BaseClass {

    @Test
    public void validateYearlyEMIBreakup() {
        HomeLoanPage homeLoanPage = new HomeLoanPage(driver);

        // Step 1: Fill loan details (these can come from DataProvider or external file)
        homeLoanPage.fillHomeLoanDetails("5000000", "8.5", "2");

        // Step 2: Extract yearly rows
       List<WebElement> yearlyRows = homeLoanPage.yearlyRows;

        // Step 3: Parse and validate
        double totalPrincipal = 0.0;
        for(WebElement row : yearlyRows) {
            List<WebElement> cols = homeLoanPage.getColumns(row);

            String year = cols.get(0).getText();
            String principal = cols.get(1).getText();
            String interest = cols.get(2).getText();
            String totalPayment = cols.get(3).getText();
            String balance = cols.get(4).getText();
            String loanPaid = cols.get(5).getText();

            // Print for reuse
            System.out.println(year + " | " + principal + " | " + interest + " | "
                    + totalPayment + " | " + balance + " | " + loanPaid);

            // Add principal for validation
            totalPrincipal += Double.parseDouble(principal.replace("₹","").replace(",","").trim());
        }

        // Step 4: Validate total principal equals loan amount
        // Allow ±1 rupee tolerance
        Assert.assertEquals(totalPrincipal, 5000000.0, 1.0, "Principal mismatch!");


    }
}
