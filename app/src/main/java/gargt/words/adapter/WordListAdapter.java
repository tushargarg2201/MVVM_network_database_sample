package gargt.words.adapter;

import android.arch.paging.AsyncPagedListDiffer;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gargt.words.R;
import gargt.words.model.Movie;
import gargt.words.model.Word;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private static final String TAG = WordListAdapter.class.getName();

    private final AsyncPagedListDiffer<Movie> mDiffer
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
        Movie current = mDiffer.getItem(position);
        if (current != null) {
            Log.i(TAG, "called bindAdapter");
            if (!TextUtils.isEmpty(current.getTitle())) {
                holder.wordItemView.setText(current.getTitle());
            }
            if (!TextUtils.isEmpty(current.getThumbnailUrl())) {
                holder.mobileItemView.setText(current.getThumbnailUrl());
            }
        } else {
            Log.i(TAG, "holder needs to be clear");
        }

    }

    public void setWords(PagedList<Movie> movie) {
        mDiffer.submitList(movie);
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

    public static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(Movie oldItem, Movie newItem) {
            return oldItem.getTitle() == newItem.getTitle();
        }

        @Override
        public boolean areContentsTheSame(Movie oldItem, Movie newItem) {
            return oldItem.equals(newItem);
        }
    };

}
