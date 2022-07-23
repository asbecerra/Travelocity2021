package Pages;

import Utils.DetailFlightInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FlightsSearchPage extends BasePage{
    private Logger log = Logger.getLogger("FlightsSearchPage");
    /**
     * Locators
     */
    @FindBy(id = "listings-sort")
    public WebElement sortByBox;
    @FindBy(css = "button.uitk-card-link")
    public List<WebElement> searchFlightResultList;
    @FindBy(xpath = "//div[@data-test-id='show-details-link']")
    public WebElement detailsFlight;
    @FindBy(css = "[data-test-id=\"baggage-fee-information\"]")
    public WebElement baggageFeesInfo;
    @FindBy(css = "[data-icon=\"tool-close\"]")
    public WebElement closeDetailInfoBtn;
    @FindBy(css = "[data-opt-id=\"DURATION_INCREASING\"]")
    public WebElement shorterDurationOpt;
    @FindBy(css = "[data-test-id=\"journey-duration\"]")
    public List<WebElement> journeyDurationList;
    @FindBy(css = "[data-test-id=\"select-button\"]")
    public WebElement continueBtn;

    /**
     * Constructor
     * @param driver
     */
    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Wait until Sort By  Box is Visible
     */
    public void isVisibleSortByBox() {
        isVisible(sortByBox);
    }
    /**
     * Get Sort by Box Text
     * @return
     */
    public String getSortByText() {
        isVisible(sortByBox);
        return sortByBox.getText();
    }

    /**
     * Allows to verify  if each flight result contains the duration time of flight
     * @return
     */
    public boolean isJourneyDurationVisibleInEachFlightResult() {
        log.info("Verifying that flight duration is present in each search flight results element... ");
        boolean accumulator = true;
        for (WebElement searchFlightResult : searchFlightResultList) {
            WebElement journeyDuration = searchFlightResult.findElement(By.xpath("//*[@data-test-id='journey-duration']"));
            if (journeyDuration != null) {
                accumulator = accumulator && true;
            } else {
                accumulator = accumulator && false;
            }
        }
        return accumulator;
    }

    /**
     * Allows to verify if details and baggage fees, is present in every search flight result
     * @return
     */
    public List<DetailFlightInfo> detailsAndBaggageInfoList() {
        log.info("Verifying details info and baggage fee, is present in every flight result ");
        List<DetailFlightInfo> detailsList = new ArrayList<>();
        for (WebElement searchFlightResult : searchFlightResultList) {
            isClickable(searchFlightResult);
            clickOn(searchFlightResult);
            isVisible(detailsFlight);
            DetailFlightInfo detail = new DetailFlightInfo(detailsFlight.getText(), baggageFeesInfo.getText());
            detailsList.add(detail);
            clickOn(closeDetailInfoBtn);
        }
        return detailsList;
    }

    /**
     * Sort search results flight by shorter durations, and verifies that the sort is correct
     * @return
     */
    public List<Integer> sortFlightSearchResultByDuration() {
        log.info("Sorting Flight Search Result by Shorter Duration");
        clickOn(sortByBox);
        isVisible(shorterDurationOpt);
        clickOn(shorterDurationOpt);
        clickOn(sortByBox);
        allElementsVisible(searchFlightResultList);
        log.info("Converting Flight Search Result duration into minutes ");

        List<Integer> flightDurationArray = new ArrayList<Integer>();
        for (WebElement journeyDuration : journeyDurationList) {
            String duration = journeyDuration.getText().replace("(Nonstop)", "");
            String[] elements = duration.split(" ");
            int totalMinutes = 0;
            if (elements.length > 1) {
                String hoursString = elements[0].replaceAll("[^0-9.]", "");
                String minutesString = elements[1].replaceAll("[^0-9.]", "");

                int hours = Integer.parseInt(hoursString);
                int minutes = Integer.parseInt(minutesString);
                totalMinutes = hours * 60 + minutes;
            } else {
                String minutesString = elements[0].replaceAll("[^0-9.]", "");

                totalMinutes = Integer.parseInt(minutesString);
            }

            flightDurationArray.add(totalMinutes);
        }

        return flightDurationArray;
    }

    public ReturnFlightPage  selectFirstFlight() {
        log.info("Selecting first flight result... ");
        WebElement searchFlightResult = searchFlightResultList.get(0);

        isClickable(searchFlightResult);
        clickOn(searchFlightResult);
        isVisible(continueBtn);
        clickOn(continueBtn);
        return new ReturnFlightPage(getDriver());
    }

}
