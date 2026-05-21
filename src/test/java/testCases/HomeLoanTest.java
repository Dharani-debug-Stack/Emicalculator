package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomeLoanPage;
import testBase.BaseClass;
import utilities.ExcelUtils;

import java.util.List;

public class HomeLoanTest extends BaseClass {

    @Test(groups = {"Regression","Master","Sanity"})
    public void verifyHomeLoanEMICalculator() {

        capture.info("********** Home Loan EMI Test Started **********");

        try {

            HomeLoanPage home = new HomeLoanPage(driver);

            //Navigate
            home.navigateToHomeLoanCalculator();

            //Enter Details
            home.setHomeValue();
            home.setDownPayment();
            home.setInsurance();
            home.setInterest();
            home.setTenure();
            home.setFees();

            capture.info("********** Input details entered successfully **********");

            //Validate Table Generated
            boolean result = home.isTableGenerated();
            Assert.assertTrue(result, "EMI table not generated");

            capture.info("********** EMI table generated successfully **********");

            //Extract Table Data
            List<String[]> yearlyData = home.extractYearlyTable();
            double totalPrincipal = 0.0;

            for (String[] row : yearlyData) {
                totalPrincipal += Double.parseDouble(
                        row[1].replace("₹", "").replace(",", "").trim()
                );
            }

            //Calculation + Validation
            double homeValue = Double.parseDouble(
                    home.getHomeValue().replace("₹", "").replace(",", "").trim()
            );

            double downPaymentPercent = Double.parseDouble(home.getDownPayment());
            double insurance = Double.parseDouble(
                    home.getInsurance().replace("₹", "").replace(",", "").trim()
            );

            double downPayment = homeValue * (downPaymentPercent / 100.0);
            double expectedLoanAmount = homeValue + insurance - downPayment;

            Assert.assertEquals(totalPrincipal, expectedLoanAmount, 1.0,
                    "Principal mismatch");

            capture.info("********** Loan calculation validated successfully **********");

            //Excel Write
            ExcelUtils excel = new ExcelUtils("EMI Table");

            excel.write(0,0,"Year");
            excel.write(0,1,"Principal");
            excel.write(0,2,"Interest");
            excel.write(0,3,"Taxes, Home Insurance & Maintenance");
            excel.write(0,4,"Total Payment");
            excel.write(0,5,"Balance");
            excel.write(0,6,"Loan Paid To Date");

            List<WebElement> rows = home.getAllRows();
            int rowNum = 1;

            for (WebElement row : rows) {
                List<WebElement> cols = home.getColumns(row);
                int colNum = 0;

                for (WebElement col : cols) {
                    excel.write(rowNum, colNum++, col.getText().trim());
                }
                rowNum++;
            }

            excel.save(System.getProperty("user.dir")+"/src/test/resources/caldata.xlsx");

            Runtime.getRuntime().exec(
                    "cmd /c start excel \"" +
                            System.getProperty("user.dir") + "/src/test/resources/caldata.xlsx\""
            );

            Thread.sleep(5000);

            Runtime.getRuntime().exec("taskkill /f /im excel.exe");

            capture.info("********** Excel file generated and verified **********");
            capture.info("********** Home Loan EMI Test Passed **********");

        } catch(Exception e)
        {
            capture.error("********** Home Loan Test Failed **********", e);
            Assert.fail();
        }
    }
}

//            //  FIX: Unique filename (avoids lock issue)
//            String filePath = System.getProperty("user.dir")
//                    + "/src/test/resources/caldata_"
//                    + System.currentTimeMillis() + ".xlsx";
//
//            excel.save(filePath);
//
//            System.out.println("Excel saved at: " + filePath);
//
//            // OPTIONAL: Open file safely
//            try {
//                if (java.awt.Desktop.isDesktopSupported()) {
//                    java.awt.Desktop.getDesktop().open(new java.io.File(filePath));
//                }
//            } catch (Exception e) {
//                System.out.println("⚠ Unable to open Excel file automatically");
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            Assert.fail("Test failed due to exception: " + e.getMessage());
//        }
//    }
//}