package validator;

import dto.orderDto.OrderRentalDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.LocalDateFormatter;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidatorOrder implements Validator<OrderRentalDto> {

    private static ValidatorOrder INSTANCE;

    public static synchronized ValidatorOrder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ValidatorOrder();
        }
        return INSTANCE;
    }

    private final LocalDate NOW_DATE = LocalDate.now();

    public ValidationResult isValidSelectDate(OrderRentalDto orderRentalDto) {
        var validationResult = new ValidationResult();
        LocalDate startDate = LocalDateFormatter.format(orderRentalDto.getRentalStartDate());
        LocalDate endDate = LocalDateFormatter.format(orderRentalDto.getRentalEndDate());

        if (!LocalDateFormatter.isValidDateSelection(NOW_DATE, startDate)) {
            validationResult.add(Error.of("invalid.startDate", "Selected start date is valid"));
        }
        if (!LocalDateFormatter.isValidDateSelection(startDate, endDate)) {
            validationResult.add(Error.of("invalid.endDate", "Selected end date is valid"));
        }
        return validationResult;

    }

    @Override
    public ValidationResult isValid(OrderRentalDto object) {

        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValidFormat(object.getRentalStartDate())) {
            validationResult.add(Error.of("invalid.startDate", "Start date is valid"));
        }
        if (!LocalDateFormatter.isValidFormat(object.getRentalEndDate())) {
            validationResult.add(Error.of("invalid.endDate", "End date is valid"));
        }
        return validationResult;
    }
}
