package com.example.sberbank.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.net.URL;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {

    private ResponseCallback<Bitmap> mCallback;

    public ImageLoader(@NonNull ResponseCallback<Bitmap> callback) {
        mCallback = callback;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        Bitmap image = null;

        try {
            URL url = new URL(strings[0]);
            image = BitmapFactory.decodeStream(url.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null)
            mCallback.onSuccess(bitmap);
        else mCallback.onFailure(new Exception());
    }
}
