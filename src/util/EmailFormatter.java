package util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class EmailFormatter {
    private final static String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]{3,20}@gmail\\.com$";
    private final static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValid(String email){
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
