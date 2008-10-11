package com.bbaron.timetracker.dao.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bbaron.timetracker.model.Timecard;

@Repository
public class MockTimecardDao extends MockGenericDao<Timecard, Long> {

    @Override
    protected List<Timecard> createEntities() {
        List<Timecard> list = new ArrayList<Timecard>();
        return list;
    }

}