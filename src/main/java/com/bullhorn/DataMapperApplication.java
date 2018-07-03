package com.bullhorn;

import com.bullhorn.app.Constants;
import com.bullhorn.orm.refreshWork.dao.MappedMessagesDAO;
import com.bullhorn.orm.refreshWork.dao.ValidatedMessagesDAO;
import com.bullhorn.orm.timecurrent.dao.*;
import com.bullhorn.orm.timecurrent.model.TblIntegrationConfig;
import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;
import com.bullhorn.services.MapperHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@EnableScheduling
public class DataMapperApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataMapperApplication.class);

	private Environment env;

	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}

	private final AssignmentProcessorDAO assignmentProcessorDAO;
	private final ValidatedMessagesDAO validatedMessagesDAO;
	private final FrontOfficeSystemDAO frontOfficeSystemDAO;
	private final MapDAO mapDAO;
	private final MappedMessagesDAO mappedMessagesDAO;
	private final ConfigDAO configDAO;
	private final ClientDAO clientDAO;

	@Autowired
	public DataMapperApplication(MappedMessagesDAO mappedMessagesDAO
			, MapDAO mapDAO
			, AssignmentProcessorDAO assignmentProcessorDAO
			, ValidatedMessagesDAO validatedMessagesDAO
			, ConfigDAO configDAO
			, FrontOfficeSystemDAO frontOfficeSystemDAO
			, ClientDAO clientDAO) {
		this.mapDAO = mapDAO;
		this.assignmentProcessorDAO = assignmentProcessorDAO;
		this.validatedMessagesDAO = validatedMessagesDAO;
		this.mappedMessagesDAO = mappedMessagesDAO;
		this.configDAO = configDAO;
		this.frontOfficeSystemDAO = frontOfficeSystemDAO;
		this.clientDAO = clientDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(DataMapperApplication.class, args);
	}

	private List<TblIntegrationFrontOfficeSystem> lstFOS = null;

	@Bean(name = "integrationConfig")
	public List<TblIntegrationConfig> getConfig(){
		String cluster = (env.getProperty("azureConsumer.clusterName")!=null)?env.getProperty("azureConsumer.clusterName"):"";
		LOGGER.debug("Cluster info : {} & isEmpty : {}",cluster,cluster.isEmpty());
		// VIMP: lstFOS drives the AzureConsumer ThreadPool size
		lstFOS = frontOfficeSystemDAO.findByStatus(true,cluster);
		return configDAO.findAll();
	}

	@Bean("mapperTaskScheduler")
	@DependsOn("integrationConfig")
	public ThreadPoolTaskScheduler mapperTaskScheduler() {
		LOGGER.debug("Starting Mapper Task Scheduler");
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(lstFOS.size());
		threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
		TblIntegrationConfig val2 = getConfig().stream()
				.filter((k) -> k.getCfgKey().equals(Constants.DATA_MAPPER_THREADPOOL_SCHEDULER_TERMINATION_TIME_INSECONDS))
				.collect(Collectors.toList()).get(0);
		int terminationTime = Integer.parseInt(val2.getCfgValue());
		threadPoolTaskScheduler.setAwaitTerminationSeconds(terminationTime);
		return threadPoolTaskScheduler;
	}

	@Bean("mapperHandler")
	@DependsOn("mapperTaskScheduler")
	public MapperHandler mapperHandler(){
		LOGGER.debug("DataMapperAsyncService Constructed");
		TblIntegrationConfig val1 = getConfig().stream().filter((k) -> k.getCfgKey().equals(Constants.DATA_MAPPER_EXECUTE_INTERVAL)).collect(Collectors.toList()).get(0);
		long interval = Long.parseLong(val1.getCfgValue());
		MapperHandler mapperHandler = new MapperHandler(mapDAO, validatedMessagesDAO, mappedMessagesDAO, assignmentProcessorDAO, clientDAO);
		mapperHandler.setInterval(interval);
		mapperHandler.setPoolSize(lstFOS.size());
		mapperHandler.setLstFOS(lstFOS);
		return mapperHandler;
	}

	@EventListener
	public void init(ContextRefreshedEvent event) {
		LOGGER.debug("Starting Data Mapper");
		mapperHandler().executeAsynchronously();
		addDataMapperShutdownHook();
	}

	@PreDestroy
	public void destroy() {
		LOGGER.debug("Shutting down Data Mapper");
	}

	public void addDataMapperShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				LOGGER.info("Shutdown received");
				mapperHandler().shutdown();
			}
		});

		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				LOGGER.error("Uncaught Exception on " + t.getName() + " : " + e, e);
				mapperHandler().shutdown();
			}
		});
		LOGGER.info("Data Mapper ShutdownHook Added");
	}

}
