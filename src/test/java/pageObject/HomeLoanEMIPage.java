package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomeLoanEMIPage extends BasePage {
    WebDriver driver;

    @FindBy(xpath="//table//tr[contains(@class,'yearlypaymentdetails')]")
    List<WebElement> tableRows;

    public HomeLoanEMIPage(WebDriver driver) {
        super(driver);
    }


    public List<String[]> extractYearlyTable() {
        List<String[]> data = new ArrayList<>();
        for(WebElement row : tableRows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            String[] rowData = cols.stream().map(WebElement::getText).toArray(String[]::new);
            data.add(rowData);
        }
        return data;
    }
}

