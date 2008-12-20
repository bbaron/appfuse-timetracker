package com.bbaron.timetracker.dao;

import com.bbaron.timetracker.model.User;

public class UserDaoTest extends AbstractGenericDaoTestCase<User, String> {
    
    public UserDaoTest() {
        super(User.class, "user1", "user1000");
    }

    @Override
    protected User createEntity() {
        User user = new User("user1");
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
    protected String getIdFrom(User user) {
        return user.getUsername();
    }

    @Override
    protected void clearIdFrom(User user) {
        user.setUsername(null);
    }

    @Override
    protected String[] getSetUpStatements() {
        return new String[] {
                "insert into users (first_name, last_name, username, enabled, password) values ('first1', 'last1', 'user1', 1, 'x')",
        };
    }

	@Override
	protected void updateEntity(User user) {
		user.setLastName("newLastName");
	}

}
