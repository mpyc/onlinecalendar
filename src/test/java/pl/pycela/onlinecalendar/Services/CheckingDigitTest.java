package pl.pycela.onlinecalendar.Services;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckingDigitTest {


    @Test
    public void expiryDate_is_digit() {
        assertEquals(true, CheckingDigit.CheckingIsDigit("19"));
        assertEquals(true, CheckingDigit.CheckingIsDigit("-91"));
        assertEquals(true, CheckingDigit.CheckingIsDigit("31"));
    }

    @Test
    public void expiryDate_is_not_digit() {
        assertEquals(false, CheckingDigit.CheckingIsDigit("1a1"));
        assertEquals(false, CheckingDigit.CheckingIsDigit("a11321"));
        assertEquals(false, CheckingDigit.CheckingIsDigit("31321!"));
    }

    @Test
    public void expiryDate_is_null() {
        assertEquals(false, CheckingDigit.CheckingIsDigit(""));
        assertEquals(false, CheckingDigit.CheckingIsDigit("      "));
        assertEquals(false, CheckingDigit.CheckingIsDigit("         "));
        assertEquals(false, CheckingDigit.CheckingIsDigit("   ''   "));
    }
}