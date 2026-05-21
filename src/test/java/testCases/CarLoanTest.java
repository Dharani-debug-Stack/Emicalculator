package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CarLoanPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class CarLoanTest extends BaseClass
{

    @Test(groups = {"Sanity","Master"})
    public void verifyCarLoan()
    {
        capture.info("********** Starting Car Loan Test **********");

        try
        {
            capture.info("Creating HomePage object");
            HomePage hp = new HomePage(driver);

            capture.info("Clicking on Car Loan link");
            hp.clickcarLoan();

            capture.info("Creating CarLoanPage object");
            CarLoanPage car = new CarLoanPage(driver);

            capture.info("Setting Loan Amount");
            car.setLoanAmount();

            capture.info("Setting Loan Interest");
            car.setLoanInterest();

            capture.info("Setting Loan Term");
            car.setloanterm();

            capture.info("Fetching Interest details");
            car.getInterest();

            capture.info("Fetching Total amount");
            car.getTotal();

            capture.info("Fetching EMI value");
            double emi = car.fetchEMI();
            capture.info("EMI Value: " + emi);

            Assert.assertTrue(emi > 0);
            capture.info("EMI is greater than 0 - Validation Passed");

            capture.info("Validating EMI calculation");
            boolean status = car.validateEMI();
            Assert.assertTrue(status);

            capture.info("EMI validation successful");

            capture.info("********** Car Loan Test Passed **********");

        }
        catch(Exception e)
        {
            capture.error("Exception occurred in Car Loan Test: " + e.getMessage(), e);
            Assert.fail();
        }
    }
}