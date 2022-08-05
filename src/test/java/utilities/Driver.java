package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class Driver {
    //create a driver instance
    private static WebDriver driver;
    private static final int TIMEOUT = 5;
    private static final int IMPLICITLY_WAIT_TIMEOUT=10;

    //What?=>It is just to create, initialize the driver instance.(Singleton driver)
    //Why?=>We don't want to create and initialize the driver when we don't need
    //We will create and initialize the driver when it is null
    //We can use Driver class with different browser(chrome,firefox,headless)
    private Driver() {
        //we don't want to create another object. Singleton pattern
    }

    //to initialize the driver we create a static method
    public static WebDriver getDriver() {
        //create the driver if and only if it is null
        if (driver == null) {
            driver = DriverFactory.getDriverInstance();
            //driver = DriverFactory.getDriverInstance(ConfigReader.getProperty("browser"));
            //with above usage you can select browser to execute your script
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_TIMEOUT));
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {//if the driver is pointing chrome
            driver.quit();//quit the driver
            driver = null;//set it back to null to make sure driver is null
            // so I can initialize it again
            //This is important especially you do cross browser testing(testing with
            // multiple browser like chrome, firefox, ie etc.)
        }
    }

    public static void waitAndClick(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static void waitAndClick(WebElement element) {
        for (int i = 0; i < TIMEOUT; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static void waitAndSendText(WebElement element, String text, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    //    Driver.waitANdSendText(Element , "TEXT");
    public static void waitAndSendText(WebElement element, String text) {
        for (int i = 0; i < TIMEOUT; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static void waitAndSendTextWithDefaultTime(WebElement element, String text) {
        for (int i = 0; i < TIMEOUT; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static String waitAndGetText(WebElement element, int timeout) {
        String text = "";
        for (int i = 0; i < timeout; i++) {
            try {
                text = element.getText();
                return text;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
        return null;
    }

    public static void wait2(int sec) {
        try {
            Thread.sleep(1000 * sec);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (ElementClickInterceptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //This is hard waited web element click
    public static void waitAndClickElement(WebElement element, int seconds) {
        for (int i = 0; i < seconds; i++) {
            try {
                element.click();
                break;
            } catch (Exception e) {
                wait2(1);
            }


        }
    }
    //this is hard wait
    public static void wait(int secs) {
        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Alert waitForAlertIsPresent(){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static Boolean waitForInVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickabilility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(TIMEOUT));
            wait.until(expectation);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command, element);
    }

    public static void selectAnItemFromDropdown(WebElement item, String selectableItem) {
        wait(5);
        Select select = new Select(item);
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equalsIgnoreCase(selectableItem)) {
                select.getOptions().get(i).click();
                break;
            }
        }

    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param elements
     */
    public static void clickWithJSAsList(List<WebElement> elements) {
        for (WebElement each : elements) {
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForVisibility(each, 5));
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", each);
        }
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    //    Parameter1 : WebElement
//    Parameter2:  String
//    Driver.selectByVisibleText(dropdown element, "CHECKING-91303-116.98$")
    public static void selectByVisibleText(WebElement element, String text) {
        Select objSelect = new Select(element);
        objSelect.selectByVisibleText(text);
    }

    //    Parameter1 : WebElement
//    Parameter2:  int
//    Driver.selectByIndex(dropdown element, 1)
    public static void selectByIndex(WebElement element, int index) {
        Select objSelect = new Select(element);
        objSelect.selectByIndex(index);
    }

    //    Parameter1 : WebElement
//    Parameter2:  String
//    Driver.selectByIndex(dropdown element, "91303")
    public static void selectByValue(WebElement element, String value) {
        Select objSelect = new Select(element);
        List<WebElement> elementCount = objSelect.getOptions();
        objSelect.selectByValue(value);
        System.out.println("number of elements: " + elementCount.size());
    }

    public static void sleep(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitAndClickLocationText(WebElement element, String value) {
        Driver.getDriver().findElement(By.xpath("//*[text()='" + value + "']")).click();
    }

    public static void scrollIntoView(WebElement element){
        executeJScommand(element,"arguments[0].scrollIntoView(true);");
    }
    public static void setValueByJS(WebElement element, String value){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value='"+value+"';", element);
    }

    public static void clickPageDown(){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public static void clickByHoverOver(WebElement element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element)
                .click().build().perform();
    }
}