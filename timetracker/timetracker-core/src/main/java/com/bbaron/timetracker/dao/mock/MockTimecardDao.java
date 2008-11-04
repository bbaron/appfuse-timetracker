package com.bbaron.timetracker.dao.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.dao.TimecardDao;
import com.bbaron.timetracker.model.Timecard;
import com.bbaron.timetracker.model.TimecardSearchCriteria;

@Repository
public class MockTimecardDao extends MockGenericDao<Timecard, Long> implements TimecardDao {

    @Override
    protected List<Timecard> createEntities() {
        List<Timecard> list = new ArrayList<Timecard>();
        return list;
    }

	@Override
	public Timecard findLatest(Long submitterId) {
		return null;
	}

    @Override
    public Timecard findById(Long timecardId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Timecard> findByCriteria(TimecardSearchCriteria criteria) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Timecard> findSubmitted(Long approverId) {
        // TODO Auto-generated method stub
        return null;
    }

}
