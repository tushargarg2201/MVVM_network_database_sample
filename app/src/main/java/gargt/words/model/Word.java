package gargt.words.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @ColumnInfo(name = "mobilenumber")
    private String mMobileNumber;


    public Word(@NonNull String word, String mobileNumber) {
        this.mWord = word;
        this.mMobileNumber = mobileNumber;
    }

    public String getWord() {
        return this.mWord;
    }

    public String getMobileNumber() {
        return this.mMobileNumber;
    }
}
