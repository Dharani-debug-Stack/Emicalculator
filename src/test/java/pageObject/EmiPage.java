package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmiPage extends BasePage
{
    public EmiPage(WebDriver driver)
    {
        super(driver);
    }

    //EMI
    @FindBy(xpath="//input[@id='loanamount']")
    WebElement txtloanamount;

    @FindBy(xpath="//input[@id='loaninterest']")
    WebElement txtloaninterest;

    @FindBy(xpath="//input[@id='loanterm']")
    WebElement txtloanterm;

    @FindBy(xpath="//label[normalize-space()='Mo']")
    WebElement clickmonth;

    @FindBy(xpath="//input[@id='loanfees']")
    WebElement txtloanfees;

//EMI
// Loan Amount

    @FindBy(xpath="//a[normalize-space()='Loan Amount Calculator']")
    WebElement clickbtn;

    @FindBy(xpath="//input[@id='loanemi']")
    WebElement txtloanemi;

    @FindBy(xpath="//input[@id='loaninterest']")
    WebElement txtloaninterest1;

    @FindBy(xpath="//input[@id='loanterm']")
    WebElement txtloanterm1;

    @FindBy(xpath="//label[normalize-space()='Mo']")
    WebElement clickmonth1;

    @FindBy(xpath="//input[@id='loanfees']")
    WebElement txtloanfees1;

//LoanAmount

//Loan Tenure

    @FindBy(xpath="//a[normalize-space()='Loan Tenure Calculator']")
    WebElement clcikbtn1;

    @FindBy(xpath="//input[@id='loanamount']")
    WebElement txtloanamount2;

    @FindBy(xpath="//input[@id='loanemi']")
    WebElement txtloanemi1;

    @FindBy(xpath="//input[@id='loaninterest']")
    WebElement txtloaninterest2;

    @FindBy(xpath="//input[@id='loanfees']")
    WebElement txtloanfees2;

//Loan Tenure


    //Emi method
    public void setLoanAmount()
    {
        txtloanamount.click();
        txtloanamount.sendKeys(Keys.CONTROL+"a");
        txtloanamount.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanamount)).sendKeys("1500000");
        txtloanamount.click();
    }

    public void setLoanInterest()
    {
        txtloaninterest.click();
        txtloaninterest.sendKeys(Keys.CONTROL+"a");
        txtloaninterest.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloaninterest)).sendKeys("9.5");
        txtloaninterest.click();
    }

    public void setloanterm()
    {
        txtloanterm.click();
        txtloanterm.sendKeys(Keys.CONTROL+"a");
        txtloanterm.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanterm)).sendKeys("1");
        txtloanterm.click();
        txtloanterm.sendKeys(Keys.ENTER);
    }

    public void setloantermmonth()
    {
        clickmonth.click();
        clickmonth.sendKeys(Keys.ENTER);
    }

    public void setfee()
    {
        txtloanfees.click();
        txtloanfees.sendKeys(Keys.CONTROL+"a");
        txtloanfees.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanfees)).sendKeys("1");
        txtloanfees.click();
        txtloanfees.sendKeys(Keys.ENTER);
    }



//Loan Amount method

    public void clickLoanamount()
    {
        clickbtn.click();
    }
    public void setLoanAmount1()
    {
        txtloanemi.click();
        txtloanemi.sendKeys(Keys.CONTROL+"a");
        txtloanemi.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanemi)).sendKeys("1500000");
        txtloanemi.click();
    }

    public void setLoanInterest1()
    {
        txtloaninterest1.click();
        txtloaninterest1.sendKeys(Keys.CONTROL+"a");
        txtloaninterest1.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloaninterest1)).sendKeys("9.5");
        txtloaninterest1.click();
    }

    public void setloanterm1()
    {
        txtloanterm1.click();
        txtloanterm1.sendKeys(Keys.CONTROL+"a");
        txtloanterm1.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanterm1)).sendKeys("1");
        txtloanterm1.click();
        txtloanterm1.sendKeys(Keys.ENTER);
    }

    public void setloantermmonth1()
    {
        clickmonth1.click();
        clickmonth1.sendKeys(Keys.ENTER);
    }

    public void setfee1()
    {
        txtloanfees1.click();
        txtloanfees1.sendKeys(Keys.CONTROL+"a");
        txtloanfees1.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanfees)).sendKeys("1");
        txtloanfees1.click();
        txtloanfees1.sendKeys(Keys.ENTER);
    }

// Loan Tenure method

    public void clickLoanamount1()
    {
        clcikbtn1.click();
    }
    public void setLoanAmount2()
    {
        txtloanamount2.click();
        txtloanamount2.sendKeys(Keys.CONTROL+"a");
        txtloanamount2.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanamount2)).sendKeys("1500000");
        txtloanamount2.click();
    }

    public void setLoanInterest2()
    {
        txtloanemi1.click();
        txtloanemi1.sendKeys(Keys.CONTROL+"a");
        txtloanemi1.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanemi1)).sendKeys("9.5");
        txtloanemi1.click();
    }

    public void setloanterm2()
    {
        txtloaninterest2.click();
        txtloaninterest2.sendKeys(Keys.CONTROL+"a");
        txtloaninterest2.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloaninterest2)).sendKeys("1");
        txtloaninterest2.click();
        txtloaninterest2.sendKeys(Keys.ENTER);
    }


    public void setfee2()
    {
        txtloanfees2.click();
        txtloanfees2.sendKeys(Keys.CONTROL+"a");
        txtloanfees2.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanfees2)).sendKeys("1");
        txtloanfees2.click();
        txtloanfees2.sendKeys(Keys.ENTER);
    }



}
