package tests;

import Pages.*;
import Utils.Constants;
import Utils.DateOperations;
import Utils.DetailFlightInfo;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class TravelocityTests extends BaseTest {

    @Parameters("departureDate")
    @Test
    public void bookingRoundTripFlightTest(@Optional("2021-07-20") String departureDate) {
        LocalDate departureDateObject = DateOperations.departureDateFrom(departureDate);

        FlightsPage flightsPage = homePage.clickOnFlightsIcon();
        flightsPage.clickOnLeavingFromBtn();
        flightsPage.setLeavingFromCityName(Constants.FROMCITY);
        flightsPage.clickOnLeavingFromResults();
        flightsPage.clickOnGoingToBtn();
        flightsPage.setGoingToCityName(Constants.TOCITY);
        flightsPage.clickOnDestinationResults();
        flightsPage.clickOnDepartureDateButton();

        flightsPage.selectDepartureDate(departureDateObject);
        flightsPage.clickDoneOnCalendarPicker();
        Assert.assertTrue(flightsPage.isRoundTripSelected(),"Round Trip is not selected");
        Assert.assertEquals(flightsPage.getTravelerFieldText(), "1 traveler", "The number of passengers is  more that 1 adult");

        String departureDateString = DateOperations.monthDayFormat(departureDateObject);
        Assert.assertEquals(flightsPage.getDepartingDateText(), departureDateString, "The current dates are not matching");
        FlightsSearchPage flightsSearchPage = flightsPage.clickOnSubmitBtn();
        Assert.assertTrue(flightsSearchPage.getSortByText().contains("Price"), "Sort By Box does not allows to order by Price");
        Assert.assertTrue(flightsSearchPage.getSortByText().contains("Departure"), "Sort By Box does not allows to order by Departure");
        Assert.assertTrue(flightsSearchPage.getSortByText().contains("Arrival"), "Sort By Box does not allows to order by Arrival");
        Assert.assertTrue(flightsSearchPage.getSortByText().contains("Duration"), "Sort By Box does not allows to order by Duration");
        Assert.assertTrue(flightsSearchPage.isJourneyDurationVisibleInEachFlightResult());

       List<DetailFlightInfo> detailsInformation = flightsSearchPage.detailsAndBaggageInfoList();
        for (DetailFlightInfo detail : detailsInformation) {
            Assert.assertTrue(detail.getShowDetails().contains("Show details"));
            Assert.assertTrue(detail.getBaggageFees().contains("checked bag"));
        }

        List<Integer> flightDurationArray = flightsSearchPage.sortFlightSearchResultByDuration();
        Assert.assertEquals(flightDurationArray, flightDurationArray.stream().sorted().collect(Collectors.toList()),
                "Flights are not sorted from minor to major duration.");

        ReturnFlightPage returnFlightPage = flightsSearchPage.selectFirstFlight();
        returnFlightPage.waitForPageToLoad();

        ReviewYourTripPage reviewYourTripPage = returnFlightPage.selectThirdFlightToReturnToLasVegas();
        Assert.assertTrue(reviewYourTripPage.getTotalPriceText().contains("Trip total"),  "Total Price is not present");

        LocalDate returnDate = departureDateObject.plusDays(1);
        String returnDateString = DateOperations.monthDayFormat(returnDate);
        Assert.assertTrue(reviewYourTripPage.getDepartureTravelInfoText().contains(departureDateString),
                "Departure travel info is not present");
        Assert.assertTrue(reviewYourTripPage.getReturnTravelInfoText().contains(returnDateString),
                "Return travel info is not present");
        WhosTravelingPage whosTravelingPage = reviewYourTripPage.clickOnCheckOutBtn();
        Assert.assertTrue(whosTravelingPage.getRoundTripInfoText().contains("Roundtrip flight"),
                "Roundtrip flight information is not present");
        Assert.assertTrue(whosTravelingPage.getWhosTravelingInfoText().contains("Who's traveling?"),
                "Who's Traveling information is not present");
        Assert.assertTrue(whosTravelingPage.getPaymentDetailsText().contains("How would you like to pay?"),
                "Payment Details information is not present");
        Assert.assertTrue(whosTravelingPage.getUserAccountHeaderText().contains("Sign in to book faster"),
                "Sing in to book faster options in not present");
        Assert.assertTrue(whosTravelingPage.getCompleteBookingBtnText().contains("Complete Booking"),
                "Complete Booking button is not present");
    }
}
