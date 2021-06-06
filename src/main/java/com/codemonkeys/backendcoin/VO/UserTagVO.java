package com.codemonkeys.backendcoin.VO;

import java.util.List;

public class UserTagVO {
    private Long userId;
    private List<String> movies;

    public UserTagVO(Long userId, List<String> movies, List<String> actors, List<String> directors, List<String> genres) {
        this.userId = userId;
        this.movies = movies;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    private List<String> actors;
    private List<String> directors;
    private List<String> genres;

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }
}
