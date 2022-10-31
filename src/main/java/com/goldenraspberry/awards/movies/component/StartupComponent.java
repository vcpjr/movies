package com.goldenraspberry.awards.movies.component;

import com.goldenraspberry.awards.movies.service.MovieService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class StartupComponent {

	private static final Logger LOGGER = Logger.getLogger(StartupComponent.class);

	@Autowired
	private MovieService service;

	@PostConstruct
	public void init() {
		LOGGER.info("START UP: Reading movies from movielist.csv");
		InputStream csv = this.getClass().getResourceAsStream("/movielist.csv");
		this.service.importCsv(csv);

	}


}
