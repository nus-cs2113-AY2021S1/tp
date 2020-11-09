package seedu.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptographyTest {
    
    @Test
    public void encipherPassword_converts_String_to_cipherText_Properly() {
        String password = "123456";
        
        assertEquals("561278", Cryptography.encipherPassword(password));
    }

    @Test
    public void encipherPassword_converts_maxString_to_cipherText_Properly() {
        String password = "999999";

        assertEquals("777777", Cryptography.encipherPassword(password));
    }

    @Test
    public void encipherPassword_converts_minString_to_cipherText_Properly() {
        String password = "000000";

        assertEquals("000000", Cryptography.encipherPassword(password));
    }


    @Test
    void decipherPassword_converts_String_to_plainText_Properly() {
        String password = "561278";

        assertEquals("123456", Cryptography.decipherPassword(password));
    }

    @Test
    public void decipherPassword_converts_maxString_to_plainText_Properly() {
        String password = "777777";

        assertEquals("999999", Cryptography.decipherPassword(password));
    }

    @Test
    public void decipherPassword_converts_minString_to_plainText_Properly() {
        String password = "000000";

        assertEquals("000000", Cryptography.encipherPassword(password));
    }
}