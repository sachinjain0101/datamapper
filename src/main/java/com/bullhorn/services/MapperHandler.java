package com.bullhorn.services;

import com.bullhorn.orm.refreshWork.dao.MappedMessagesDAO;
import com.bullhorn.orm.refreshWork.dao.ValidatedMessagesDAO;
import com.bullhorn.orm.timecurrent.dao.AssignmentProcessorDAO;
import com.bullhorn.orm.timecurrent.dao.MapDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class MapperHandler {

    @Autowired
    @Qualifier("mapperTaskScheduler")
    ThreadPoolTaskScheduler taskScheduler;

    private final MapDAO mapDAO;
    private final ValidatedMessagesDAO validatedMessagesDAO;
    private final MappedMessagesDAO mappedMessagesDAO;
    private final AssignmentProcessorDAO assignmentProcessorDAO;

    private long interval;
    private int poolSize;

    Map<CancellableRunnable, Future<?>> cancellableFutures = new HashMap<>();

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }


    @Autowired
    public MapperHandler(MapDAO mapDAO, ValidatedMessagesDAO validatedMessagesDAO, MappedMessagesDAO mappedMessagesDAO, AssignmentProcessorDAO assignmentProcessorDAO) {
        this.validatedMessagesDAO = validatedMessagesDAO;
        this.mappedMessagesDAO = mappedMessagesDAO;
        this.mapDAO = mapDAO;
        this.assignmentProcessorDAO = assignmentProcessorDAO;
    }

    public void executeAsynchronously() {
        //taskScheduler.scheduleWithFixedDelay(new Mapper (mapDAO, validatedMessagesDAO, mappedMessagesDAO, assignmentProcessorDAO),interval);

        for (int i = 1; i <= poolSize; i++) {
            Mapper mapper = new Mapper (mapDAO, validatedMessagesDAO, mappedMessagesDAO, assignmentProcessorDAO,interval);
            Future<?> future = taskScheduler.submit(mapper);
            cancellableFutures.put(mapper, future);
        }
    }

    public void shutdown() {

        for (Map.Entry<CancellableRunnable, Future<?>> entry : cancellableFutures.entrySet()) {
            entry.getKey().cancel();
            entry.getValue().cancel(true);
        }

        taskScheduler.shutdown();
    }


}
