package com.domainlanguage.intervals;

import junit.framework.TestCase;

public class IntervalLimitTest extends TestCase {

    //InvervalLimit invalid = IntervalLimit.lower(false, new Object());
    
    public void testUpperClosed() throws Exception {
        IntervalLimit uc = IntervalLimit.upper(true, 1L);
        assertTrue(uc.isClosed());
        assertTrue(uc.isUpper());
    }

    public void testUpperOpen() throws Exception {
        IntervalLimit uc = IntervalLimit.upper(false, "");
        assertTrue(uc.isOpen());
        assertTrue(uc.isUpper());
    }

    public void testLowerClosed() throws Exception {
        IntervalLimit uc = IntervalLimit.lower(true, 1L);
        assertTrue(uc.isClosed());
        assertTrue(uc.isLower());
    }

    public void testLowerOpen() throws Exception {
        IntervalLimit uc = IntervalLimit.lower(false, "");
        assertTrue(uc.isOpen());
        assertTrue(uc.isLower());
    }
}
