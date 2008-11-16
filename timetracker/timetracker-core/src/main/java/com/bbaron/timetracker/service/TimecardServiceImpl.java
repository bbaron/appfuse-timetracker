package com.bbaron.timetracker.service;

import static com.bbaron.timetracker.model.TimecardStatus.*;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbaron.timetracker.dao.TimecardDao;
import com.bbaron.timetracker.dao.UserDao;
import com.bbaron.timetracker.model.*;

@Service("timecardService")
public class TimecardServiceImpl implements TimecardService {

    private TimecardDao timecardDao;
    private UserDao userDao;
    private final Collection<String> statuses = getAllEnums(TimecardStatus.values());
    private final Collection<String> tasks = getAllEnums(Task.values());

    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setTimecardDao(TimecardDao timecardDao) {
        this.timecardDao = timecardDao;
    }

    @Override
    @Transactional
    public Long createTimecard(Long userId, LocalDate startDate) {
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
    @Transactional
    public void enterTimeAllocation(Long timecardId, TimeAllocation alloc) {
        Timecard timecard = timecardDao.get(timecardId);
        timecard.addTimeAllocation(alloc);
        timecardDao.save(timecard);
    }

    @Override
    @Transactional(readOnly = true)
    public Timecard getTimecard(Long timecardId) {
        Timecard timecard = timecardDao.findById(timecardId);
        return timecard;
    }

    @Override
    public Timecard getLatestTimecard(Long submitterId) {
        Timecard timecard = timecardDao.findLatest(submitterId);
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
    @Transactional(readOnly = true)
    public Collection<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public Collection<Timecard> searchTimecards(TimecardSearchCriteria criteria) {
        return timecardDao.findByCriteria(criteria);
    }

    @Override
    public void submitTimecard(Long timecardId) {
        updateTimecardStatus(timecardId, Submitted);
    }

    @Override
    public void rejectTimecard(Long timecardId) {
        updateTimecardStatus(timecardId, Rejected);
    }

    @Override
    public void approveTimecard(Long timecardId, Long approverId) {
        Timecard timecard = timecardDao.get(timecardId);
        timecard.setStatus(Approved);
        timecard.setApprover(userDao.get(approverId));
        updateTimecardStatus(timecardId, Approved);
    }

    private void updateTimecardStatus(Long timecardId, TimecardStatus status) {
        Timecard timecard = timecardDao.get(timecardId);
        timecard.setStatus(status);
        timecardDao.save(timecard);
    }

}
