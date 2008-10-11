package com.bbaron.timetracker.dao.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.model.User;

@Repository
public class MockUserDao extends MockGenericDao<User, Long> {

    @Override
    protected List<User> createEntities() {
        List<User> list = new ArrayList<User>();
        User e = new User();
        e.setFirstName("first");
        e.setLastName("last");
        e.setId(1L);
        e.setUsername("user");
        list.add(e);
        return list;
    }

}
