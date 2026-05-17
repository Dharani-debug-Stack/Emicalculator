package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

    public class EmiPage extends BasePage {

        public EmiPage(WebDriver driver){
            super(driver);
        }

        //Input fields
        @FindBy(id = "loanamount")
        WebElement loanAmount;

        @FindBy(id ="loaninterest")
        WebElement loanInterest;

        @FindBy(id ="loanterm")
        WebElement loanTenure;

        //Emi result
        @FindBy (xpath ="//*[@id='emiamount']/p/span")
        WebElement emiValue;

        //Enter loan details
        public void enterLoanDetails(String amount, String interest, String tenure)
        {
            loanAmount.clear();
            loanAmount.sendKeys(amount);

            loanInterest.clear();
            loanInterest.sendKeys(interest);

            loanTenure.clear();
            loanTenure.sendKeys(tenure);
        }

        //Get EMI from UI
        public double getEMIFromUI()
        {
            String emi = emiValue.getText();

            // Remove ₹, commas, spaces
            emi = emi.replaceAll("[^0-9.]", "");

            return Double.parseDouble(emi);
        }

    }


