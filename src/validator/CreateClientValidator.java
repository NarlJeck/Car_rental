package validator;

import dto.clientDto.CreateRegistrationClientDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import util.EmailFormatter;
import util.PhoneNumberFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateClientValidator implements Validator<CreateRegistrationClientDto> {

    private static CreateClientValidator INSTANCE;

    public static synchronized CreateClientValidator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CreateClientValidator();
        }
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateRegistrationClientDto object) {
        var validationResult = new ValidationResult();
        if (!PhoneNumberFormatter.isValidPhoneNumber(object.getPhoneNumber())) {
            validationResult.add(Error.of("invalid.phone_number", "Phone is invalid"));
        }
        if (!EmailFormatter.isValid(object.getEmail())) {
            validationResult.add(Error.of("invalid.email", "Email is invalid"));
        }
        return validationResult;
    }
}
