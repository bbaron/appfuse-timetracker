package com.bbaron.timetracker.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractIntegralValueEditorTest {

    private IntegralValueEditor editor;
    private Object value;
    private String text = "13";

	@Before
	public void setUp() throws Exception {
	    editor = createEditor(true);
	    value = createValue(13);
	}
	
    protected abstract IntegralValueEditor createEditor(boolean allowEmpty);
    protected abstract Object createValue(int n);

    @Test
    public void testSetAsText() throws Exception {
        editor.setAsText(text);
        assertEquals(value, editor.getValue());
    }

    @Test
    public void testGetAsText() throws Exception {
        editor.setValue(value);
        assertEquals(text, editor.getAsText());
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
		createEditor(false).setAsText(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnknownDateFormat() throws Exception {
		editor.setAsText("BLING");
	}
}
