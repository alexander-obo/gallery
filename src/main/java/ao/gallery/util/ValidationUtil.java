package ao.gallery.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationUtil {

    private static final int MINIMUM_EMAIL_LENGTH = 6;
    private static final int MAXIMUM_EMAIL_LENGTH = 50;
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.length() < MINIMUM_EMAIL_LENGTH || email.length() > MAXIMUM_EMAIL_LENGTH) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
