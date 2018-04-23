package gargt.words.adapter;

import android.arch.paging.AsyncPagedListDiffer;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gargt.words.R;
import gargt.words.model.Word;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private static final String TAG = WordListAdapter.class.getName();

    private final AsyncPagedListDiffer<Word> mDiffer
            = new AsyncPagedListDiffer(this, DIFF_CALLBACK);

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private final TextView mobileItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            mobileItemView = itemView.findViewById(R.id.mobileTextView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words

    public WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        //Word current = mWords.get(position);
        Word current = mDiffer.getItem(position);
        if (current != null) {
            Log.i(TAG, "called bindAdapter");
            holder.wordItemView.setText(current.getWord());
            holder.mobileItemView.setText(current.getMobileNumber());
        } else {
            Log.i(TAG, "holder needs to be clear");
        }

    }

    public void setWords(PagedList<Word> words) {
        mDiffer.submitList(words);
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mDiffer != null) {
            return mDiffer.getItemCount();
        }
        return 0;
    }

    public static final DiffUtil.ItemCallback<Word> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Word>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull Word oldWord, @NonNull Word newWord) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    boolean areItemsSame = false;
                    if (oldWord.getWord() == newWord.getWord()) {
                        areItemsSame = true;
                    }
                    Log.i(TAG, "areItems the same called ---> " + areItemsSame);
                    return true;
                }

                @Override
                public boolean areContentsTheSame(Word oldWord, Word newWord) {
                    boolean areContentsSame = oldWord.equals(newWord);
                    Log.i(TAG, "Are contents the same called ---> " + areContentsSame);
                    return oldWord.equals(newWord);
                }
    };
}
