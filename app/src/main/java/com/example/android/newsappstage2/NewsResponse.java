package com.example.android.newsappstage2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emoke Hajdu on 6/23/2018.
 */

public class NewsResponse {
    private String mStatus;
    private String mUserTier;
    private int mTotal;
    private int mStartIndex;
    private int mPageSize;
    private int  mCurrentPage;
    private int mPages;
    private String mOrderBy;
    private String mResults;
    private List<Article> mArticles;

    public NewsResponse(String json) {
        try {
            JSONObject jsonResponse = new JSONObject(json);
            JSONObject jsonNewsResponse = jsonResponse.getJSONObject("response");

            mStatus = jsonNewsResponse.getString("status");
            mUserTier = jsonNewsResponse.getString("userTier");
            mTotal = jsonNewsResponse.getInt("total");
            mStartIndex = jsonNewsResponse.getInt("startIndex");
            mPageSize = jsonNewsResponse.getInt("pageSize");
            mCurrentPage = jsonNewsResponse.getInt("currentPage");
            mPages = jsonNewsResponse.getInt("pages");
            mOrderBy = jsonNewsResponse.getString("orderBy");
            JSONArray jsonArticlesArray = jsonNewsResponse.getJSONArray("results");
            mArticles = new ArrayList<>();

            for (int i = 0; i < jsonArticlesArray.length(); i++) {
                JSONObject jsonArticle = jsonArticlesArray.getJSONObject(i);


                String authors = "";

            if(jsonArticle.has("tags")) {
                JSONArray jsonTagsArray = jsonArticle.getJSONArray("tags");
                for (int j = 0; j < jsonTagsArray.length(); j++) {
                    JSONObject jsonTag = jsonTagsArray.getJSONObject(j);
                    if(jsonTag.getString("type").equals("contributor")) {
                        String authorName = jsonTag.getString("webTitle");

                        if(authors.length() > 0)
                            authors += ", ";

                        authors += authorName;
                    }
                }

            }
                Article article = new Article(jsonArticle.getString("sectionName"),
                        jsonArticle.getString("webTitle"),
                        jsonArticle.getString("webUrl"),
                        jsonArticle.getString("webPublicationDate"));
                mArticles.add(article);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Article> getArticles() {
        return mArticles;
    }

}
