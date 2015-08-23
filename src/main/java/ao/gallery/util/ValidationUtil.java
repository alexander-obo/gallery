package ao.gallery.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class ValidationUtil {

    private static final int MINIMUM_EMAIL_LENGTH = 6;
    private static final int MAXIMUM_EMAIL_LENGTH = 50;
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static final int MINIMUM_LOGIN_LENGTH = 3;
    private static final int MAXIMUM_LOGIN_LENGTH = 20;

    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    private static final int MAXIMUM_PASSWORD_LENGTH = 20;

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

    public static boolean isLoginValid(String login) {
        if (StringUtils.isBlank(login)) {
            return false;
        }
        return !(login.length() < MINIMUM_LOGIN_LENGTH || login.length() > MAXIMUM_LOGIN_LENGTH);
    }

    public static boolean isPasswordValid(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        return !(password.length() < MINIMUM_PASSWORD_LENGTH || password.length() > MAXIMUM_PASSWORD_LENGTH);
    }
}