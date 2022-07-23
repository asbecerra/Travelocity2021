package Pages;

import Utils.DateOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import org.openqa.selenium.support.FindBy;

public class FlightsPage extends BasePage {

    private Logger log = Logger.getLogger("FlightsPage");
    /** Locators*/
    @FindBy(css = "button.uitk-faux-input[data-stid='location-field-leg1-origin-menu-trigger']")
    public WebElement leavingFromBtn;
    @FindBy(id = "location-field-leg1-origin")
    public WebElement leavingFromTextField;
    @FindBy(xpath = "//button[@data-stid='location-field-leg1-origin-result-item-button']")
    public WebElement leavingFromResults;
    @FindBy(css = "button.uitk-faux-input[data-stid='location-field-leg1-destination-menu-trigger']")
    public WebElement goingToBtn;
    @FindBy(id = "location-field-leg1-destination")
    public WebElement goingToTextField;
    @FindBy(xpath = "//button[@data-stid='location-field-leg1-destination-result-item-button']")
    public WebElement destinationResults;
    @FindBy(xpath = "(//*[@class = 'uitk-tab-anchor'])[7]")
    public WebElement roundTripOption;
    @FindBy(id = "d1-btn")
    public WebElement departureDateBtn;
    @FindBy(xpath = "(//*[@class = 'uitk-date-picker-month-name uitk-type-medium'])[1]")
    public WebElement datePickerMonth;
    @FindBy(xpath = "(//button[@data-stid = 'date-picker-paging'])[2]")
    public WebElement nextMonthBtn;
    @FindBy(xpath = "//button[@data-stid = 'apply-date-picker']")
    public WebElement doneBtn;
    @FindBy(css = "*[data-testid=\"travelers-field\"]")
    public WebElement travelersField;
    @FindBy(xpath = "(//button[@type = 'submit'])[1]")
    public WebElement submitBtn;

    /**
     * Constructor
     * @param driver
     */
    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    /**
     *Click on Leaving from button
     */
    public void clickOnLeavingFromBtn() {
        clickOn(leavingFromBtn);
    }

    /**
     * Allows to enter the Origin City name
     * @param fromCity
     */
    public void setLeavingFromCityName(String fromCity) {
        log.info("Entering origin city name " + fromCity );
        sendKeys(leavingFromTextField, fromCity);
    }

    /**
     * Select the first element in the list of results
     */
    public void clickOnLeavingFromResults() {
        log.info("Selecting origin airport");
        clickOn(leavingFromResults);
    }

    /**
     * Click on Going To Button
     */
    public void clickOnGoingToBtn() {
        clickOn(goingToBtn);
    }

    /**
     *
     * Allows to enter the Destination city name
     * @param toCity
     */
    public void setGoingToCityName(String toCity) {
        log.info("Entering destination city name " + toCity );
        sendKeys(goingToTextField, toCity);
    }

    /**
     * Allows to select first result
     */
    public void clickOnDestinationResults() {
        log.info("Selecting destination airport");
        clickOn(destinationResults);
    }
    /**
     * Click on DEparture DAte button
     */
    public void clickOnDepartureDateButton() {
        log.info("Clicking on departure date button");
        clickOn(departureDateBtn);
    }

    /**
     * Return the first month in the picker (left one)
     * @return
     */
    public String getInitialMonthOfDatePickerText() {
        isVisible(datePickerMonth);
        return datePickerMonth.getText();
    }

    /**
     * Select departure and return dates
     * @param date
     */
    public void selectDepartureDate(LocalDate date) {
        log.info("Selecting departure date" + date) ;
        String monthAndYear = DateOperations.monthAndYearFormat(date);

        while(true) {
            if (getInitialMonthOfDatePickerText().equals(monthAndYear)) {
                break;
            } else {
                clickOn(nextMonthBtn);
            }
        }

        String dayOfMonth = String.valueOf(date.getDayOfMonth());
        WebElement monthTable = getDriver().findElement(By.xpath("(//table[@class = 'uitk-date-picker-weeks'])[1]"));

        List<WebElement> buttonsList = monthTable.findElements(By.xpath("//button[@class='uitk-date-picker-day uitk-new-date-picker-day']"));

        for (WebElement button : buttonsList) {
            String dayAttribute = button.getAttribute("data-day");

            if (dayAttribute.equals(dayOfMonth)) {
                clickOn(button);
                log.info("Departure day selected +");
                break;
            }
        }

    }

    /**
     * CLick on done button to confirm dates selected
     */
    public void clickDoneOnCalendarPicker() {
        clickOn(doneBtn);
    }

    /**
     * Verifies thaT Round trip is selected
     * @return
     */
    public boolean isRoundTripSelected() {
        return isAriaSelected(roundTripOption);
    }

    /**
     * Obtains the text inside Travelers field
     * @return
     */
    public String getTravelerFieldText() {
        log.info("Verifying travelers number");
        return travelersField.getText();
    }

    /**
     * Obtains Departure date Text
     * @return
     */
    public String getDepartingDateText() {
        log.info("Verifying Departure date");
        return departureDateBtn.getText();
    }

    /**
     * Click on Submit Button
     * @return
     */
    public FlightsSearchPage clickOnSubmitBtn() {
        log.info("Clicking on Submit Button");
        clickOn(submitBtn);
        return new FlightsSearchPage(getDriver());
    }

}
