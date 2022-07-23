package Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateOperations {
    private static DateTimeFormatter monthYearFormat = DateTimeFormatter.ofPattern("MMMM yyyy");
    private static DateTimeFormatter monthDayFormat = DateTimeFormatter.ofPattern("MMM d");
    private static DateTimeFormatter yearMonthDayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static String monthAndYearFormat(LocalDate date) {
        return date.format(monthYearFormat);
    }

    public static LocalDate addTwoMonths(LocalDate date) {
        return date.plusMonths(2);
    }

    public static String monthDayFormat(LocalDate date) {
        return date.format(monthDayFormat);
    }

    public static LocalDate departureDateFrom(String departureDate) {
        LocalDate defaultDate = getCurrentDate().plusMonths(2);

        try {
            LocalDate dateTime = LocalDate.parse(departureDate, yearMonthDayFormat);

            if (dateTime.isAfter(defaultDate)) {
                return dateTime;
            } else {
                return defaultDate;
            }
        } catch (Exception e) {
            return defaultDate;
        }
    }

}
