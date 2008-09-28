package com.bbaron.timetracker.model;

import static org.junit.Assert.assertNull;

import org.junit.Test;


public class UserTest {

	@Test
	public void testUser() throws Exception {
		User user = new User();
		assertNull(user.getUsername());
	}
}
