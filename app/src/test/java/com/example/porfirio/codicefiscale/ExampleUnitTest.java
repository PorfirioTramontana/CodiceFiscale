package com.example.porfirio.codicefiscale;

import com.example.porfirio.codicefiscale.engine.CitiesCodes;
import com.example.porfirio.codicefiscale.engine.ReverseGeocoding;

import org.junit.Test;

import static java.lang.String.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Test
    public void provaCitta2() {
        CitiesCodes cc = new CitiesCodes();
        for (int i = 30; i < 50; i += 1) {
            for (int j = 4; j < 24; j += 1) {
                String s = ReverseGeocoding.getCity(valueOf(i), valueOf(j), cc);
                System.err.println(i);
                System.err.println(j);
                System.err.println(s);
                assertNotNull(s, s);
            }
        }
    }


}