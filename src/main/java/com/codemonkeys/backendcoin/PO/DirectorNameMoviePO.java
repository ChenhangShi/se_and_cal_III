package com.codemonkeys.backendcoin.PO;

public class DirectorNameMoviePO {
    public String getMovie_director() {
        return movie_director;
    }

    public DirectorNameMoviePO(String movie_director, Integer movie_id) {
        this.movie_director = movie_director;
        this.movie_id = movie_id;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    String movie_director;
    Integer movie_id;

}
