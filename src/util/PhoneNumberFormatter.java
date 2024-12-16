package util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class PhoneNumberFormatter {
    private static final String PHONE_NUMBER_PATTERN = "^(25|29|33|44)([1-9]\\d{6})$";

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber);
    }

}
