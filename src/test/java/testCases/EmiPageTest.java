package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.EmiPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class EmiPageTest extends BaseClass
{
    @Test(groups = {"Regression","Master"})
    public void veriftEmi()
    {
        capture.info("********** Starting EMI Page Test **********");
        try
        {
            capture.info("Creating HomePage object");
            HomePage hp= new HomePage(driver);

            capture.info("Clicking on EMI Calculator");
            hp.clickcalculator();

            capture.info("Creating EmiPage object");
            EmiPage emi= new EmiPage(driver);

            capture.info("Validating text fields");
            Assert.assertTrue(emi.validateTextFields(), "Textbox validation failed");

            capture.info("Validating input boxes");
            Assert.assertTrue(emi.validateInputBox(emi.txtloanamount, "2000000"));
            Assert.assertTrue(emi.validateInputBox(emi.txtloaninterest, "8"));
            Assert.assertTrue(emi.validateInputBox(emi.txtloanterm, "2"));
            Assert.assertTrue(emi.validateInputBox(emi.txtloanfees, "1"));

            capture.info("Validating slider functionality");
            Assert.assertTrue(emi.validateSliderFunctionality(), "Slider not working");

            capture.info("Performing EMI calculation");
            emi.setLoanAmount();
            emi.setLoanInterest();
            emi.setloanterm();
            emi.setloantermmonth();
            emi.setfee();

            capture.info("Performing loan amount variation calculation");
            emi.clickLoanamount();
            emi.setLoanAmount1();
            emi.setLoanInterest1();
            emi.setloanterm1();
            emi.setloantermmonth1();
            emi.setfee1();

            capture.info("Performing loan tenure variation calculation");
            emi.clickLoanamount1();
            emi.setLoanAmount2();
            emi.setLoanInterest2();
            emi.setloanterm2();
            emi.setfee2();

            capture.info("EMI UI Validation Completed");
            System.out.println("EMI UI Validation Completed");

            capture.info("********** EMI Page Test Passed **********");
        }
        catch(Exception e)
        {
            capture.error("Exception occurred in EMI Page Test: " + e.getMessage(), e);
            e.printStackTrace();
            Assert.fail();
        }
    }
}
