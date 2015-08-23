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

    @Test
    public void isLoginValid_null_notValid() {
        assertFalse("Login cannot be null", ValidationUtil.isLoginValid(null));
    }

    @Test
    public void isLoginValid_emptyString_notValid() {
        assertFalse("Login cannot be empty", ValidationUtil.isLoginValid(""));
    }

    @Test
    public void isLoginValid_stringOfSpacesOnly_notValid() {
        assertFalse("Login cannot be empty", ValidationUtil.isLoginValid("      "));
    }

    @Test
    public void isLoginValid_tooShortLogin_notValid() {
        assertFalse("Login cannot be less than 3 symbols", ValidationUtil.isLoginValid("ab"));
    }

    @Test
    public void isLoginValid_shortestLogin_valid() {
        String login = "abc";
        assertTrue("Login " + login + " is valid", ValidationUtil.isLoginValid(login));
    }

    @Test
    public void isLoginValid_tooLongLogin_notValid() {
        assertFalse("Login cannot be greater than 20 symbols", ValidationUtil.isLoginValid("abcdeabcdeabcdeabcdea"));
    }

    @Test
    public void isLoginValid_longestLogin_valid() {
        String login = "abcdeabcdeabcdeabcde";
        assertTrue("Login " + login + " is valid", ValidationUtil.isLoginValid(login));
    }

    @Test
    public void isLoginValid_simpleLogin_valid() {
        String login = "simpleUser";
        assertTrue("Login " + login + " is valid", ValidationUtil.isLoginValid(login));
    }

    @Test
    public void isLoginValid_nonEnglishSymbolInLogin_notValid() {
        assertFalse("Login can only consists of English letters", ValidationUtil.isLoginValid("пользователь"));
    }

    @Test
    public void isLoginValid_loginWithSpace_notValid() {
        assertFalse("Login cannot contains spaces", ValidationUtil.isLoginValid("user login"));
    }

    @Test
    public void isLoginValid_loginWithAllowedDelimiters_valid() {
        assertTrue("Login can contains a \".\" symbol", ValidationUtil.isLoginValid("user.login"));
        assertTrue("Login can contains a \"-\" symbol", ValidationUtil.isLoginValid("user-login"));
        assertTrue("Login can contains a \"_\" symbol", ValidationUtil.isLoginValid("user_login"));
    }

    @Test
    public void isPasswordValid_null_notValid() {
        assertFalse("Password cannot be null", ValidationUtil.isPasswordValid(null));
    }

    @Test
    public void isPasswordValid_emptyString_notValid() {
        assertFalse("Password cannot be empty", ValidationUtil.isPasswordValid(""));
    }

    @Test
    public void isPasswordValid_stringOfSpacesOnly_notValid() {
        assertFalse("Password cannot be empty", ValidationUtil.isPasswordValid("      "));
    }

    @Test
    public void isPasswordValid_tooShortPassword_notValid() {
        assertFalse("Password cannot be less than 6 symbols", ValidationUtil.isPasswordValid("abcde"));
    }

    @Test
    public void isPasswordValid_tooLongPassword_notValid() {
        assertFalse("Password cannot be greater than 20 symbols", ValidationUtil.isPasswordValid("abcdeabcdeabcdeabcdea"));
    }

    @Test
    public void isPasswordValid_simplePassword_valid() {
        String password = "password";
        assertTrue("Password \"" + password + "\" is valid", ValidationUtil.isPasswordValid(password));
    }
}
