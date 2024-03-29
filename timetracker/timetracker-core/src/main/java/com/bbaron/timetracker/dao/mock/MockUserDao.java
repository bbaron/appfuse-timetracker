package com.bbaron.timetracker.dao.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.dao.UserDao;
import com.bbaron.timetracker.model.User;

@Repository
public class MockUserDao extends MockGenericDao<User, String> implements UserDao {

    @Override
    protected List<User> createEntities() {
        List<User> list = new ArrayList<User>();
        User e = new User("user");
        e.setFirstName("first");
        e.setLastName("last");
        list.add(e);
        return list;
    }


}
