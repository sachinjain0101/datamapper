package com.bullhorn.services;

import com.bullhorn.orm.refreshWork.dao.MappedMessagesDAO;
import com.bullhorn.orm.refreshWork.dao.ValidatedMessagesDAO;
import com.bullhorn.orm.timecurrent.dao.AssignmentProcessorDAO;
import com.bullhorn.orm.timecurrent.dao.MapDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
public class MapperAsyncService {

    @Autowired
    @Qualifier("mapperTaskScheduler")
    ThreadPoolTaskScheduler taskScheduler;

    private final MapDAO mapDAO;
    private final ValidatedMessagesDAO validatedMessagesDAO;
    private final MappedMessagesDAO mappedMessagesDAO;
    private final AssignmentProcessorDAO assignmentProcessorDAO;

    private long interval;

    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Autowired
    public MapperAsyncService(MapDAO mapDAO, ValidatedMessagesDAO validatedMessagesDAO, MappedMessagesDAO mappedMessagesDAO, AssignmentProcessorDAO assignmentProcessorDAO) {
        this.validatedMessagesDAO = validatedMessagesDAO;
        this.mappedMessagesDAO = mappedMessagesDAO;
        this.mapDAO = mapDAO;
        this.assignmentProcessorDAO = assignmentProcessorDAO;
    }

    public void executeAsynchronously() {
        taskScheduler.scheduleWithFixedDelay(new Mapper (mapDAO, validatedMessagesDAO, mappedMessagesDAO, assignmentProcessorDAO),interval);
    }

}
