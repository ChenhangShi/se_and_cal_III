package com.codemonkeys.backendcoin.Enum;

public enum NodeType {
    Actor("actor_chName"),
    Actor_Bio("actor_bio"),
    Actor_ForeName("actor_foreName"),
    Actor_Nationality("actor_nationality"),
    Actor_Constellation("actor_constellation"),//星座
    Actor_Birthplace("actor_birthplace"),
    Actor_RepWorks("actor_repworks"),
    Actor_Achiem("actor_achiem"),//成就
    Actor_Brokerage("actor_brokerage"),//经纪人
    Movie("movie_chName"),
    Movie_Bio("movie_bio"),
    Movie_ForeName("movie_foreName"),
    Movie_ProdTime("movie_prodTime"),
    Movie_ProdCompany("movie_prodCompany"),
    Movie_Director("movie_director"),
    Movie_ScreenWriter("movie_screenwriter"),
    Movie_Genre("movie_genre"),
    Movie_Star("movie_star"),
    Movie_Length("movie_length"),
    Movie_RekeaseTime("movie_rekeaseTime"),
    Movie_Language("movie_language"),
    Movie_Achiem("movie_achiem"),

    Genre("genre")
    ;

    private String type;
    NodeType(String type) {
        this.type=type;
    }
}
