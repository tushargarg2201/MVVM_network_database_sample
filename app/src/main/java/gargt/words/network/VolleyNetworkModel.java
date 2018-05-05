package gargt.words.network;

import android.app.Application;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import gargt.words.Dao.MovieDao;
import gargt.words.model.Movie;
import gargt.words.view.FrameworkApplication;

public class VolleyNetworkModel {
    private static final String TAG = VolleyNetworkModel.class.getSimpleName();
    private List<Movie> movieList = new ArrayList<Movie>();
    private static final String url = "https://api.androidhive.info/json/movies.json";
    private Application mApplication;
    private MovieDao mMovieDao;

    public VolleyNetworkModel(Application application, MovieDao movieDao) {
        mApplication = application;
        mMovieDao = movieDao;
    }


    public void startNetworkRequest() {
        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        //mMainActivityPresenterImpl.hideLoading();
                        Log.i("Tushar", "Received json Response");

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setRating(((Number) obj.get("rating"))
                                        .doubleValue());
                                movie.setYear(obj.getInt("releaseYear"));

                                // Genre is json array
                                JSONArray genreArry = obj.getJSONArray("genre");
                                ArrayList<String> genre = new ArrayList<String>();
                                for (int j = 0; j < genreArry.length(); j++) {
                                    genre.add((String) genreArry.get(j));
                                }
                                //movie.setGenre(genre);

                                // adding movie to movies array
                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        if (movieList != null && movieList.size() > 0) {
                            insertDB(movieList);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.i("Tushar", "Volley Error is--->" + error.getMessage());

            }
        });

        // Adding request to request queue
        FrameworkApplication.getInstance().addToRequestQueue(movieReq);

    }



    private void insertDB(final List<Movie> mAllMovieList) {
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                Log.i("Tushar", "Inserting movieList in the database");
                mMovieDao.insert(mAllMovieList);
            }
        });
    }
}
