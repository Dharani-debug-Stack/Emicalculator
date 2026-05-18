package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CarLoanPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class CarLoanTest extends BaseClass
{

    @Test
    public void verifyCarLoan()
    {
        try
        {

            HomePage hp= new HomePage(driver);
            hp.clickcarLoan();

            CarLoanPage car= new CarLoanPage(driver);
            car.setLoanAmount();
            car.setLoanInterest();
            car.setloanterm();
            car.getInterest();
            car.getTotal();
        }catch(Exception e)
        {
            Assert.fail();
        }
    }
}
