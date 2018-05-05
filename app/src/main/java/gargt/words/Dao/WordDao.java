package gargt.words.Dao;

import android.arch.persistence.room.Dao;

@Dao
public interface WordDao {

//    @Insert (onConflict = OnConflictStrategy.REPLACE)
//    void insert(Word word);
//
//    @Query("DELETE from word_table")
//    void deleteAll();
//
//    @Query("SELECT * from word_table ORDER by word ASC")
//    //LiveData<List<Word>> getAllWords();
//    public abstract DataSource.Factory<Integer, Word> getAllWords();
}
