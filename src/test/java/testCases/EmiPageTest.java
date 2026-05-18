package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.EmiPage;
import testBase.BaseClass;

public class EmiPageTest extends BaseClass {
    @Test
    public void validateEMIAccuracy()
    {

        try
    {
       // Step 1: Open website
        driver.get("https://emicalculator.net/");

        EmiPage page = new EmiPage(driver);

        // Input values
        double principal = 1500000;   // 15 Lakhs
        double annualRate = 9.5;
        int tenureYears = 1;

        page.enterLoanDetails("1500000", "9.5", "1");

        // Step 2: Get EMI from UI
        double uiEMI = page.getEMIFromUI();

        //  Step 3: Calculate EMI using formula
        double monthlyRate = (annualRate / 12) / 100;
        int months = tenureYears * 12;

        double calculatedEMI =
                (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                        (Math.pow(1 + monthlyRate, months) - 1);

        // Round value for comparison
        calculatedEMI = Math.round(calculatedEMI);

        //  Print values
        System.out.println("UI EMI        : " + uiEMI);
        System.out.println("Calculated EMI: " + calculatedEMI);

        //  Step 4: Assert
        Assert.assertEquals(uiEMI, calculatedEMI, " EMI mismatch - Calculation is incorrect");

        System.out.println("EMI is accurate!");

    }
        catch (Exception e)
     {
       // e.printStackTrace();
       //  System.out.println("Error occured: "+e.getMessage());
        Assert.fail("Test failed due to exception");
     }
   }
}

