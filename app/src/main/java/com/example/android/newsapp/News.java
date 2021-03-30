package com.example.android.newsapp;

public class News {

    private String articleTitle;
    private String newsURL;
    private String time;
    private String author;
    private String sectionName;

    public News(String articleTitleIn, String newsURLIn, String timeIn, String authorIn, String sectionNameIn) {
        articleTitle = articleTitleIn;
        sectionName = sectionNameIn;
        newsURL = newsURLIn;
        time = timeIn;
        author = authorIn;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthor() {
        return author;
    }

    public String getTime() {
        return time;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public String getArticleTitle() {
        return articleTitle;
    }
}
