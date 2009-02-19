package com.bbaron.timetracker.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnumEditorTest {

	private EnumEditor editor = new EnumEditor(Foo.class);

	@Before
	public void setUp() throws Exception {
		editor = new EnumEditor(Foo.class);
	}

	private static enum Foo {
		BAR
	}

	@Test
	public void testSetAndGet() throws Exception {
		editor.setAsText("BAR");
		assertEquals("BAR", editor.getAsText());
	}

	@Test
	public void testNullSafe() throws Exception {
		editor.setAsText(null);
		assertEquals("", editor.getAsText());
	}

	@Test
	public void testEmptySafe() throws Exception {
		editor.setAsText("");
		assertEquals("", editor.getAsText());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyDisallowed() throws Exception {
		new EnumEditor(Foo.class, false).setAsText(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnknownEnumValue() throws Exception {
		editor.setAsText("BLING");
	}
}
