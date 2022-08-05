package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public final class DriverFactory {
    /*
     *  This method returns new instance of Driver
     *  according to configuration.properties
     * */
    public static WebDriver getDriverInstance() {
        WebDriver driver = null;
        String browser = ConfigReader.getProperty("browser");
        if (browser==null) browser="chrome";
        driver = getDriverInstance(browser);
        return driver;
    }

    public static WebDriver getDriverInstance(String browser) {
        WebDriver driver = null;
        if ("chrome".equals(browser)) {
            driver = getConfiguredChromeDriver();
        } else if ("firefox".equals(browser)) {
            driver = getConfiguredFirefoxDriver();
        } else if ("ie".equals(browser)) {
            driver = getConfiguredIEDriver();
        } else if ("edge".equals(browser)) {
            driver = getConfiguredEdgeDriver();
        } else if ("safari".equals(browser)) {
            driver = getConfiguredSafariDriver();
        } else if ("chrome-headless".equals(browser)) {
            driver = getConfiguredChromeHeadlessDriver();
        } else if ("firefox-headless".equals(browser)) {
            driver = getConfiguredFirefoxHeadlessDriver();
        }
        return driver;
    }

    public static WebDriver getConfiguredChromeDriver() {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        //DesiredCapabilities dc = new DesiredCapabilities();
        //dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.IGNORE);
        driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver getConfiguredFirefoxDriver() {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver getConfiguredIEDriver() {
        WebDriver driver;
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
        return driver;
    }

    public static WebDriver getConfiguredEdgeDriver() {
        WebDriver driver;
        WebDriverManager.edgedriver().setup();
        driver = new InternetExplorerDriver();
        return driver;
    }

    public static WebDriver getConfiguredSafariDriver() {
        WebDriver driver;
        WebDriverManager.getInstance(SafariDriver.class).setup();
        driver = new SafariDriver();
        return driver;
    }

    public static WebDriver getConfiguredChromeHeadlessDriver() {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
        return driver;
    }

    public static WebDriver getConfiguredFirefoxHeadlessDriver() {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
        return driver;
    }
}
