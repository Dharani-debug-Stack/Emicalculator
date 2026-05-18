package testCases;

import org.testng.annotations.Test;
import pageObject.EMIPricePage;
import testBase.BaseClass;

public class TC_EMIPriceTest extends BaseClass {

    @Test
    public void verifyEMIPricePageFlow() throws Exception {
        EMIPricePage emiPage = new EMIPricePage(driver);

        // Step 1: Click Car Loan Tab
        emiPage.clickCarLoanTab();

        // Step 2: Select Year 2026 schedule + pause + screenshot
        emiPage.selectYear2026();

        // Step 3: Scroll to bottom + pause + screenshot
        emiPage.scrollToBottom();

        System.out.println(" EMI Price Page test executed successfully with screenshots.");
    }
}
