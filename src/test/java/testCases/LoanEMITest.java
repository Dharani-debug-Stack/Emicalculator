package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.LoanEMIPage;
import testBase.BaseClass;

public class LoanEMITest extends BaseClass {

    @Test
    public void validateLoanEMICalculatorUI() {

        // Navigate to EMI Calculator under Loan Calculator
        driver.findElement(By.linkText("Loan Calculator")).click();
        driver.findElement(By.linkText("EMI Calculator")).click();

        LoanEMIPage loanEMIPage = new LoanEMIPage(driver);

        // Validate input fields
        Assert.assertTrue(
                loanEMIPage.areInputFieldsEnabled(),
                "Loan EMI input fields are not enabled"
        );
    }
}
