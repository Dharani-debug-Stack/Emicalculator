package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomeLoanPage;
import testBase.BaseClass;
import utilities.ExcelUtils;

import java.util.List;

public class HomeLoanTest extends BaseClass {

    @Test
    public void verifyHomeLoanEMICalculator() {

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

            //Validate Table Generated
            boolean result = home.isTableGenerated();
            Assert.assertTrue(result, "EMI table not generated");

            System.out.println("Home Loan EMI table generated successfully");

            //Extract Table Data
            List<String[]> yearlyData = home.extractYearlyTable();

            double totalPrincipal = 0.0;

            for (String[] row : yearlyData) {

                System.out.println(String.join(" | ", row));

                totalPrincipal += Double.parseDouble(
                        row[1].replace("₹", "").replace(",", "").trim()
                );
            }

            //Calculation
            double homeValue = Double.parseDouble(
                    home.getHomeValue().replace("₹", "").replace(",", "").trim()
            );

            double downPaymentPercent = Double.parseDouble(home.getDownPayment());
            double insurance = Double.parseDouble(
                    home.getInsurance().replace("₹", "").replace(",", "").trim()
            );

            double downPayment = homeValue * (downPaymentPercent / 100.0);
            double expectedLoanAmount = homeValue + insurance - downPayment;

            //Validation
            Assert.assertEquals(totalPrincipal, expectedLoanAmount, 1.0,
                    "Principal mismatch");

            System.out.println("HomeLoanEMI validation successful!");

            //Excel Write (FIXED)
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

            //Save Excel
            excel.save(System.getProperty("user.dir")+"/src/test/resources/caldata.xlsx");
            Runtime.getRuntime().exec(
                    "cmd /c start excel \"" +
                            System.getProperty("user.dir") + "/src/test/resources/caldata.xlsx\""
            );

            // Wait (user can see file)
            Thread.sleep(5000);

            // Close Excel
            Runtime.getRuntime().exec("taskkill /f /im excel.exe");

                    System.out.println("Table captured successfully!");
            System.out.println("Excel opened and closed successfully!");
        }catch(Exception e)
        {
            Assert.fail();
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
    }
}