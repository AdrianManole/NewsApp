package com.adrianmanole.newsapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * An {@link News} knows how to create a list item layout for each news
 * story in the data source (a list of {@link News} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context     of the app
     * @param newsStories is the list of news stories, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> newsStories) {
        super(context, 0, newsStories);
    }

    /**
     * Returns a list item view that displays information about the news story at the given position
     * in the list of news stories.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news story at the given position in the list of news
        News currentNews = getItem(position);

        String section = currentNews != null ? currentNews.getSection() : null;
        // Find the TextView with view ID section
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        // Display the title of the current news story in that TextView
        sectionView.setText(section);

        // Set the proper background color on the section circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable sectionCircle = (GradientDrawable) sectionView.getBackground();
        // Get the appropriate background color based on the current news section
        assert currentNews != null;
        String sectionColor = getSectionColor(currentNews.getSection());
        // Set the color on the magnitude circle
        sectionCircle.setColor(Integer.parseInt(sectionColor));

        String title = currentNews.getTitle();
        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Display the title of the current news story in that TextView
        titleView.setText(title);

        // Create a new string object from the date when the news story was published
        String dateObject = currentNews.getDate();

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Jul 23, 2017")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current news story in that TextView
        dateView.setText(formattedDate);


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the color for the section rectangle based on the news section.
     *
     * @param section of the news story
     */
    private String getSectionColor(String section) {
        int sectionColorResourceId;
        switch (section) {
            case "Politics":
                sectionColorResourceId = R.color.politics;
                break;
            case "Education":
                sectionColorResourceId = R.color.education;
                break;
            case "Uk news":
                sectionColorResourceId = R.color.uk_news;
                break;
            case "Environment":
                sectionColorResourceId = R.color.environment;
                break;
            case "Opinion":
                sectionColorResourceId = R.color.opinion;
                break;
            case "US news":
                sectionColorResourceId = R.color.us_news;
                break;
            case "Teacher Network":
                sectionColorResourceId = R.color.teacher_network;
                break;

            default:
                sectionColorResourceId = R.color.default_color;
                break;
        }

        return String.valueOf(ContextCompat.getColor(getContext(), sectionColorResourceId));
    }

    /**
     * Return the formatted date string (i.e. "Jul 23, 2017") from a String object.
     */
    private String formatDate(String dateObject) {
        String jsonDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat dateFormat = new SimpleDateFormat(jsonDateFormat, Locale.ENGLISH);
        try {
            Date parsedJsonDate = dateFormat.parse(dateObject);
            String finalDatePattern = "MMM dd, yyyy";
            SimpleDateFormat finalDateFormatter = new SimpleDateFormat(finalDatePattern, Locale.ENGLISH);
            return finalDateFormatter.format(parsedJsonDate);
        } catch (ParseException e) {
            Log.e("QueryUtils", "Error parsing JSON date: ", e);
            return "";
        }
    }
}