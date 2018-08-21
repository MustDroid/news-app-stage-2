package com.example.android.newsappstage2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {

    private ListView lstArticles;

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        return new UpdateNewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        updateNewsList(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {

    }

    private static class UpdateNewsLoader extends AsyncTaskLoader<List<Article>> {

        private UpdateNewsLoader(Context context) {
            super(context);
        }

        @Override
        public List<Article> loadInBackground() {

            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
            String sectionName = SP.getString(getContext().getResources().getString(R.string.settings_section_key), "all");
            String orderBySetting = SP.getString(getContext().getResources().getString(R.string.settings_order_by_key), "newest");

            String url = "http://content.guardianapis.com/search?api-key=235cdc6b-5ff6-48c3-9417-fea3a33f2feb&order-by="+orderBySetting;
            if(!sectionName.equals(getContext().getResources().getString(R.string.settings_section_all_value)))
                url += "&section="+sectionName;

            String json = RestClient.doGETRequest(url);
            if(json.equals(""))
                return new ArrayList<>();

            return new NewsResponse(json).getArticles();
        }
    }

    private void updateNewsList(List<Article> articles) {
        if(articles.size() == 0) {
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
            return;
        }

        ArticlesArrayAdapter articlesArrayAdapter = new ArticlesArrayAdapter(this, articles);
        lstArticles.setAdapter(articlesArrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstArticles = (ListView)findViewById(R.id.lstArticles);
        getSupportLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;

            case R.id.action_exit:
                finishAffinity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


