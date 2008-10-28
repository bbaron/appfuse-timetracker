package com.bbaron.timetracker.dao.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.dao.TimecardDao;
import com.bbaron.timetracker.model.Timecard;

@Repository
public class MockTimecardDao extends MockGenericDao<Timecard, Long> implements TimecardDao {

    @Override
    protected List<Timecard> createEntities() {
        List<Timecard> list = new ArrayList<Timecard>();
        return list;
    }

	@Override
	public Timecard findLastSaved(Long submitterId) {
		return null;
	}

    @Override
    public Timecard findById(Long timecardId) {
        // TODO Auto-generated method stub
        return null;
    }

}
