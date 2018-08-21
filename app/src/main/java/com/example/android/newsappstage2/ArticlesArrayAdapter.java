package com.example.android.newsappstage2;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Emoke Hajdu on 6/23/2018.
 */

public class ArticlesArrayAdapter extends ArrayAdapter<Article> {
    private List<Article> mArticles;

    public ArticlesArrayAdapter(Context context, List<Article> articles) {
        super(context, R.layout.article, articles);

        this.mArticles = articles;
    }
    private class ViewHolder {
        public TextView txtWebTitle;
        public TextView txtSectionName;
        public TextView txtWebUrl;
        public TextView txtPublicationDate;

    }

    public List<Article> getArticles() {
        return mArticles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.article, parent, false);

            viewHolder.txtWebTitle = (TextView) convertView.findViewById(R.id.txtWebTitle);
            viewHolder.txtSectionName = (TextView) convertView.findViewById(R.id.txtSectionName);
            viewHolder.txtWebUrl = (TextView) convertView.findViewById(R.id.txtwebURL);
            viewHolder.txtPublicationDate = (TextView) convertView.findViewById(R.id.txtPublicationDate);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Article article = mArticles.get(position);

        viewHolder.txtWebTitle.setText(article.getWebTitle());
        viewHolder.txtSectionName.setText(article.getSectionName());
        viewHolder.txtPublicationDate.setText(article.getWebPublicationDate());
        String html = "<a href='" + article.getWebUrl() + "'>Read More...</a>";
        viewHolder.txtWebUrl.setText(article.getWebUrl());

        return convertView;
    }
}
