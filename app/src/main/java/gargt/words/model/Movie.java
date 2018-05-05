package gargt.words.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;

@Entity(tableName = "movie_table")
public class Movie {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "thumbnailUrl")
    private String thumbnailUrl;

    @NonNull
    @ColumnInfo(name = "year")
    private int year;

    @NonNull
    @ColumnInfo(name = "rating")
    private double rating;

//    @NonNull
//    @ColumnInfo(name = "genre")
//    private ArrayList<String> genre;

    public Movie() {
    }

    public Movie(String name, String thumbnailUrl, int year, double rating,
                 ArrayList<String> genre) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.rating = rating;
        //this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


}
