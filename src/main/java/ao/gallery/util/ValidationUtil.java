package ao.gallery.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public final class ValidationUtil {

    private static final int MINIMUM_EMAIL_LENGTH = 6;
    private static final int MAXIMUM_EMAIL_LENGTH = 50;
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static final String LOGIN_DELIMITERS = ".-_";
    private static final String LOGIN_REGEX = "^[A-Za-z\\d][A-Za-z\\d\\.\\-_]{1,18}[A-Za-z\\d]$";
    private static final Pattern LOGIN_PATTERN = Pattern.compile(LOGIN_REGEX);

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
        if (login == null) {
            return false;
        }
        // check for more than one delimiter together
        for (int i = 0; i < login.length() - 1; i++) {
            String current = String.valueOf(login.charAt(i));
            String next = String.valueOf(login.charAt(i + 1));
            if (LOGIN_DELIMITERS.contains(current) && LOGIN_DELIMITERS.contains(next)) {
                return false;
            }
        }
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        return matcher.matches();
    }

    public static boolean isPasswordValid(String password) {
        if (StringUtils.isBlank(password)) {
            return false;
        }
        if (password.contains(" ")) {
            return false;
        }
        return !(password.length() < MINIMUM_PASSWORD_LENGTH || password.length() > MAXIMUM_PASSWORD_LENGTH);
    }
}
