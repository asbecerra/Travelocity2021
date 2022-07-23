package Utils;

/**
 * Allows to store the details and baggage fee info for every a search flight result
 */
public class DetailFlightInfo {
    /**
     * Variables
     */
    private String baggageFees;
    private String showDetails;

    /**
     * Constructor
     * @param showDetails
     * @param baggageFees
     */
    public DetailFlightInfo(String showDetails, String baggageFees) {
        setShowDetails(showDetails);
        setBaggageFees(baggageFees);
    }

    /**
     * getter for Show Details
     * @return
     */
    public String getShowDetails() {
        return showDetails;
    }

    /**
     * Setter for Show details
     * @param showDetails
     */
    public void setShowDetails(String showDetails) {
        this.showDetails = showDetails;
    }

    /**
     * getter for Baggage fee
     * @return
     */
    public String getBaggageFees() {
        return baggageFees;
    }

    /**
     * setter for baggage fee
     * @param baggageFees
     */
    public void setBaggageFees(String baggageFees) {
        this.baggageFees = baggageFees;
    }
}
