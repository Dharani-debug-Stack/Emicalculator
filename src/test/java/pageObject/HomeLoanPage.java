package pageObject;
import java.util.ArrayList;


import org.openqa.selenium.By;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomeLoanPage extends BasePage {

    public HomeLoanPage(WebDriver driver) {
        super(driver);
    }

    //  MAIN MENU
    @FindBy(id = "menu-item-dropdown-2696")
    WebElement loanMenu;

    //  FIRST CLICK (IMPORTANT)
    @FindBy(xpath = "//a[@href='https://emicalculator.net/loan-calculator/']")
    WebElement loanCalculatorLink;

    //  SECOND NAVIGATION (HOME LOAN PAGE LINK)
    @FindBy(xpath = "//a[@href='https://emicalculator.net/home-loan-emi-calculator/']")
    WebElement homeLoanLink;

    //  INPUT FIELDS (FROM YOUR INSPECT)
    @FindBy(id = "homeprice")
    WebElement txtHomeValue;

    @FindBy(id = "downpayment")
    WebElement txtDownPayment;

    @FindBy(id = "homeloaninsuranceamount")
    WebElement txtInsurance;

    @FindBy(id = "homeloaninterest")
    WebElement txtInterest;

    @FindBy(id = "homeloanterm")
    WebElement txtTenure;

    @FindBy(id = "loanfees")
    WebElement txtFees;

    //  YEAR-WISE TABLE
    @FindBy(xpath = "//table//tr[contains(@class,'yearlypaymentdetails')]")
    List<WebElement> yearlyRows;

    public void navigateToHomeLoanCalculator() {

        //  Direct navigation (stable and correct)
        driver.get("https://emicalculator.net/home-loan-emi-calculator/");
    }

    //  ENTER VALUES

    public void setHomeValue() {
        txtHomeValue.click();
        txtHomeValue.sendKeys(Keys.CONTROL + "a");
        txtHomeValue.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtHomeValue)).sendKeys("6500000");
    }

    public String getHomeValue() {
        return txtHomeValue.getAttribute("value");
    }

    public void setDownPayment() {
        txtDownPayment.click();
        txtDownPayment.sendKeys(Keys.CONTROL + "a");
        txtDownPayment.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtDownPayment)).sendKeys("20");
    }

    // Get Down Payment
    public String getDownPayment() {
        return txtDownPayment.getAttribute("value");
    }



    public void setInsurance() {
        txtInsurance.click();
        txtInsurance.sendKeys(Keys.CONTROL + "a");
        txtInsurance.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtInsurance)).sendKeys("170000");
    }

    // Get Insurance
    public String getInsurance() {
        return txtInsurance.getAttribute("value");
    }

    public void setInterest() {
        txtInterest.click();
        txtInterest.sendKeys(Keys.CONTROL + "a");
        txtInterest.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtInterest)).sendKeys("10");
    }

    public void setTenure() {
        txtTenure.click();
        txtTenure.sendKeys(Keys.CONTROL + "a");
        txtTenure.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtTenure)).sendKeys("2");
        txtTenure.sendKeys(Keys.TAB); // trigger calc
    }

    public void setFees() {
        txtFees.click();
        txtFees.sendKeys(Keys.CONTROL + "a");
        txtFees.sendKeys(Keys.DELETE);
        wait.until(ExpectedConditions.visibilityOf(txtFees)).sendKeys("0.25");
    }

    //  VALIDATE TABLE

    public boolean isTableGenerated() {
        wait.until(ExpectedConditions.visibilityOfAllElements(yearlyRows));
        return yearlyRows.size() > 0;
    }
//Extract yearly from table


    public List<String[]> extractYearlyTable() {
        scrollDown(); // scroll before reading
        List<String[]> data = new ArrayList<>();
        for (WebElement row : yearlyRows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            String[] rowData = cols.stream().map(WebElement::getText).toArray(String[]::new);
            data.add(rowData);
        }
        return data;
    }

    public List<WebElement> getAllRows() {
        return yearlyRows;
    }

    public List<WebElement> getColumns(WebElement row) {
        List<WebElement> cols = row.findElements(By.tagName("td"));

        if (cols.size() == 0) {
            cols = row.findElements(By.tagName("th")); // header
        }
        return cols;
    }

}
