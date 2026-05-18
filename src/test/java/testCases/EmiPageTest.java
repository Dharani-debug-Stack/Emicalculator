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

            //loan tanure
            emi.clickLoanamount1();
            emi.setLoanAmount2();
            emi.setLoanInterest2();
            emi.setloanterm2();
            emi.setfee2();
        }catch(Exception e)
        {
            Assert.fail();
        }
    }

}
