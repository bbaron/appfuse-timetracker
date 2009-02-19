package com.bbaron.timetracker.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class UserTest {

	@Test
	public void testUser() throws Exception {
		User user = new User("user");
		assertEquals("user", user.getUsername());
	}
}
