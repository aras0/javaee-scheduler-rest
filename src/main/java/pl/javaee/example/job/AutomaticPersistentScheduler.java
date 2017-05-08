package pl.javaee.example.job;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import pl.javaee.example.services.SchedulerService;

@Startup
@Singleton
@DependsOn("SchedulerService")
public class AutomaticPersistentScheduler {

	private final Logger log = Logger.getLogger(getClass().getName());

	@Inject
	private SchedulerService service;

	private boolean cancelTimer = false;

	public void stopDiscovery() {
		this.cancelTimer = true;
	}

    @Schedule(dayOfWeek = "*", info = "AutomaticPersistentScheduler", persistent = true)	
    public void process() {
		if (cancelTimer) {
			return;
		}

		log.info("http://www.nbp.pl/kursy/xml/LastA.xml");

		service.getCurreny("http://www.nbp.pl/kursy/xml/LastA.xml");
		stopDiscovery();

	}

	@PostConstruct
	public void postConstruct() {
		log.info("Inicjalized Scheduler postConstruct");
	}

	@PreDestroy
	public void terminate() {
		log.info("Terminate");
		stopDiscovery();
	}

}
