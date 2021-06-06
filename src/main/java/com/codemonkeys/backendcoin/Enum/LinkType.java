package com.codemonkeys.backendcoin.Enum;

public enum LinkType {
    Actor_Info("actor_info"),Movie_Info("movie_info"),Actor_Movie("actor_movie"),
    Movie_Genre("movie_genre")
    ;

    private String type;
    LinkType(String type) {
        this.type=type;
    }
}
