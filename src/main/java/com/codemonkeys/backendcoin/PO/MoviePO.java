package com.codemonkeys.backendcoin.PO;

public class MoviePO {
     public int movie_id;
     public String movie_bio;
     public String movie_chName;
     public String movie_foreName;
     public String movie_prodTime;
     public String movie_prodCompany;
     public String movie_director;
     public String movie_screenwriter;
     public String movie_genre;
     public String movie_star;
     public String movie_length;
     public String movie_rekeaseTime;
     public String movie_language;
     public String movie_achiem;

     @Override
     public String toString() {
          return "MoviePO{" +
                  "movie_id=" + movie_id +
                  ", movie_bio='" + movie_bio + '\'' +
                  ", movie_chName='" + movie_chName + '\'' +
                  ", movie_foreName='" + movie_foreName + '\'' +
                  ", movie_prodTime='" + movie_prodTime + '\'' +
                  ", movie_prodCompany='" + movie_prodCompany + '\'' +
                  ", movie_director='" + movie_director + '\'' +
                  ", movie_screenwriter='" + movie_screenwriter + '\'' +
                  ", movie_genre='" + movie_genre + '\'' +
                  ", movie_star='" + movie_star + '\'' +
                  ", movie_length='" + movie_length + '\'' +
                  ", movie_rekeaseTime='" + movie_rekeaseTime + '\'' +
                  ", movie_language='" + movie_language + '\'' +
                  ", movie_achiem='" + movie_achiem + '\'' +
                  '}';
     }


}
