package com.bbaron.timetracker.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbaron.timetracker.dao.GenericDao;
import com.bbaron.timetracker.model.Task;
import com.bbaron.timetracker.model.TimeAllocation;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardStatus;
import com.bbaron.timetracker.model.User;

@Service
public class TimecardServiceImpl implements TimecardService {

	private GenericDao<Timecard, Long> timecardDao;
	private GenericDao<User, Long> userDao;
    private final Collection<String> statuses = getAllEnums(TimecardStatus.values());
    private final Collection<String> tasks = getAllEnums(Task.values());
    
	protected Logger logger = Logger.getLogger(getClass());

    @Autowired
	public void setUserDao(GenericDao<User, Long> userDao) {
		this.userDao = userDao;
	}

    @Autowired
	public void setTimecardDao(GenericDao<Timecard, Long> timecardDao) {
		this.timecardDao = timecardDao;
	}

	@Override
	public Long createTimecard(Long userId, Date startDate) {
		User submitter = userDao.get(userId);
		if (logger.isInfoEnabled()) {
		    logger.info("creating timecard for user " + submitter + " starting " + startDate);
		}
		Timecard timecard = new Timecard();
		timecard.setSubmitter(submitter);
		timecard.setStartDate(startDate);
		Timecard saved = timecardDao.save(timecard);
		return saved.getId();
	}

	@Override
	public void enterTimeAllocation(Long timecardId, TimeAllocation alloc) {
		Timecard timecard = timecardDao.get(timecardId);
		timecard.addTimeAllocation(alloc);
	}

    @Override
    public Timecard getTimecard(Long timecardId) {
        Timecard timecard = timecardDao.get(timecardId);
        return timecard;
    }

    @Override
    public Collection<String> getAllStatuses() {
        return statuses;
    }
    
    private Collection<String> getAllEnums(Enum<?>[] values) {
        SortedSet<String> set = new TreeSet<String>();
        for (Enum<?> value : values) {
            set.add(value.name());            
        }
        return set;
    }

    @Override
    public Collection<String> getAllTasks() {
        return tasks;
    }

    @Override
    public Map<Long, String> getAllUsers() {
        Map<Long, String> map = new HashMap<Long, String>();
        
        for (User user : userDao.getAll()) {
            map.put(user.getId(), user.getUsername());
        }
        return map;
    }

}
