package com.bullhorn.services;

import com.bullhorn.json.model.AzureConfig;
import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;
import com.microsoft.azure.servicebus.QueueClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AzureConsumerAsyncService {

    @Autowired
    TaskExecutor executor;

    private AzureConfig config;

    @Autowired
    public AzureConsumerAsyncService(@Qualifier("azureConfig") AzureConfig config){
        this.config = config;
    }

    public List<QueueClient> executeAsynchronously() {

        List<TblIntegrationFrontOfficeSystem> lstFOS = config.getLstFOS();
        List<QueueClient> consumers = new ArrayList<>();

        for(TblIntegrationFrontOfficeSystem fos : lstFOS){
            AzureConsumer consumer = new AzureConsumer(fos,config.getTopicName());
            consumers.add(consumer.getReceiveClient());
            executor.execute(consumer);
        }

        return consumers;
    }

}
