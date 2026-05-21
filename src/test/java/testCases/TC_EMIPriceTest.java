package testCases;

import org.testng.annotations.Test;
import pageObject.EMIPricePage;
import testBase.BaseClass;

public class TC_EMIPriceTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void verifyEMIPricePageFlow() throws Exception {

        capture.info("********** EMI Price Page Test Started **********");

        EMIPricePage emiPage = new EMIPricePage(driver);

        // Step 1: Click Car Loan Tab
        emiPage.clickCarLoanTab();
        capture.info("********** Navigated to Car Loan Tab **********");

        // Step 2: Select Year 2026 schedule
        emiPage.selectYear2026();
        capture.info("********** Year 2026 schedule selected **********");

        // Step 3: Scroll to bottom
        emiPage.scrollToBottom();
        capture.info("********** Page scrolled to bottom **********");

        System.out.println(" EMI Price Page test executed successfully with screenshots.");

        capture.info("********** EMI Price Page Test Passed **********");
    }
}
