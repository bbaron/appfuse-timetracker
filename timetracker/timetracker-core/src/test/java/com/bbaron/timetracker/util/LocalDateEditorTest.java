package com.bbaron.timetracker.util;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

public class LocalDateEditorTest {

	private DateTimeFormatter dateFormat;
    private LocalDateEditor editor;
    private LocalDate localDate;
    private String localDateString = "2001-09-11";

	@Before
	public void setUp() throws Exception {
	    localDate = new LocalDate(2001, 9, 11);
	    dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
	    editor = new LocalDateEditor(dateFormat, true);
	}

    @Test
    public void testSetAsText() throws Exception {
        editor.setAsText(localDateString);
        assertEquals(localDate, editor.getValue());
    }

    @Test
    public void testGetAsText() throws Exception {
        editor.setValue(localDate);
        assertEquals(localDateString, editor.getAsText());
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
		new LocalDateEditor(dateFormat, false).setAsText(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnknownDateFormat() throws Exception {
		editor.setAsText("BLING");
	}
}
