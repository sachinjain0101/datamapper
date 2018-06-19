package com.bullhorn;

import com.bullhorn.orm.refreshWork.dao.MappedMessagesDAO;
import com.bullhorn.orm.refreshWork.dao.ValidatedMessagesDAO;
import com.bullhorn.orm.timecurrent.dao.AssignmentProcessorDAO;
import com.bullhorn.orm.timecurrent.dao.ErrorsDAO;
import com.bullhorn.orm.timecurrent.dao.MapDAO;
import com.bullhorn.services.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@EnableScheduling
public class DataMapperApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataMapperApplication.class);

	@Autowired
	Environment env;

	final AssignmentProcessorDAO assignmentProcessorDAO;
	final ErrorsDAO errorsDAO;
	final ValidatedMessagesDAO validatedMessagesDAO;
	final MapDAO mapDAO;
	final MappedMessagesDAO mappedMessagesDAO;

	@Autowired
	public DataMapperApplication(MappedMessagesDAO mappedMessagesDAO, MapDAO mapDAO, AssignmentProcessorDAO assignmentProcessorDAO, ErrorsDAO errorsDAO, ValidatedMessagesDAO validatedMessagesDAO) {
		this.mapDAO = mapDAO;
		this.assignmentProcessorDAO = assignmentProcessorDAO;
		this.errorsDAO = errorsDAO;
		this.validatedMessagesDAO = validatedMessagesDAO;
		this.mappedMessagesDAO = mappedMessagesDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(DataMapperApplication.class, args);
	}

	@EventListener
	public void init(ContextRefreshedEvent event) {
		LOGGER.info("Starting Data Mapper");
		Mapper mapper = new Mapper (mapDAO, validatedMessagesDAO, mappedMessagesDAO);
		mapper.run();
	}

	@PreDestroy
	public void destroy() {
		LOGGER.info("Shutting down Data Mapper");
	}

}
