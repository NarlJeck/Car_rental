package util;

import exception.DateSelectException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class LocalDateFormatter {
    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalDate format(String date) {

        return LocalDate.parse(date, FORMATTER);
    }


    public boolean checkDate(LocalDate dateStart, LocalDate dateEnd) {

        boolean isDateParseAfterOrEqual = dateEnd.isEqual(dateStart) || dateEnd.isAfter(dateStart);
        System.out.println("Сравнение дат конца и начала _______)" + dateEnd.isEqual(dateStart));
        System.out.println("Проверка даты конца что идет за датой старта__________" + dateEnd.isAfter(dateStart));

        if (isDateParseAfterOrEqual) {
            return true;
        }
        return false;

    }

    public boolean isValidDateSelection(LocalDate dateStart, LocalDate dateEnd) {

        return checkDate(dateStart, dateEnd);
    }

    public boolean isValidFormat(String date) {
        try {
            format(date);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }
}
