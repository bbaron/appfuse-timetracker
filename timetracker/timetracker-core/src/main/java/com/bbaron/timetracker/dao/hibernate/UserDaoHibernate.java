package com.bbaron.timetracker.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.dao.UserDao;
import com.bbaron.timetracker.model.User;

@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

    public UserDaoHibernate() {
        super(User.class);
    }

}
