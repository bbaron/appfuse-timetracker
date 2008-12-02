package com.bbaron.timetracker.dao.hibernate;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.dao.UserDao;
import com.bbaron.timetracker.model.User;

@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

    public UserDaoHibernate() {
        super(User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findByUsername(String submitterUsername) {
        List<User> result = getHibernateTemplate().find("from User u where u.username = ?", submitterUsername);
        return (User) DataAccessUtils.requiredSingleResult(result);
    }

}
