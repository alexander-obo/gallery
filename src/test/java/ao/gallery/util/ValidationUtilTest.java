package ao.gallery.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationUtilTest {

    @Test
    public void isEmailValid_null_notValid() {
        assertFalse("Email can not be null", ValidationUtil.isEmailValid(null));
    }

    @Test
    public void isEmailValid_emptyString_notValid() {
        assertFalse("Email cannot be empty", ValidationUtil.isEmailValid(""));
    }

    @Test
    public void isEmailValid_stringOfSpacesOnly_notValid() {
        assertFalse("Email cannot be empty", ValidationUtil.isEmailValid("       "));
    }

    @Test
    public void isEmailValid_tooShortEmail_notValid() {
        assertFalse("Email cannot be less than 6 symbols", ValidationUtil.isEmailValid("a@b.c"));
    }

    @Test
    public void isEmailValid_tooLongEmail_notValid() {
        String tooLongEmail = "abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdea@deab.cde";
        assertFalse("Email cannot be greater than 50 symbols", ValidationUtil.isEmailValid(tooLongEmail));
    }

    @Test
    public void isEmailValid_minimumValidEmail_valid() {
        String shortestValidEmail = "a@b.cd";
        assertTrue("Email " + shortestValidEmail + " is valid", ValidationUtil.isEmailValid(shortestValidEmail));
    }

    @Test
    public void isEmailValid_maximumValidEmail_valid() {
        String longestValidEmail = "qwertyuiopqwertyuiopqwertyuiopqwertyuiop@qwert.com";
        assertTrue("Email " + longestValidEmail + " is valid", ValidationUtil.isEmailValid(longestValidEmail));
    }

    @Test
    public void isEmailValid_stringWithoutAt_notValid() {
        assertFalse("Email without \"@\" is not valid", ValidationUtil.isEmailValid("someemail"));
    }

    @Test
    public void isEmailValid_emailWithSpaceInMiddle_notValid() {
        assertFalse("Email with space inside it is not valid", ValidationUtil.isEmailValid("some@ email.com"));
    }

    @Test
    public void isEmailValid_simpleValidEmail_valid() {
        String email = "some@email.com";
        assertTrue("Email " + email + " is valid", ValidationUtil.isEmailValid(email));
    }
}
