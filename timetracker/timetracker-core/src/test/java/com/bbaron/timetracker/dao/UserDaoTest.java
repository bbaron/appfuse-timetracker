package com.bbaron.timetracker.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.bbaron.timetracker.model.User;

public class UserDaoTest extends BaseDaoTestCase {

    private GenericDao<User, Long> userDao;

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.executeSqlScript("classpath:/onsetup-User.sql", false);
    }

    @Override
    protected void onTearDownAfterTransaction() throws Exception {
        super.executeSqlScript("classpath:/onteardown-User.sql", false);
    }


    public void setUserDao(GenericDao<User, Long> userDao) {
        this.userDao = userDao;
    }

    public void testGetUserInvalid() throws Exception {
        try {
            userDao.get(1000L);
            fail("'badusername' found in database, failing test...");
        } catch (DataAccessException d) {
            assertTrue(d != null);
        }
    }

    public void testGetUser() throws Exception {
        User user = userDao.get(-1L);

        assertNotNull(user);
    }

    public void testUpdateUser() throws Exception {
        User user = userDao.get(-1L);

        userDao.save(user);
        flush();

        user = userDao.get(-1L);

        // verify that violation occurs when adding new user with same username
        user.setId(null);

        endTransaction();

        try {
            userDao.save(user);
            flush();
            fail("saveUser didn't throw DataIntegrityViolationException");
        } catch (DataIntegrityViolationException e) {
            assertNotNull(e);
            logger.debug("expected exception: " + e.getMessage());
        }
    }

    public void testAddAndRemoveUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("Last");

        user = userDao.save(user);
        flush();

        assertNotNull(user.getId());
        user = userDao.get(user.getId());
        assertEquals("Last", user.getLastName());

        userDao.remove(user.getId());
        flush();

        try {
            userDao.get(user.getId());
            fail("getUser didn't throw DataAccessException");
        } catch (DataAccessException d) {
            assertNotNull(d);
        }
    }

    public void testUserExists() throws Exception {
        boolean b = userDao.exists(-1L);
        assertTrue(b);
    }

    public void testUserNotExists() throws Exception {
        boolean b = userDao.exists(111L);
        assertFalse(b);
    }
}
