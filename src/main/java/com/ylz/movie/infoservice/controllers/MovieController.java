package com.ylz.movie.infoservice.controllers;

import com.ylz.movie.infoservice.models.Movie;
import com.ylz.movie.infoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Value("${themoviedb.base_url}")
    private String movieDbBaseUrl;

    @Value("${themoviedb.api_key}")
    private String movieDbApiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject(movieDbBaseUrl + movieId + "?api_key=" + movieDbApiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
