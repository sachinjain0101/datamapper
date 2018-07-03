package com.bullhorn.services;

import com.bullhorn.app.Constants;
import com.bullhorn.orm.refreshWork.dao.MappedMessagesDAO;
import com.bullhorn.orm.refreshWork.dao.ValidatedMessagesDAO;
import com.bullhorn.orm.timecurrent.dao.AssignmentProcessorDAO;
import com.bullhorn.orm.timecurrent.dao.ClientDAO;
import com.bullhorn.orm.timecurrent.dao.MapDAO;
import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    private final ClientDAO clientDAO;

    private long interval;
    private int poolSize;

    Map<CancellableRunnable, Future<?>> cancellableFutures = new HashMap<>();

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
    private List<TblIntegrationFrontOfficeSystem> lstFOS;
    public void setLstFOS(List<TblIntegrationFrontOfficeSystem> lstFOS) {
        this.lstFOS = lstFOS;
    }

    @Autowired
    public MapperHandler(MapDAO mapDAO, ValidatedMessagesDAO validatedMessagesDAO
            , MappedMessagesDAO mappedMessagesDAO, AssignmentProcessorDAO assignmentProcessorDAO
            , ClientDAO clientDAO) {
        this.validatedMessagesDAO = validatedMessagesDAO;
        this.mappedMessagesDAO = mappedMessagesDAO;
        this.mapDAO = mapDAO;
        this.assignmentProcessorDAO = assignmentProcessorDAO;
        this.clientDAO = clientDAO;
    }

    public void executeAsynchronously() {
        //taskScheduler.scheduleWithFixedDelay(new Mapper (mapDAO, validatedMessagesDAO, mappedMessagesDAO, assignmentProcessorDAO),interval);
        for (TblIntegrationFrontOfficeSystem FOS:lstFOS) {
            Mapper mapper = new Mapper (mapDAO, validatedMessagesDAO, mappedMessagesDAO, assignmentProcessorDAO,clientDAO);
            mapper.setFOS(FOS);
            mapper.setInterval(interval);
            taskScheduler.setThreadNamePrefix(String.format(Constants.DATA_MAPPER_THREAD_POOL_PREFIX,FOS.getName()));
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
