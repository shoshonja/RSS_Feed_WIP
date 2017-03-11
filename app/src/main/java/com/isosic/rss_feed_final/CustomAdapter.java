package com.isosic.rss_feed_final;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<RssFeedItems> {
    private Context context;
    private ArrayList<RssFeedItems> feedItems;
    private String[] titles;
    private String[] descriptions;
    private String[] dates;
    private String[] thumbnails;
    private String[] links;

    public CustomAdapter(Context context, ArrayList<RssFeedItems> feedItems) {
        super(context, R.layout.rss_layout, feedItems);
        this.context = context;
        this.feedItems = feedItems;

        populate();
    }

    private void populate() {
        int size = feedItems.size();
        titles = new String[size];
        descriptions = new String[size];
        dates = new String[size];
        thumbnails = new String[size];
        links = new String[size];

        for (int i = 0; i < size; i++) {
            titles[i] = feedItems.get(i).getTitle();
            descriptions[i] = feedItems.get(i).getDescription();
            dates[i] = feedItems.get(i).getPubDate();
            thumbnails[i] = feedItems.get(i).getThumbnail();
            links[i] = feedItems.get(i).getLink();
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View feedView = inflater.inflate(R.layout.rss_layout, parent, false);

        ImageView thumbnail = (ImageView) feedView.findViewById(R.id.ivThumbnail);
        TextView title = (TextView) feedView.findViewById(R.id.tvTitle);
        TextView description = (TextView) feedView.findViewById(R.id.tvDescription);
        TextView pubDate = (TextView) feedView.findViewById(R.id.tvPubDate);

        title.setText(titles[position]);
        description.setText(descriptions[position]);
        pubDate.setText(dates[position]);

        new ImageDownloader(thumbnail).execute(thumbnails[position]);


        return feedView;

    }
}
