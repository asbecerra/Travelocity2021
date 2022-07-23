package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.logging.Logger;

public class HomePage extends BasePage {
    private Logger log = Logger.getLogger("HomePage");
    /**
     * Locators
     */
    @FindBy(xpath = "//a[@href='?pwaLob=wizard-flight-pwa']")
    public WebElement flightsIcon;
    /**
     * Default constructor to create instance of BP class using Page Factory
     *
     * @param driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public FlightsPage clickOnFlightsIcon() {
        log.info("Clicking on Flights Icon... ");
        clickOn(flightsIcon);
        return new FlightsPage(getDriver());
    }

}
