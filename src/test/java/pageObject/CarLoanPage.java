package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CarLoanPage extends BasePage
{
    public CarLoanPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//input[@id='loanamount']")
    WebElement txtloanamount;

    @FindBy(xpath="//input[@id='loaninterest']")
    WebElement txtloaninterest;

    @FindBy(xpath="//input[@id='loanterm']")
    WebElement txtloanterm;

    @FindBy(xpath="//*[@id=\"emitotalinterest\"]/p/span")
    WebElement Interest;

    @FindBy(xpath="//*[@id=\"emitotalamount\"]/p")
    WebElement Total;

    public void setLoanAmount()
    {
        txtloanamount.click();
        txtloanamount.sendKeys(Keys.CONTROL+"a");
        txtloanamount.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf( txtloanamount)).sendKeys("1500000");
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
        wait.until(ExpectedConditions.visibilityOf( txtloanterm)).sendKeys("1");
        txtloanterm.click();
        txtloanterm.sendKeys(Keys.ENTER);
    }

    public void getInterest()
    {
        String inter=Interest.getText();
        System.out.println(inter);
        inter=inter.replaceAll("[^0-9.]","");
        double inter1=Double.parseDouble(inter);
        double inter2=inter1/12;
        System.out.println("Interest amount for one month = "+inter2);
    }

    public void getTotal()
    {

        String total=Total.getText();
        total=total.replaceAll("[^0-9.]","");
        double total1=Double.parseDouble(total);
        String inter=Interest.getText();
        inter=inter.replaceAll("[^0-9.]","");
        double inter1=Double.parseDouble(inter);
        double total2=total1-inter1;
        System.out.println(total2);
        double total3=total2/12;
        System.out.println("principal amount for one month = "+total3);
    }
}

