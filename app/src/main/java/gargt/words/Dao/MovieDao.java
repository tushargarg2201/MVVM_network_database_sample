package gargt.words.Dao;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import gargt.words.model.Movie;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> movie);

    @Query("DELETE from movie_table")
    void deleteAll();

    @Query("SELECT * from movie_table")
    //LiveData<List<Word>> getAllWords();
    public abstract DataSource.Factory<Integer, Movie> getAllMovies();
}
