package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.BasePage;

public class HomeLoanPage extends BasePage {

    public HomeLoanPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@id='loanamount']")
    WebElement txtLoanAmount;

    @FindBy(xpath="//input[@id='loaninterest']")
    WebElement txtLoanInterest;

    @FindBy(xpath="//input[@id='loanterm']")
    WebElement txtLoanTerm;

    @FindBy(xpath = "//table//tr[contains(@class,'yearlypaymentdetails')]")
   public  List<WebElement> yearlyRows;


    public void setLoanAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOf(txtLoanAmount));
        txtLoanAmount.clear();
        txtLoanAmount.sendKeys(amount);
    }

    public void setLoanInterest(String interest) {
        txtLoanInterest.clear();
        txtLoanInterest.sendKeys(interest);
    }

    public void setLoanTerm(String years) {
        txtLoanTerm.clear();
        txtLoanTerm.sendKeys(years);
        txtLoanTerm.sendKeys(Keys.TAB); // triggers calculation
    }

    public void fillHomeLoanDetails(String amount, String interest, String tenure) {
        setLoanAmount(amount);
        setLoanInterest(interest);
        setLoanTerm(tenure);
    }


   public List<WebElement> getYearlyRows() {
       scrollDown(); // important as table is below
        return yearlyRows;
    }


}