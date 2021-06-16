package com.codemonkeys.backendcoin.PO;

public class UserRecommendedMoviePO {
    private Integer userId;
    private Integer movieId;
    private String movieName;

    public UserRecommendedMoviePO(Integer userId, Integer movieId, String movieName) {
        this.userId = userId;
        this.movieId = movieId;
        this.movieName = movieName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public boolean equals(UserRecommendedMoviePO userRecommendedMoviePO){
        return userId.equals(userRecommendedMoviePO.userId) && movieId.equals(userRecommendedMoviePO.movieId) && movieName.equals(userRecommendedMoviePO.movieName);
    }
}
