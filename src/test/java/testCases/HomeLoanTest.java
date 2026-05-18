package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomeLoanPage;
import pageObject.HomePage;
import testBase.BaseClass;
import utilities.ExcelUtils;

public class HomeLoanTest extends BaseClass
{
    @Test
    public void verifyHomeLoan()
    {
        try
        {
            HomePage hp= new HomePage(driver);
            hp.clickhomeLoan();

            HomeLoanPage home= new HomeLoanPage(driver);
            home.setLoanAmount();
            home.setLoanInterest();
            home.setloanterm();

            //readtable
            ExcelUtils excel= new ExcelUtils("EMI Table");

            excel.write(0,0, "Year");
            excel.write(0,1, "Principal");
            excel.write(0,2, "Interest");
            excel.write(0,3, "Total Payment");
            excel.write(0,4, "Balance");
            excel.write(0,5, "Loan Paid To Date");

            List<WebElement> rows = home.getAllRows();
            int rowNum = 1;  //Excel row index

            for (WebElement row : rows) {

                List<WebElement> cols = home.getColumns(row);

                int colNum = 0;  //excel column index

                for (WebElement col : cols) {
                    String data = col.getText().trim();
                    excel.write(rowNum, colNum++, data);
                }
                rowNum++;
            }
            // Step 3:  Save Excel
            excel.save(System.getProperty("user.dir") + "/src/test/resources/caldata.xlsx");
            Process exec = Runtime.getRuntime().exec("cmd /c start excel "
                    + System.getProperty("user.dir") + "/src/test/resources/caldata.xlsx");

            System.out.println("Table captured successfully!");
        }catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
