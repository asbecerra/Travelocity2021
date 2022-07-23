package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.logging.Logger;

public class ReturnFlightPage extends BasePage {
    private Logger log = Logger.getLogger("ReturnFlightPage");
    /**
     * Locators
     */
    @FindBy(xpath = "//button[@class = 'uitk-card-link']")
    public List<WebElement> returnFlightList;
    @FindBy(css = "[data-test-id=\"select-button\"]")
    public WebElement continueBtn;
    @FindBy(css = "[class=\"uitk-text uitk-type-left uitk-type-300 uitk-type-bold uitk-step-indicator-step-details-wrapper\"]")
    public WebElement chooseReturningFlightDiv;
    @FindBy(css = "[data-test-id=forcedChoiceNoThanks]")
    public WebElement noThanksBtn;

    /**
     * Constructor
     * @param driver
     */
    public ReturnFlightPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifies that return flight page result has loaded
     */
    public void waitForPageToLoad() {
        isVisible(chooseReturningFlightDiv);
        allElementsVisible(returnFlightList);
    }

    /**
     * Select the third return flight result
     * if a pop up given the option for hotel appears, is going to click on "No, Thanks"
     * Button, and continue the flow
     * @return
     */
    public ReviewYourTripPage selectThirdFlightToReturnToLasVegas(){
        log.info("Selecting the third flight search result to return ");
        WebElement thirdResult = returnFlightList.get(2);
        isClickable(thirdResult);
        clickOn(thirdResult);
        isVisible(continueBtn);
        clickOn(continueBtn);

        try {
            if (noThanksBtn != null) {
                clickOn(noThanksBtn);
                log.info("Cancel Hotel option");
            }

            log.info("Reviewing your trip page");
            return new ReviewYourTripPage(getDriver());
        }
        catch(Exception e) {
            log.info("Reviewing your trip page");
            return new ReviewYourTripPage(getDriver());
        }
    }

}
