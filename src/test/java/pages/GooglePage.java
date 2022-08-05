package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class GooglePage extends BasePage{

    public GooglePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    WebElement searchBox;


    public void search(String searchWord){
        Driver.waitAndSendText(searchBox, searchWord);
        Driver.waitAndSendText(searchBox, String.valueOf(Keys.ENTER));

    }
}
