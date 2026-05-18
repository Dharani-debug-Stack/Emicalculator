package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeLoanPage extends BasePage
{
    public HomeLoanPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//input[@id='loanamount']")
    WebElement txtloanamount;

    @FindBy(xpath="//input[@id='loaninterest']")
    WebElement txtloaninterest;

    @FindBy(xpath="//input[@id='loanterm']")
    WebElement txtloanterm;

    @FindBy(xpath="//table//tr[contains(@class,'yearlypaymentdetails')]")
    List<WebElement> allRows;

    public void setLoanAmount()
    {
        txtloanamount.click();
        txtloanamount.sendKeys(Keys.CONTROL+"a");
        txtloanamount.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtloanamount)).sendKeys("1500000");
        txtloanamount.click();
    }

    public String getLoanAmountValue() {
        return txtloanamount.getAttribute("value");
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

    public List<WebElement> getAllRows() {
        wait.until(ExpectedConditions.visibilityOfAllElements(allRows));
        return allRows;
    }

    public List<WebElement> getColumns(WebElement row) {
        List<WebElement> cols = row.findElements(By.tagName("td"));

        if (cols.size() == 0) {
            cols = row.findElements(By.tagName("th")); // header
        }
        return cols;
    }

    public List<String[]> extractYearlyTable() {
        List<String[]> data = new ArrayList<>();
        scrollDown();
        for(WebElement row : allRows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            String[] rowData = cols.stream().map(WebElement::getText).toArray(String[]::new);
            data.add(rowData);
        }
        return data;
    }

}
