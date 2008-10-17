package com.bbaron.timetracker.dao;

import com.bbaron.timetracker.model.User;

public class UserDaoTest extends AbstractGenericDaoTestCase<User, Long> {
    
    public UserDaoTest() {
        super(User.class, -1L, 1000L);
    }

    @Override
    protected User createEntity() {
        User user = new User();
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("Last");
        return user;
    }

    @Override
    protected Object getActualFrom(User user) {
        return user.getLastName();
    }

    @Override
    protected Object getExpected() {
        return "Last";
    }

    @Override
    protected Long getIdFrom(User user) {
        return user.getId();
    }

    @Override
    protected void clearIdFrom(User user) {
        user.setId(null);
    }

    @Override
    protected String[] getSetUpStatements() {
        return new String[] {
                "insert into tt_user (id, first_name, last_name, username) values (-1, 'first1', 'last1', 'user1')",
        };
    }

}
