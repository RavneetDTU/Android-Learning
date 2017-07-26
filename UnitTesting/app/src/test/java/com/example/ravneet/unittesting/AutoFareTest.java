package com.example.ravneet.unittesting;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by ravneet on 23/7/17.
 */

public class AutoFareTest {

    @Test
    public void calcFare() throws Exception{
        assertEquals("Fare Don't Match :",41,MainActivity.calcFare(4,0));
    }
}