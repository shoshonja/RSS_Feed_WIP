package com.isosic.rss_feed_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Shosh on 11.3.2017..
 */

public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {

    ImageView imageView;

    public ImageDownloader(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap thumbnail = null;

        try {
            InputStream inputStream = new java.net.URL(url).openStream();
            thumbnail = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thumbnail;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
