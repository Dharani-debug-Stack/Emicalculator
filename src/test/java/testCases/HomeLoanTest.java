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



            // Step 2: Extract yearly table
            List<String[]> yearlyData = home.extractYearlyTable();

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
            double expectedLoanAmount = Double.parseDouble(
                    home.getLoanAmountValue().replace("₹","").replace(",","").trim()
            );
            Assert.assertEquals(totalPrincipal, expectedLoanAmount, 1.0, "Principal mismatch!");



        }catch(Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
