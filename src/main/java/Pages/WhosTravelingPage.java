package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.logging.Logger;

public class WhosTravelingPage extends BasePage {
    private Logger log = Logger.getLogger("WhosTravelingPage");

    /**
     * Locators
     */
    @FindBy(id = "summary-container")
    public WebElement roundTripFlightInfo;
    @FindBy(className = "allTravelerDetails")
    public WebElement whosTravelingInfo;
    @FindBy(id = "payments")
    public WebElement paymentDetails;
    @FindBy(id = "complete-booking")
    public WebElement completeBookingBtn;
    @FindBy(id = "user-account")
    public WebElement userAccountHeader;

    /**
     * Constructor
     * @param driver
     */
    public WhosTravelingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify that Round trip flight section is present
     * @return
     */
    public String getRoundTripInfoText() {
        log.info("Verifying Roundtrip flight container is displayed");
        isVisible(roundTripFlightInfo);
        return roundTripFlightInfo.getText();
    }

    /**
     * Verify Who's Traveling information is present
     * @return
     */
    public String getWhosTravelingInfoText() {
        log.info("Verifiying Who's Traveling information is displayed");
        isVisible(whosTravelingInfo);
        return whosTravelingInfo.getText();
    }

    /**
     * Verify Payment details is present
     * @return
     */
    public String getPaymentDetailsText() {
        log.info("Verifying Payment Details is Displayed");
        isVisible(paymentDetails);
        return paymentDetails.getText();
    }

    /**
     * Verify Complete Booking button is present
     * @return
     */
    public String getCompleteBookingBtnText() {
        log.info("Verifying Complete Booking button is Displayed");
        isVisible(completeBookingBtn);
        return completeBookingBtn.getText();
    }

    /**
     * Verify Sing in to book faster option is present
     * @return
     */
    public String getUserAccountHeaderText() {
        log.info("Verifying Sign in to book faster option is Displayed");
        isVisible(userAccountHeader);
        return userAccountHeader.getText();
    }

}
