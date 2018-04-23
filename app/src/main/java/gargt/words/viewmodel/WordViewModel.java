package gargt.words.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import gargt.words.model.Word;
import gargt.words.repository.WordRepository;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mWordRepository;
    private LiveData<PagedList<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(application);
        mAllWords = mWordRepository.getAllWords();
    }

    public LiveData<PagedList<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) { mWordRepository.insert(word); }
}
