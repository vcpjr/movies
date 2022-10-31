package com.goldenraspberry.awards.movies.model.util;

import com.goldenraspberry.awards.movies.component.StartupComponent;
import com.goldenraspberry.awards.movies.model.Movie;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvMovieReader {

	private static final Logger LOGGER = Logger.getLogger(CsvMovieReader.class);

	public List<Movie> readCsv(InputStream fis) {
		List<Movie> movies = new ArrayList<>();
		try (InputStreamReader isr = new InputStreamReader(fis);
			  BufferedReader br = new BufferedReader(isr)){
			//Pula a primeira linha
			br.readLine();

			while(br.ready()){
				String linha = br.readLine();

				if(!linha.trim().isEmpty()){
					Movie movie = Movie.fromCSV(linha);
					movies.add(movie);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		LOGGER.infof("Readed %d movies from CSV", movies.size());
		return movies;
	}
}