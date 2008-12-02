package com.bbaron.timetracker.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bbaron.timetracker.model.TimecardDate;

public class TimecardDateEditorTest {

    private TimecardDateEditor editor;
    private TimecardDate timecardDate;
    private String timecardDateString = "2001-09-11";
    private String dateFormat = "yyyy-MM-dd";

	@Before
	public void setUp() throws Exception {
	    timecardDate = TimecardDate.date(2001, 9, 11);
	    editor = new TimecardDateEditor(true, dateFormat);
	}

    @Test
    public void testSetAsText() throws Exception {
        editor.setAsText(timecardDateString);
        assertEquals(timecardDate, editor.getValue());
    }

    @Test
    public void testGetAsText() throws Exception {
        editor.setValue(timecardDate);
        assertEquals(timecardDateString, editor.getAsText());
    }

	@Test
	public void testNullSafe() throws Exception {
		editor.setAsText(null);
		assertNull(editor.getValue());
		assertEquals("", editor.getAsText());
	}

	@Test
	public void testEmptySafe() throws Exception {
		editor.setAsText("");
		assertEquals("", editor.getAsText());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyDisallowed() throws Exception {
		new TimecardDateEditor(false, dateFormat).setAsText(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnknownDateFormat() throws Exception {
		editor.setAsText("BLING");
	}
}
