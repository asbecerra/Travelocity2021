package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReviewYourTripPage extends BasePage {
    private Logger log = Logger.getLogger("ReviewYourTripPage");
    /**
     * Locators
     */
    @FindBy(css = "[data-test-id=\"trip-total\"]")
    public WebElement totalPriceFlight;
    @FindBy(css = "[data-test-id=\"flight-review-header\"]")
    public List<WebElement> travelFlightInfo;
    @FindBy(css = "[data-test-id=\"goto-checkout-button\"]")
    public WebElement checkOutBtn;
    /**
     * Constructor
     * @param driver
     */
    public ReviewYourTripPage(WebDriver driver) {
        super(driver);
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }

    /**
     * Verify that Total Price is present
     * @return
     */
    public String getTotalPriceText() {
        log.info("Verifying Total Price is present ...");
        isVisible(totalPriceFlight);
        return totalPriceFlight.getText();
    }

    /**
     * Verifies that Departure Travel Information is present
     * @return
     */
    public String getDepartureTravelInfoText() {
        log.info("Verifying Departure Travel information is present");
       WebElement departureTravelInfo = travelFlightInfo.get(0);
       isVisible(departureTravelInfo);
       return departureTravelInfo.getText();
    }

    /**
     * Verifies that Return Travel Information is present
     * @return
     */
    public String getReturnTravelInfoText() {
        log.info("Verifying Return Travel information is present");
       WebElement returnTravelInfo = travelFlightInfo.get(1);
       isVisible(returnTravelInfo);
       return returnTravelInfo.getText();
    }

    /**
     * Click on Check Out Button
     * @return
     */
    public WhosTravelingPage clickOnCheckOutBtn(){
        log.info("Clicking on Check out Button");
        clickOn(checkOutBtn);
        return new WhosTravelingPage(getDriver());
    }

}
