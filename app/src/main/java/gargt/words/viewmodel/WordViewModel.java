package gargt.words.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import gargt.words.model.Movie;
import gargt.words.model.Word;
import gargt.words.repository.WordRepository;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;
    private LiveData<PagedList<Word>> mAllWords;
    private LiveData<PagedList<Movie>> mAllMovies;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(application);
        mAllWords = mWordRepository.getAllWords();
        mAllMovies = mWordRepository.getAllMovies();
    }

    public LiveData<PagedList<Word>> getAllWords() { return mAllWords; }

    public LiveData<PagedList<Movie>> getAllMovies() {
        return mAllMovies;
    }

    public void insert(Word word) { mWordRepository.insert(word); }

}
