package com.bullhorn.services;

import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;
import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AzureConsumer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AzureConsumer.class);

    private TblIntegrationFrontOfficeSystem fos;
    private QueueClient receiveClient;
    private String topicName;

    public AzureConsumer(TblIntegrationFrontOfficeSystem fos, String topicName) {
        this.fos = fos;
        this.topicName = topicName;
        setReceiveClient();
    }

    public QueueClient getReceiveClient() {
        return receiveClient;
    }

    public void setReceiveClient() {
        try {
            this.receiveClient = new QueueClient(new ConnectionStringBuilder(fos.getAzureEndPoint(), topicName), ReceiveMode.PEEKLOCK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ServiceBusException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
            LOGGER.info("AzureConsumer is running for {}", fos.getName());
            try {
                registerReceiver(receiveClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void registerReceiver(QueueClient queueClient) throws Exception {
        queueClient.registerMessageHandler(new IMessageHandler() {
                                               // callback invoked when the message handler loop has obtained a message
                                               public CompletableFuture<Void> onMessageAsync(IMessage message) {
                                                   // receives message is passed to callback
//                                                   if (message.getLabel() != null &&
//                                                           message.getContentType() != null &&
//                                                           message.getLabel().contentEquals("Scientist") &&
//                                                           message.getContentType().contentEquals("application/json")) {

                                                   byte[] body = message.getBody();

                                                   LOGGER.info("\n************* {} **************\n\n", new String(body, UTF_8));
                                                   return CompletableFuture.completedFuture(null);
                                               }

                                               // callback invoked when the message handler has an exception to report
                                               public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
                                                   System.out.printf(exceptionPhase + "-" + throwable.getMessage());
                                               }
                                           },
                // 1 concurrent call, messages are auto-completed, auto-renew duration
                new MessageHandlerOptions(1, true, Duration.ofMinutes(1)));
    }

}
