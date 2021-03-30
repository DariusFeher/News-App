package com.example.android.newsapp;

import android.content.Context;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class NewsAdapter extends ArrayAdapter<News> {
    private static final String LOG_TAG = NewsAdapter.class.getSimpleName();


    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        News currentNews = (News) getItem(position);

        TextView articleTitleView = (TextView) listItemView.findViewById(R.id.article_title);
        articleTitleView.setText(currentNews.getArticleTitle());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        String time = currentNews.getTime();
        // if there is a date associated with the article
        if (!time.equals("")) {
            dateView.setVisibility(View.VISIBLE);
            timeView.setVisibility(View.VISIBLE);

            TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(time);
            Instant i = Instant.from(ta);
            Date result1 = Date.from(i);


            String formattedDate = formatDate(result1);
            String formattedTime = formatTime(result1);

            Log.i(LOG_TAG, "FORMATTED TIME: " + formattedTime);
            Log.i(LOG_TAG, "FORMATTED DATE: " + formattedDate);

            dateView.setText(formattedDate);
            timeView.setText(formattedTime);
        } else {
            // otherwise set the visibility to GONE
            dateView.setVisibility(View.GONE);
            timeView.setVisibility(View.GONE);
        }

        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        // if there is not an authot associated with the article
        if (currentNews.getAuthor().equals(""))
            authorView.setVisibility(View.GONE);
        else {
            // otherwise display it
            authorView.setVisibility(View.VISIBLE);
            authorView.setText(currentNews.getAuthor());
        }

        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        sectionView.setText(currentNews.getSectionName());

        listItemView.setBackgroundResource(R.drawable.rounded_corners);

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


}

