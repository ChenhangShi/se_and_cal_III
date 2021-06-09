package com.codemonkeys.backendcoin.Enum;

public enum LinkType {
    Actor_Info("actor_info"),Movie_Info("movie_info"),Actor_Movie("actor_movie")
    ;

    private String type;
    LinkType(String type) {
        this.type=type;
    }
}
