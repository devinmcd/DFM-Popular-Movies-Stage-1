package com.example.dfmpopularmoviesstage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dfmpopularmoviesstage1.Utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        text = findViewById(R.id.test);

        new MovieDataTask().execute("popular");
    }

    private class MovieDataTask extends AsyncTask<String, Void, String> {
        String t;

        @Override
        protected String doInBackground(String... params) {
            URL url = NetworkUtils.buildUrl(params[0]);
            try {
                t = NetworkUtils.getResponseFromHttpUrl(url);
                return t;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            text.setText(t);
        }
    }
}
