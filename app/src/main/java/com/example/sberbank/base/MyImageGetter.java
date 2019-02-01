package com.example.sberbank.base;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.widget.TextView;
import com.example.sberbank.remote.ImageLoader;
import com.example.sberbank.remote.ResponseCallback;

public class MyImageGetter implements Html.ImageGetter {

    private TextView target;

    public MyImageGetter(final TextView target) {
        this.target = target;
    }

    @Override
    public Drawable getDrawable(String source) {
        final LevelListDrawable listDrawable = new LevelListDrawable();

        new ImageLoader(new ResponseCallback<Bitmap>() {
            @Override
            public void onSuccess(Bitmap response) {
                BitmapDrawable drawable = new BitmapDrawable(response);
                listDrawable.addLevel(1, 1, drawable);
                float k = ((float) response.getWidth() / response.getHeight());
                listDrawable.setBounds(target.getLeft(), 0, target.getRight(), (int) (target.getRight() / k));
                listDrawable.setLevel(1);
                CharSequence text = target.getText();
                target.setText(text);
            }

            @Override
            public void onFailure(Exception e) {
            }
        }).execute(source);

        return listDrawable;
    }
}
