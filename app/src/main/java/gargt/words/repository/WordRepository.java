package gargt.words.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.AsyncTask;
import android.util.Log;

import gargt.words.Dao.WordDao;
import gargt.words.Database.WordRoomDatabase;
import gargt.words.model.Word;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<PagedList<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                        .setPrefetchDistance(10)
                        .setPageSize(20).build();
        mAllWords = new LivePagedListBuilder(mWordDao.getAllWords(), pagedListConfig).build();
        Log.i("Tushar", "mAllWords is---> " +mAllWords);
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<PagedList<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert (Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        InsertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
