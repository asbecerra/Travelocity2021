package tests;
import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.logging.Logger;

public class BaseTest {
    /**
     *
     */
    String URL = "https://www.travelocity.com/";
    public WebDriver driver;
    protected  HomePage homePage;
    private Logger log = Logger.getLogger("BaseTest");

    /**
     * Set up chrome driver using
     * Webdriver Manager
     */
    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("Chrome") String browser) {
        driver = createDriver(browser);
        log.info("Opening the Webpage");
        driver.get(URL);
        log.info("Maximizing window");
        driver.manage().window().maximize();
        //initializing HomePage
        homePage = new HomePage(driver);
    }

    /**
     * Close the Web page
     */

    @AfterMethod
    public void tearDown() {
        log.info("Closing Webpage");
        driver.quit();
    }

    WebDriver createDriver(String browser) {
        if (browser.equals("Chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }

}
