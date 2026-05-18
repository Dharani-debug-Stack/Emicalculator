package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        super(driver);
    }

    @FindBy(xpath="//a[normalize-space()='Car Loan']")
    WebElement clickcartLoan;

    @FindBy(xpath="//a[normalize-space()='Home Loan']")
    WebElement clickhomeLoan;

    @FindBy(xpath="//li[@id='menu-item-3009']//a[contains(text(),'Loan Calculator — Calculate EMI, Affordability, Te')]")
    WebElement clickcalculator;

    public void clickcarLoan()
    {
        clickcartLoan.click();
    }
    public void clickhomeLoan(){clickhomeLoan.click();}
    public void clickcalculator(){clickcalculator.click();}
}