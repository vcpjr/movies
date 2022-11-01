package com.goldenraspberry.awards.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldenraspberry.awards.movies.model.Movie;
import com.goldenraspberry.awards.movies.model.dto.PrizeDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class MoviesApplicationIntegrationTests {


	private final static String URI = "/api/movies";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_insert_new_movie() throws Exception {

		Movie movie = new Movie();
		movie.setTitle("Junit Movie");
		movie.setReleaseYear(2018);
		movie.setProducers(new ArrayList<>());

		MvcResult resultInsert =
			mockMvc.perform(post(URI)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(movie)))
				.andExpect(status().isOk())
				.andReturn();
		Movie returnedMovie = this.objectMapper.readValue(resultInsert.getResponse().getContentAsString(), Movie.class);

		MvcResult resultFind =
				mockMvc.perform(get(URI + "/" + returnedMovie.getId())
								.contentType(MediaType.APPLICATION_JSON) )
						.andExpect(status().isOk())
						.andReturn();
		Movie insertedMovie = this.objectMapper.readValue(resultFind.getResponse().getContentAsString(), Movie.class);

		Assertions.assertThat(returnedMovie).usingRecursiveComparison().isEqualTo(insertedMovie);
	}

	@Test
	public void test_min_max_winners() throws Exception {
		MvcResult resultFind =
				mockMvc.perform(get(URI + "/" + "minMaxWinners")
								.contentType(MediaType.APPLICATION_JSON) )
						.andExpect(status().isOk())
						.andReturn();
		PrizeDTO dto = this.objectMapper.readValue(resultFind.getResponse().getContentAsString(), PrizeDTO.class);

		System.out.println(dto);
		//TODO Assertions
	}


}
