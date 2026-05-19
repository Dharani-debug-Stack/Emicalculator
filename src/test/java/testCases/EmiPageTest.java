package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.EmiPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class EmiPageTest extends BaseClass
{
    @Test
    public void veriftEmi()
    {
        try
        {
            HomePage hp= new HomePage(driver);
            hp.clickcalculator();

            EmiPage emi= new EmiPage(driver);

            Assert.assertTrue(emi.validateTextFields(), "Textbox validation failed");
            //Validate inputs
            Assert.assertTrue(emi.validateInputBox(emi.txtloanamount, "2000000"));
            Assert.assertTrue(emi.validateInputBox(emi.txtloaninterest, "8"));
            Assert.assertTrue(emi.validateInputBox(emi.txtloanterm, "2"));
            Assert.assertTrue(emi.validateInputBox(emi.txtloanfees, "1"));


            // Validate slider
            Assert.assertTrue(emi.validateSliderFunctionality(), "Slider not working");

            //EMI Calculation
            emi.setLoanAmount();
            emi.setLoanInterest();
            emi.setloanterm();
            emi.setloantermmonth();
            emi.setfee();

            //loan amount
            emi.clickLoanamount();
            emi.setLoanAmount1();
            emi.setLoanInterest1();
            emi.setloanterm1();
            emi.setloantermmonth1();
            emi.setfee1();

            //loan Tenure
            emi.clickLoanamount1();
            emi.setLoanAmount2();
            emi.setLoanInterest2();
            emi.setloanterm2();
            emi.setfee2();

            System.out.println("EMI UI Validation Completed");
        }catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
