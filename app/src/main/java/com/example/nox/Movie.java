package com.example.nox;



    public class Movie {

        String MovieName;
        String Duration;
        String RelaesedDate;
        String Description;

        public Movie(String movieName, String duration, String relaesedDate, String description) {
            MovieName = movieName;
            Duration = duration;
            RelaesedDate = relaesedDate;
            Description = description;
        }

        public String getMovieName() {
            return MovieName;
        }

        public String getDuration() {
            return Duration;
        }

        public String getRelaesedDate() {
            return RelaesedDate;
        }

        public String getDescription() {
            return Description;
        }
    }


