package com.bbaron.timetracker.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.bbaron.timetracker.model.User;

public class UtilsTest {

    @Test
    public void testToMap() throws Exception {
        List<User> users = getUsers();
        Map<String, String> userMap = Utils.toMap(users, "id", "username");
        assertEquals(3, userMap.size());
        assertEquals("user1", userMap.get("1"));
        Iterator<Entry<String, String>> entries = userMap.entrySet().iterator();
        Entry<String, String> entry;
        entry = entries.next();
        assertEquals("user1", entry.getValue());
        assertEquals("1", entry.getKey());
        entry = entries.next();
        assertEquals("user2", entry.getValue());
        assertEquals("2", entry.getKey());
        entry = entries.next();
        assertEquals("user3", entry.getValue());
        assertEquals("3", entry.getKey());
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        User u1 = new User("user1");
        u1.setId(1L);
        User u2 = new User("user2");
        u2.setId(2L);
        User u3 = new User("user3");
        u3.setId(3L);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        return users;
    }
    
    @Test
    public void testInvalidKeyProperty() throws Exception {
        try {
            Utils.toMap(Collections.singleton(new User("")), "x", "username");
            fail("should throw IllegalArgumentException");
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NoSuchMethodException);
        }
    }

    @Test
    public void testInvalidValueProperty() throws Exception {
        try {
            Utils.toMap(Collections.singleton(new User("")), "id", "xxx");
            fail("should throw IllegalArgumentException");
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NoSuchMethodException);
        }
    }
}
