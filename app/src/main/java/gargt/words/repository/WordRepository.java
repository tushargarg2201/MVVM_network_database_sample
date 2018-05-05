package gargt.words.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import gargt.words.Dao.MovieDao;
import gargt.words.Database.WordRoomDatabase;
import gargt.words.model.Movie;
import gargt.words.model.Word;
import gargt.words.network.VolleyNetworkModel;

public class WordRepository {

    private MovieDao mWordDao;
    private LiveData<PagedList<Word>> mAllWords;
    private LiveData<PagedList<Movie>> mAllMovies;
    private static final String url = "https://api.androidhive.info/json/movies.json";
    PagedList.Config pagedListConfig;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.movieDao();
        pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                        .setPrefetchDistance(10)
                        .setPageSize(20).build();

        //mAllWords = new LivePagedListBuilder(mWordDao.getAllWords(), pagedListConfig).build();
        mAllMovies = new LivePagedListBuilder(mWordDao.getAllMovies(), pagedListConfig).build();
        Log.i("Tushar", "InWordRepository mAllMovies is-->" + mAllMovies.getValue());

        VolleyNetworkModel volleyNetworkModel = new VolleyNetworkModel(application, mWordDao);
        volleyNetworkModel.startNetworkRequest();
            //Log.i("Tushar", "mAllWords is---> " +mAllWords);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<PagedList<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert (Word word) {
        //new InsertAsyncTask(mWordDao).execute(word);
    }

    public LiveData<PagedList<Movie>> getAllMovies() {
        return mAllMovies;
    }
}
