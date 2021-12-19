package com.cranaya.ligaespaola.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.cranaya.ligaespaola.LigaApp;
import com.cranaya.ligaespaola.R;
import com.squareup.picasso.Picasso;

public class LoadImage{

    @BindingAdapter("image_url")
    public static void loadImage(ImageView imageView,String url){
        Picasso.with(imageView.getContext())
                .load(url)
                .resize(450, 600)
                .placeholder(R.drawable.default_foto)
                .error(R.drawable.default_foto)
                .into(imageView);

    }
}
