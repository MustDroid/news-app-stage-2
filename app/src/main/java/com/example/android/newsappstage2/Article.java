package com.example.android.newsappstage2;

/**
 * Created by Emoke Hajdu on 6/23/2018.
 */

public class Article {
    private String mSectionName;
    private String mWebTitle;
    private String mWebUrl;
    private String mWebPublicationDate;
    private String mAuthor;

    public String getAuthor() {
        return mAuthor;
    }

    public Article(String Author) {

        this.mAuthor = Author;
    }

    public Article(String sectionName, String webTitle, String webUrl, String webPublicationDate) {
        this.mSectionName = sectionName;
        this.mWebTitle = webTitle;
        this.mWebUrl = webUrl;
        this.mWebPublicationDate = webPublicationDate;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public String getWebPublicationDate() {
        return mWebPublicationDate;
    }
}
