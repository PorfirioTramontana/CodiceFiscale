package com.example.porfirio.codicefiscale;

import com.example.porfirio.codicefiscale.engine.CitiesCodes;
import com.example.porfirio.codicefiscale.engine.ReverseGeocoding;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void provaCitta1() {
        CitiesCodes cc = new CitiesCodes();
        String s = ReverseGeocoding.getCity("40.843107", "14.226497", cc);
        assertEquals("Napoli", s);
    }
}