package com.example.appdual.Class;

import java.io.Serializable;

public class Film implements Serializable {
    public String nombrepeli;
    public String backdrop_path;
    public String poster_path;
    public String overview;
    public String release_date;
    public String vote_count;
    public String vote_average;
    public int id;

    public Film(){

    }

    public Film(String nombrepeli, String backdrop_path, String poster_path, String overview, String release_date, String vote_count, String vote_average, int id) {
        this.nombrepeli = nombrepeli;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.id = id;
    }



    public String getNombrepeli() {
        return nombrepeli;
    }

    public void setNombrepeli(String nombrepeli) {
        this.nombrepeli = nombrepeli;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getRating() {
        return vote_average;
    }

    public void setRating(String vote_average) {
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
