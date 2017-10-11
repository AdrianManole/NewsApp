package com.adrianmanole.newsapp;

/**
 * An {@link News} object contains news stories from The Guardian api
 */

public class News {

    /**
     * Section name
     */

    private String mSection;


    /**
     * Title of the story
     */

    private String mTitle;

    /**
     * Publication date of the story
     */

    private String mDate;

    /**
     * Website URL of the story
     */

    private String mUrl;


    /**
     * Construct a new {@link News} object
     *
     * @param section is part of an article
     * @param title   of the story
     * @param date    is the date when the article was published
     * @param url     is the website URL to find more details about the story
     */

    public News(String section, String title, String date, String url) {

        mSection = section;
        mTitle = title;
        mDate = date;
        mUrl = url;

    }

    /**
     * @return the section of the story
     */

    public String getSection() {
        return mSection;
    }


    /**
     * @return the title of the story
     */

    public String getTitle() {
        return mTitle;
    }


    /**
     * Returns the date when the story was published
     */

    public String getDate() {
        return mDate;
    }

    /**
     * Returns the website URL to find more details about the story
     */

    public String getUrl() {
        return mUrl;
    }

}