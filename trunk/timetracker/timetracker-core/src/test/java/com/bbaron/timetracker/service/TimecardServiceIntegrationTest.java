package com.bbaron.timetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import com.bbaron.timetracker.model.Timecard;

public class TimecardServiceIntegrationTest extends AbstractTransactionalDataSourceSpringContextTests {
    
    private TimecardService timecardService;
    
    @Autowired
    public void setTimecardService(TimecardService timecardService) {
        this.timecardService = timecardService;
    }

    /**
     * Sets AutowireMode to AUTOWIRE_BY_NAME and configures all context files needed to tests DAOs.
     * @return String array of Spring context files.
     */
    @Override
    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
                "classpath:/timetracker-hibernate.xml",
                "classpath:/timetracker-core.xml",
            };
    }

    public void testGetTimecard() throws Exception {
        Timecard timecard = timecardService.getTimecard(2000L);
        assertNotNull(timecard);
        assertNotNull(timecard.getSubmitter().getUsername());
    }
    
    public void testGetTimecardDetail() throws Exception {
        Timecard timecard = timecardService.getTimecardDetail(2000L);
        assertNotNull(timecard.getTimeAllocationList().iterator().next().getTask());
    }
}
