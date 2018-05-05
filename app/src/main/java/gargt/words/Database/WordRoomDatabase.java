package gargt.words.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import gargt.words.Dao.MovieDao;
import gargt.words.Dao.WordDao;
import gargt.words.model.Movie;

@Database(entities = {Movie.class}, version = 4)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();
    public abstract MovieDao movieDao();

    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "movie_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
